package com.learn.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gonghe.hogan
 */

public class T40 {
    /**
     *
     * 一组数{2,3,4,6,7,9}，寻求他们的和为target（例如9）的组合，每一个数不可以重复使用
     *
     */

    public static List<List<Integer>> findSum(int[] nums, int target){
        if (nums.length == 0){
            return new ArrayList<>();
        }
        List<List<Integer>>  result = new ArrayList<>();
        dps(result, new ArrayList<>(), nums, target, 0);
        return result;
    }

    public static void dps(List<List<Integer>> results, List<Integer> oneList,int[] nums, int target, int eachBegin){
        final int sum = getSum(oneList);
        if (sum >= target){
            if (sum == target){
//                这里不能简单填oneList，否则这个索引最终只是执行了前面的new ArrayList<>()，也就是空list，没有保存实际的结果
                results.add(new ArrayList<>(oneList));
            }
            return;
        }
        for (int i = eachBegin; i < nums.length; i++){
            oneList.add(nums[i]);
            //            dps中每一轮的begin都是当前的i+1、每次都要排除当前轮的值，因为不能重复使用
            dps(results,oneList,nums,target, i+1);
            oneList.remove(oneList.size()-1);
        }
    }

    public static int getSum( List<Integer> oneList){
        int sum = 0;
        for (int i : oneList){
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,6,7,9};
        int target = 9;
        final List<List<Integer>> sumResult = findSum(nums, target);

        System.out.println(sumResult.toString());
    }
}
