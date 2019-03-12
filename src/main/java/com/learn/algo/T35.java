package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class T35 {

    public static int searchInsert(int[] nums, int target){
        final int length = nums.length;
        if (length == 0){
            return 0;
        }
        if (target > nums[length-1]){
            return length;
        }
        if (target < nums[0]){
            return 0;
        }
        return twoDivideSearch(nums, 0 ,length-1, target);
    }


    public static int twoDivideSearch(int[] nums, int left, int right, int target ){
        int center = (left + right)/2;
        if (center == left){
            if (nums[left] == target){
                return left;
            }
            if (nums[right] == target){
                return right;
            }
            return  left + 1;
        }
        if (nums[center] > target){
            return twoDivideSearch(nums, left, center, target);
        }else {
            return twoDivideSearch(nums, center, right, target);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,3,5,6,9};
        int target = 10;
        System.out.println(searchInsert(nums, target));
    }
}
