package com.learn.random;

import java.util.Random;

/**
 * @author gonghe.hogan
 */

public class RandomExample1 {

    public static void main(String[] args) {
        int totalNum = 200;
        for (int i = 0; i < totalNum; i++){
            final double random = Math.random();
            int randomNum = (int)(random * 1000000);
            System.out.println(randomNum);
        }

    }
}
