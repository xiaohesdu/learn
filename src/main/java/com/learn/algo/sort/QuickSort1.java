package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @author gonghe.hogan
 */

public class QuickSort1 {

    public static void quickSort(int[] num, int L, int R){
        if (L >= R){
            return;
        }
        int flagPosition = sortAndGetPosition(num, L, R);
        quickSort(num, L, flagPosition-1);
        quickSort(num, flagPosition+1, R);
    }

    public static int sortAndGetPosition(int[] num, int L, int R){
        int flagNum = num[L];
        int initFlagPosition = L;
        while (R > L){
            while (R > L && num[R] >= flagNum){
                R--;
            }
            while (R > L && num[L] <= flagNum){
                L++;
            }
            if (R > L){
                swap(num, L, R);
            }
        }
        swap(num, initFlagPosition, R);
        return R;
    }

    public static void swap(int[] num, int a, int b){
//                swap
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

    public static void main(String[] args) {
        int[] num = new int[]{10,8,5,1};
//        int[] num = new int[]{10,8,14,4,5,20,1,84};
        quickSort(num, 0, num.length-1);
        System.out.println(Arrays.toString(num));
    }
}
