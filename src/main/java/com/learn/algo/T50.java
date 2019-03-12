package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class T50 {
    /**
     * pow(x,n), x is double
     */

    public static double getPow(double x, int n){
        if (x == 0){
            return 0.0;
        }
        if (n >= 0){
            return getPositivePow(x, n);
        }else {
            return 1/getPositivePow(x, n * -1);
        }
    }

    public static double getPositivePow(double x, long n){
//        n > 0
        if (n < 0){
            throw new RuntimeException("n must be > 0.");
        }
        double result = 1.0;
        for (long i = n; i > 0; i--){
            result = result * x;
        }
        return result;
    }

    public static void main(String[] args) {
        double x = 0.0;
        int n = -3;
        System.out.println(getPow(x,n));
    }
}
