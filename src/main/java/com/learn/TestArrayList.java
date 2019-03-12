package com.learn;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gonghe.hogan
 */

public class TestArrayList {

        public static void main(String[] args){

            String abc = "d ,fda, dd f";
            final String[] split = abc.split(",");
            final List<String> collect = Arrays.stream(split).map(String::trim).filter(s -> !"".equals(s))
                    .sorted(Comparator.comparing(Object::toString).reversed()).collect(Collectors.toList());
            System.out.println(collect.toString());
        }

}
