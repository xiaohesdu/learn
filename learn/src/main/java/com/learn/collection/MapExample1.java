package com.learn.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class MapExample1 {

    public static void main(String[] args) {
        Map<String, String > map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");

        final String c = map.get("c");
        log.info(c);
    }
}
