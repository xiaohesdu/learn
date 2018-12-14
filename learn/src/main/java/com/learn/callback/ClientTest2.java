package com.learn.callback;

/**
 * @author gonghe.hogan
 */

public class ClientTest2 {

    public static void main(String[] args) {

        Caller caller = new Caller();

        caller.setMyCallback(new MyCallback() {
            @Override
            public void call() {
                System.out.println("被调用者callee 执行了一个调用方法");
            }
        });

        caller.execute();
    }
}
