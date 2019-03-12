package com.learn.algo;

import java.util.Arrays;

/**
 * @author gonghe.hogan
 */

public class T34 {

    public static int[] searchRange(int[] nums, int target){
        int[] notMeetResult = {-1,-1};

        if (nums.length == 0){
            return notMeetResult;
        }
        final int first = findFirst(nums, target, 0, nums.length - 1);

        if (first == -1){
            return notMeetResult;
        }

        int[] result = new int[2];
        result[0] = first;

        if (first == nums.length - 1){
            result[1] = first;
            return result;
        }

        final int last = findLast(nums, target, first + 1, nums.length - 1);
        if (last == -1){
            result[1] = first;
            return result;
        }
        result[1] = last;
        return result;
    }

    public static int findFirst(int[] nums, int target, int left, int right){
        int center = (left + right)/2;
        if (center == left){
            if (nums[center] == target){
                return center;
            }
            if (nums[right] == target){
                return right;
            }
            return -1;
        }
        if (nums[center] > target){
            return findFirst(nums, target, left, center-1);
        }else if (nums[center] < target){
            return findFirst(nums, target,center+1, right);
        }else {
            return findFirst(nums, target, left, center);
        }
    }

    public static int findLast(int[] nums, int target, int left, int right){
//        隐含在下面的center==left中了
//        if (left >= nums.length-1){
//            if (nums[left] == target){
//                return left;
//            }else {
//                return -1;
//            }
//        }

        int center = (left + right)/2;
        if (center == left){
            if (nums[right] == target){
                return right;
            }
            if (nums[center] == target){
                return center;
            }
            return -1;
        }

        if (nums[center] > target){
            return findLast(nums, target, left, center-1);
        }else if (nums[center] < target){
            return findLast(nums, target,center+1, right);
        }else {
            return findLast(nums, target, center, right);
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,1,2,3};
        int target = 0;
        final int[] ints = searchRange(nums, target);
        System.out.println(Arrays.toString(ints));
    }

}
