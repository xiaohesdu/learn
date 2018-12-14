package com.learn.regix;

import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class RegixExample {


    public static void main(String[] args) {
//        final String TSDB_NOT_ALLOW_STRING = " SHOW+| DROP| INSERT";
//
//        String query = "select * from abc; drop abc".toUpperCase();
//
//        log.info("是否匹配： {}", query.matches(TSDB_NOT_ALLOW_STRING));

        final String s = String.valueOf(null);
        log.info(s);

        Pattern pattern = Pattern.compile("^([A-z0-9_]+)\\s+(.*)$");

        Matcher match = pattern.matcher("USERNAME  ");
        final boolean matches = match.matches();
        log.info("是否匹配： "+matches);
        final String group = match.group(1);

        log.info(group);

    }
}
