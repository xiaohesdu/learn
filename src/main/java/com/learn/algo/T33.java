package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class T33 {

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     */

    public static int search(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }
        final int maxPosition = findMaxPosition(nums, 0, nums.length - 1);
        if (maxPosition == nums.length-1){
            return twoDivideSearch(nums, 0 , maxPosition, target);
        }
        if (target > nums[maxPosition]){
            return -1;
        }
        if (target == nums[maxPosition]){
            return maxPosition;
        }
        if (target > nums[0]){
            return twoDivideSearch(nums, 0, maxPosition-1, target);
        }else if (target < nums[0]){
            return twoDivideSearch(nums, maxPosition+1, nums.length-1, target);
        }else {
            return 0;
        }

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
            return  -1;
        }
        if (nums[center] > target){
            return twoDivideSearch(nums, left, center-1, target);
        }else {
            return twoDivideSearch(nums, center, right, target);
        }
    }

    public static int findMaxPosition(int[] nums, int left, int right){
//        change position is the max position
        int center = (left + right)/2;
        if (center == left){
            if (nums[right] > nums[left]){
                return right;
            }else {
                return left;
            }
        }
        if (nums[center] > nums[left]){
            return findMaxPosition(nums, center, right);
        }else {
            return findMaxPosition(nums, left, center);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        int target = 0;
        int position = search(nums, target);
        System.out.println(position);
    }
}
