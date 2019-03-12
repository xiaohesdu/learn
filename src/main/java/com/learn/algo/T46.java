package com.learn.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gonghe.hogan
 */

public class T46 {

    /**
     *
     * 全排列，输入是不重复的，返回结果支持顺序
     */

    public static List<List<Integer>> permute(int[] nums){
        if (nums.length == 0){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        executeAllShow(result, new ArrayList<>(), nums);
        return result;
    }

    public static void executeAllShow(List<List<Integer>> result, List<Integer> oneAnswer,int[] nums){
//        终止条件
        if (nums.length <= 0){
            result.add(new ArrayList<>(oneAnswer));
            return;
        }
//        继续条件
        for (int i = 0; i < nums.length; i++){
            oneAnswer.add(nums[i]);
            final int[] newArrayAfterRemove = getNewArrayAfterRemove(nums, i);
            executeAllShow(result, oneAnswer, newArrayAfterRemove);
            oneAnswer.remove(oneAnswer.size()-1);
        }
    }

    public static int[] getNewArrayAfterRemove(int[] nums, int position){
        final int[] copyArray = new int[nums.length-1];
        for (int i = 0; i < copyArray.length; i ++){
            if (i >= position){
                copyArray[i] = nums[i+1];
            }else {
                copyArray[i] = nums[i];
            }
        }
        return copyArray;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        final List<List<Integer>> permute = permute(nums);
        System.out.println(permute.toString());
    }

}
