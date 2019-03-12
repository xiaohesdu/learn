package com.learn.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.util.JdbcConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class DruidExample {

    public static void main(String[] args) {
        String sql = "SELECT id, userName, password, age FROM (select a,b from t_user order by time desc) where id = 1 group by app_id order by time desc limit 5 ";
//        String sql = "  ";

        //        解析成ast抽象语法树(不同query语句用逗号隔开，返回的是一个list)
        final SQLStatement sqlStatements = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL).get(0);
//      查询语句向下转型成SQLSelectStatement
        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatements;


        //获取queryBlock（接口）
        final SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();
//        转为实现类
        final SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;

        final SQLExpr where = sqlSelectQueryBlock.getWhere();
        log.info("where : {}", where);
        final SQLTableSource from = sqlSelectQueryBlock.getFrom();
        log.info("from : {}", from);
        final List<SQLSelectItem> selectList = sqlSelectQueryBlock.getSelectList();
        log.info("select : {}", selectList.toString());

        sqlSelectQueryBlock.addWhere(SQLUtils.toSQLExpr("userName is not null"));

        final SQLLimit limit = sqlSelectQueryBlock.getLimit();
        if (limit == null){
            sqlSelectQueryBlock.setLimit(new SQLLimit(SQLUtils.toSQLExpr("3")));
        }else {
            final SQLExpr rowCount = limit.getRowCount();
            SQLIntegerExpr sqlIntegerExpr = (SQLIntegerExpr)rowCount;
            final int limitNum = sqlIntegerExpr.getNumber().intValue();
            if(limitNum > 3){
                sqlSelectQueryBlock.setLimit(new SQLLimit(SQLUtils.toSQLExpr("3")));
            }
        }

//        final SQLExpr offset = sqlSelectQueryBlock.getOffset();
//        if (offset == null){
//            sqlSelectQueryBlock.setOffset(SQLUtils.toSQLExpr("2"));
//        }else {
//
//        }


        final String newSqlString = SQLUtils.toSQLString(sqlSelectStatement);

        /**
         * SELECT id, userName, password, age
         * FROM (
         * 	SELECT a, b
         * 	FROM t_user
         * 	ORDER BY time DESC
         * )
         * WHERE id = 1
         * AND userName IS NOT NULL
         * GROUP BY app_id
         * ORDER BY time DESC
         * LIMIT 2, 3
         *
         * 注意limit 2,3相当于limit 3 offset 2
         */
        log.info("new sql : {}" , newSqlString);
    }
}
