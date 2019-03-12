package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class TwoSortFindExample2 {

    /**
     * 找到序列中次序变化后的第一个值
     * @param a
     * @param L
     * @param R
     * @return
     */
    public static int findChange(int[] a, int L, int R) {

        int mid = (L+R)/2;
        //边界条件
        if (mid == L){
            if (a[mid] <= a[mid+1]){
                return -1;
            }else {
                return mid;
            }
        }

        //终止条件
        if (a[mid] < a[mid-1]){
            return mid;
        }
        if (a[mid] > a[L]){
            return findChange(a, mid, R);
        }else {
            return findChange(a, L, mid);
        }

    }


    public static void main(String[] args) {
//        int num[] = new int[]{6,7,8,9,10,1,2,3,4,5};
        int num[] = new int[]{6,7,8,9,10};
        final int change = findChange(num, 0, num.length - 1);
        System.out.println(change);

    }
}
