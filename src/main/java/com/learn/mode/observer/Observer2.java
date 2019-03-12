package com.learn.mode.observer;

/**
 * @author gonghe.hogan
 */

public class Observer2 implements Observer {
    String name;

    public Observer2(String name) {
        this.name = name;
    }

    @Override
    public void call(String state) {
        System.out.println("收到主题的状态变为"+state);
    }
}
