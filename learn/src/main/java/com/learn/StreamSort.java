package com.learn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gonghe.hogan
 */

public class StreamSort {

    Integer age;

    public StreamSort(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StreamSort{" +
                "age=" + age +
                '}';
    }

    public static void main(String[] args) {
        StreamSort streamSort1 = new StreamSort(null);
        StreamSort streamSort2 = new StreamSort(2);
        StreamSort streamSort3 = new StreamSort(6);
        StreamSort streamSort5 = new StreamSort(3);
//        StreamSort streamSort4 = null;

        List<StreamSort> streamSortList = new ArrayList<StreamSort>(){{add(streamSort1);add(streamSort2);add(streamSort3);add(streamSort5);}};
        final List<StreamSort> streamSorts = streamSortList.stream()
                .sorted(Comparator.nullsLast(new SortComparator())).collect(Collectors.toList());
        System.out.println(streamSorts.toString());

    }
}
