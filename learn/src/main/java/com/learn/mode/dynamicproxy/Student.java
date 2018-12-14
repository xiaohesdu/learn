package com.learn.mode.dynamicproxy;

import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */

public class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " 上交班费10元。");
    }

    @Override
    public void study() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " 在学习");
    }
}
