package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class QuickSOrt {

    public static int partion(int[] a, int L, int R){
        int base = a[L];
        int basePosition = L;
        while (R > L){
            while (R > L && a[R] >= base){
                R --;
            }
            while (R > L && a[L] <= base){
                L ++;
            }
            if (L < R){
                int temp = a[L];
                a[L] = a[R];
                a[R] = temp;
            }
        }
        a[basePosition] = a[R];
        a[R] = base;
        return R;
    }

    public static void quickSort(int[] a, int L, int R){
        if (L >= R){
            return;
        }
        final int partion = partion(a, L, R);
        quickSort(a, L, partion-1);
        quickSort(a,partion+1, R);
    }
    public static void main(String[] args) {
        int[] a = new int[]{10,8,14,4,5,20,1,84};
        quickSort(a, 0, a.length-1);
        System.out.println(a.toString());
    }
}
