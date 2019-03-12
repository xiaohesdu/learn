package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class T51 {
//max sey sums, at least one num is required in final result.
    public int maxSubArray(int[] nums){
        int tempSum = 0;
        int sum = nums[0];
        for (int i : nums){
            if (tempSum > 0){
                tempSum = tempSum + i;
            }else {
                tempSum = i;
            }
            sum = Math.max(tempSum, sum);

        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        final int i = new T51().maxSubArray(nums);
        System.out.println(i);
    }
}
