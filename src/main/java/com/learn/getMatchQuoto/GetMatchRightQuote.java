package com.learn.getMatchQuoto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Stack;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class GetMatchRightQuote {

    /**
     * 找到子查询语句中匹配第一个左括号的第一个右括号，从而便于找到子查询的位置
     * @param query
     */

    public static int getMatchRightQuote(String query){
        Stack<Character> stack = new Stack<>();
        final int length = query.length();
        char[] bytes = new  char[length];
        query.getChars( 0, length, bytes, 0);
        int position = -1;

        for (int index = 0; index < length; index++){
//            如果字符是左括号就扔进栈里面；如果字符是右括号就执行出栈操作找到其匹配的左括号，当取完后栈为空说明找到第一个匹配的右括号了。
//
            final char aByte = bytes[index];
            if (Objects.equals(aByte, '(')){
                stack.push(aByte);
                continue;
            }
            if (Objects.equals(aByte, ')')){
                final Character popChar = stack.pop();
                Assert.isTrue(popChar == '(', "pop must be (");
                if (stack.empty()){
                    position = index;
                    break;
                }
            }
        }
        if (position < 0){
            log.info("没有匹配的右括号");
        }
        return position;
    }

    public static void main(String[] args) {

        String query = "(select count(*),max(b) from c) where d=1 and c = /)db";
//        String query = "(select max(b) from c where d=1 and c = db";
        final int matchRightQuote = getMatchRightQuote(query);
        log.info(String.valueOf(matchRightQuote));

    }
}
