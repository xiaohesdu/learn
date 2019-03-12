package com.learn.mode.observer;

/**
 * @author gonghe.hogan
 */

public interface Subject {

    void addObserver(Observer observer);

    void notifyALl();
}
