package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @author gonghe.hogan
 */

public class TwoDivideSort1 {
    public static void twoDivideSort(int[] nums, int L, int R){
        if (R <= L){
            return;
        }
        int middle = (L + R)/2;
        twoDivideSort(nums, L, middle);
        twoDivideSort(nums, middle+1, R);
        merge(nums, L, R,middle);

    }
    public static void merge(int[] nums, int L, int R, int middle){
        int[] result = new int[R-L+1];
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
        twoDivideSort(nums, 0 , nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
