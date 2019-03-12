package com.learn.algo;

import org.springframework.util.Assert;

/**
 * @author gonghe.hogan
 */

public class T38 {

    public static String countAndSay(int n){
        if (n == 0){
            return "10";
        }
        String nums = "1";
        if (n == 1){
            return "1";
        }
        for (int i = 1; i < n; i++){
            nums = generateOneCount(nums);
        }
        return nums;

    }

    public static String generateOneCount(String nums){
        if ("0".equals(nums)){
            return "10";
        }
        int start;

        StringBuilder sb = new StringBuilder();
        for (start = 0; start < nums.length(); ){
            final int continueRepeatCount = findContinueRepeatCount(nums, start);
            sb.append(continueRepeatCount).append(nums.charAt(start));
            start = start + continueRepeatCount;
        }
        return sb.toString();
    }

    public static int findContinueRepeatCount(String nums , int start){
        final int length = nums.length();
        Assert.isTrue(start < length, "start must be less than length.");
        if (start == length -1){
            return 1;
        }
        final char oneChar = nums.charAt(start);
        int count = 1;
        for (int i = start + 1; i < length; i++){
            if (nums.charAt(i) == oneChar){
                count++;
            }else {
                return count;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

}
