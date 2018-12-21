package com.learn.algo.dp;

import java.util.Objects;

/**
 * @author gonghe.hogan
 */

public class MaxCommonString {

    public static String getMaxCommonString(String s1, String s2){
//        maxCommonStringWithEnd代表以i和j结尾的字符串下， 最大相同的个数
        int[][] maxCommonStringWithEnd = new int[s1.length()+1][s2.length()+1];
        int maxLength = 0;
        int finalI = 0;
        for (int i = 0; i <s1.length()+1;i++){
            for (int j = 0; j < s2.length() +1; j++){
                if (i == 0 || j == 0){
                    maxCommonStringWithEnd[i][j] = 0;
                    continue;
                }
                if (Objects.equals(s1.charAt(i-1), s2.charAt(j-1))){
                    maxCommonStringWithEnd[i][j] = maxCommonStringWithEnd[i-1][j-1] + 1;
                }else {
//                  以i和j结尾，如果i和j对应的char不等，那么久应该是0（他们俩个结尾的字符串显然不等）
                    maxCommonStringWithEnd[i][j] = 0;
                }
                if (maxCommonStringWithEnd[i][j] > maxLength){
                    maxLength = maxCommonStringWithEnd[i][j];
                    finalI = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (maxLength > 0){
            sb.append(s1.charAt(finalI-1));
            finalI--;
            maxLength--;
        }

        return sb.toString();
    }


    public static void main(String[] args) {
//        String s1 = "aabbj";
//        String s2 = "aajcd";

        String s1 = "aabbjceefg";
        String s2 = "ajbbjdeefg";

        final String maxCommonString = getMaxCommonString(s1, s2);
        System.out.println(maxCommonString);
    }
}

