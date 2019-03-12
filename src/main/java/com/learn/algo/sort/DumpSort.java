package com.learn.algo.sort;

/**
 * @author gonghe.hogan
 */

public class DumpSort {

    //调整数组中的第n个，使该元素满足最大堆，但是数组最大只到maxLength
    public static void adjustOnePosition(int[] nums, int n, int maxLength){
        int compare = nums[n];
        for (int i = 2*n + 1; i <= maxLength; i = 2 * i +1){
//            先找出子节点最大的
            if (nums[i] < nums[i+1]){
                i = i +1;
            }
            if (nums[i] > compare){
                swap(nums, i, n);
            }
        }
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
