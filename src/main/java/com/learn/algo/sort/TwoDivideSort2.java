package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @author gonghe.hogan
 */

public class TwoDivideSort2 {
    public static void sort(int[] nums){
//        这样不至于出现在每个merge中新建数组，而是在之前统一建一个，后续都使用它
        int[] result = new int[nums.length];
        twoDivideSort(nums, 0, nums.length, result);
    }
    public static void twoDivideSort(int[] nums, int L, int R, int[] result){
        if (R <= L){
            return;
        }
        int middle = (L + R)/2;
        twoDivideSort(nums, L, middle, result);
        twoDivideSort(nums, middle+1, R, result);
        merge(nums, L, R,middle, result);

    }
    public static void merge(int[] nums, int L, int R, int middle, int[] result){
        int leftStart = L;
        int rightStart = middle+1;
        int resultIndex = 0;
        while (leftStart <= middle && rightStart <= R){
            if (nums[leftStart] <= nums[rightStart]){
                result[resultIndex++] = nums[leftStart++];
            }else {
                result[resultIndex++] = nums[rightStart++];
            }
        }
        while (leftStart <= middle){
            result[resultIndex++] = nums[leftStart++];
        }
        while (rightStart <= R){
            result[resultIndex++] = nums[rightStart++];
        }
        for (int i = 0; i < result.length;i++){
            nums[L++] = result[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4,9,5,3,4};
//        int[] nums = {3,4,9,5};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
