package com.learn;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author gonghe.hogan
 */

public class SortComparator implements Comparator<StreamSort> {
    @Override
    public int compare(StreamSort o1, StreamSort o2) {
        if (Objects.equals(o1,o2)){
            return 0;
        }
        Integer age1 = o1.age;
        Integer age2 = o2.age;
        if (Objects.equals(age1, age2)){
            return 0;
        }
        if (age1 == null){
            return -1;
        }
        if (age2 == null){
            return -1;
        }
        return (age1 < age2) ? -1 : 1;

    }

    public static void main(String[] args) {
        final Comparator<StreamSort> streamSortComparator = (o1, o2) -> 0;
    }
}
