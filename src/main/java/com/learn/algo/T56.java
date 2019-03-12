package com.learn.algo;

import java.util.*;

/**
 * @author gonghe.hogan
 */

public class T56 {

     static class Interval {
         int start;
         int end;

         Interval() {
             start = 0;
             end = 0;
         }

         Interval(int s, int e) {
             start = s;
             end = e;
         }
     }

    public static List<Interval> mergeSection(Interval[] intervals){
         Arrays.sort(intervals, new Comparator<Interval>(){
             @Override
             public int compare(Interval o1, Interval o2){
                return o1.start - o2.start;
             }
         });


         List<Interval> result = new ArrayList<>();
         for (Interval interval : intervals){
             if (result.isEmpty()){
                 result.add(interval);
                 continue;
             }

             Interval  lastStoreInterval = result.get(result.size()-1);
            if (interval.start > lastStoreInterval.end ){
 //                not merge, just put new
                result.add(interval);
            }else {
//                merge
                lastStoreInterval.end = Math.max(lastStoreInterval.end, interval.end);
            }
         }

        return result;
    }




    public static void main(String[] args) {
        Interval[] sections = new Interval[]{new Interval(1, 3), new Interval(2, 6),
                new Interval(4, 10), new Interval(15, 18)};
        final List<Interval> intervals = mergeSection(sections);
        System.out.println(intervals.toString());

    }



}
