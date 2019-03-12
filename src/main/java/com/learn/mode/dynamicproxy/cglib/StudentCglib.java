package com.learn.mode.dynamicproxy.cglib;

import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */

public class StudentCglib {

    public void giveMoney() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println(" 上交班费10元。 cglib");
    }

    public void study() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( " 在学习。 cglib");
    }
}
