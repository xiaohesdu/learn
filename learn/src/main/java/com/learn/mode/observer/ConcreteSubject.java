package com.learn.mode.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gonghe.hogan
 */

public class ConcreteSubject implements Subject {
    private String state;
    private Set<Observer> observers = new HashSet<>();

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyALl() {
        for (Observer observer : observers){
            observer.call(state);
        }
    }
}
