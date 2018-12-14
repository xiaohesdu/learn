package com.learn.mode.observer;

/**
 * @author gonghe.hogan
 */

public class Observer1 implements Observer {
    String name;

    public Observer1(String name) {
        this.name = name;
    }
    @Override
    public void call(String state) {
        System.out.println("收到主题的状态变为"+state);
    }
}
