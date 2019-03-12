package com.learn.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class FunctionX {
    public static void main(String[] args) {
        //1.分组计数
        List<Student> list1= Arrays.asList(
                new Student(1,"one","zhao"),
                new Student(2,"one","qian"),
                new Student(3,"two","sun"));

        final Map<String, List<Student>> collect = list1.stream().collect(Collectors.groupingBy(Student::getGroup));
        log.info("按照某个分组聚合成map ： {}", collect.toString());

        final Map<String, Long> collect1 = list1.stream().collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()));

        final Map<String, Integer> collect2 = list1.stream().collect(Collectors.groupingBy(o -> o.getGroup(), Collectors.summingInt(value -> value.getId())));
    }
}
