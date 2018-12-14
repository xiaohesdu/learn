package com.learn.callback;

/**
 * @author gonghe.hogan
 */

public class ClientTest1 {

    public static void main(String[] args) {

        Caller caller = new Caller();

        caller.setMyCallback(new MyCallbackImp());

        caller.execute();
    }
}
