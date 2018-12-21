package com.learn.algo.dp;

/**
 * @author gonghe.hogan
 */

public class MaxCommonSequence {

    public static String getMaxCommonSequence(String s1, String s2){
//        currentMaxLength是当前i和j位置处，最长公共子序列的位置
        int[][] currentMaxLength = new int[s1.length()+1][s2.length()+1];

//        这个是存储每一个的上溯位置(分为向左-1，向上0，坐上为1，这样便于后续从最后一位来追溯)
        int[][] beforePosition =  new int[s1.length()+1][s2.length()+1];

//        注意一下矩阵是length+1；故后续比较字符串时就是i-1&&j-1
        for (int i = 0; i < s1.length()+1; i++) {
            for (int j = 0; j < s2.length()+1; j++) {
//                1)初始化
                if (i == 0 || j == 0) {
                    currentMaxLength[i][j] = 0;
                    continue;
                }

//                2)依次赋值
                if (s2.charAt(j-1) == s1.charAt(i-1)) {
                    currentMaxLength[i][j] = currentMaxLength[i - 1][j - 1] + 1;
                    beforePosition[i][j] = 1;
                } else {
                    int max1 = currentMaxLength[i - 1][j];
                    int max2 = currentMaxLength[i][j - 1];
                    currentMaxLength[i][j] = Math.max(max1, max2);
                    if (max1 > max2){
//                        上面大
                        beforePosition[i][j] = 0;
                    }else {
//                        左边大
                        beforePosition[i][j] = -1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
//        公共子序列可以通过矩阵最后一位获取。
// 只有beforePosition值为1（maxLength+1了）才对最终结果有贡献，其余值都只是继承前面的值不需要管（但是它们提供了继续向前的路径）。
        int i = s1.length();
        int j = s2.length();
        while(i > 0 && j > 0){
            //        这个是存储每一个的上溯位置(分为向左-1，向上0，坐上为1，这样便于后续从最后一位来追溯)
            if (beforePosition[i][j] == 1){
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }else if (beforePosition[i][j] == 0){
//                向上
                i--;
            }else {
//                向左
                j--;
            }
        }

//这里获得字符串其实是结果的反转，需要返回来
        StringBuilder reverseSb = new StringBuilder();
        for (int m = sb.length()-1; m >= 0; m--){
            reverseSb.append(sb.charAt(m));
        }
        return reverseSb.toString();
    }

    public static void main(String[] args) {
//        String s1 = "aabbj";
//        String s2 = "aajcd";

        String s1 = "aabbj";
        String s2 = "ajbbj";
        System.out.println(getMaxCommonSequence(s1, s2));
    }
}
