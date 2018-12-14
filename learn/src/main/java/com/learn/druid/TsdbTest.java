package com.learn.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.util.JdbcConstants;

/**
 * @author gonghe.hogan
 */

public class TsdbTest {

    public static void main(String[] args) {
//        String tsdbRaw = "SELECT max(input_bytes_delta)/60 as input_bytes,mean(output_errors_delta)/60 as output_errors FROM (select data, abc,test from (select * from \"switch.flow\" group by port) where host = 13 and time < now()-3h) where host = '222.73.2.238' group by time(1m),host,portName";

//        test ok
//        String tsdbRaw = "select MEAN(speed) as speed,MEAN(lost) as lost from  (select MEAN(speed) as speed,MEAN(lost) as lost from \"probe.metric\"  group by time(10m),sourceName,province) group by time(10m),sourceName";

        //test ok (group by *)
//        String tsdbRaw = "select MEAN(speed) as speed,MEAN(lost) as lost from  (select MEAN(speed) as speed,MEAN(lost) as lost from \"probe.metric\"  group by time(10m),sourceName,province) group by *";

//      test failed (do not support fill.)
        String tsdbRaw = "select MEAN(speed) as speed,MEAN(lost) as lost from  (select MEAN(speed) as speed,MEAN(lost) as lost from \"probe.metric\"  group by time(10m),sourceName,province) group by * fill 5";


//
        //        final SQLStatement sqlStatement = SQLUtils.parseStatements(tsdbRaw, JdbcConstants.PHOENIX).get(0);
        final SQLStatement sqlStatement = SQLUtils.parseStatements(tsdbRaw, JdbcConstants.MYSQL).get(0);
        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatement;
        final SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();
        final SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;

        sqlSelectQueryBlock.addWhere(SQLUtils.toSQLExpr("sourceId = 5"));
        sqlSelectQueryBlock.addWhere(SQLUtils.toSQLExpr("time > 5"));
        final String newQuery = SQLUtils.toSQLString(sqlSelectQueryBlock);
        System.out.println(newQuery);
    }


}
