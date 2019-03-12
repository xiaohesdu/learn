package com.learn.callback;

/**
 * @author gonghe.hogan
 */

public class MyCallbackImp implements MyCallback {
    @Override
    public void call() {
//        通过接口实现了一个回调方法，等待被Caller调用
        System.out.println("被调用者callee 执行了一个调用方法");
    }
}
