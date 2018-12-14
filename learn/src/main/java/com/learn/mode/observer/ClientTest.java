package com.learn.mode.observer;

/**
 * @author gonghe.hogan
 */

public class ClientTest {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new Observer1("observer1"));
        subject.addObserver(new Observer2("observer2"));

        subject.setState("state1");
        subject.notifyALl();

    }
}
