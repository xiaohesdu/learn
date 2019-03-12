package com.learn.algo.floor;

/**
 * @author gonghe.hogan
 */

public class FloorQuestion2 {
//    100层，每次走1,2步，问次数
//    实际上满足fn = fn-1 + fn-2(一个n阶台阶，第一步是走1则有fn-1;第一步走2步则有fn-2)

    public static int solution(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        int a1 = 1;
        int a2 = 2;
        int result = 0;
        for (int i = n; i >2; i--){
            result = a1 + a2;
            a1 = a2;
            a2 = result;
        }
        return result;
    }





    public static void main(String[] args) {
        final long begin = System.currentTimeMillis();
        final int solution = solution(6);
        System.out.println("耗时：" + (System.currentTimeMillis()-begin));
        System.out.println(solution);
    }
}
