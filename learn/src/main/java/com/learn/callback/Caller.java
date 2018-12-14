package com.learn.callback;

/**
 * @author gonghe.hogan
 */

public class Caller {
//    它是执行调用方法的

    private MyCallback myCallback;

//    public Caller(MyCallback myCallback){
//        this.myCallback = myCallback;
//    }

    public void setMyCallback(MyCallback myCallback){
        this.myCallback = myCallback;
    }

    public void execute(){

        System.out.println("caller执行了某个方法");
//        当调用Caller的execute方法时，会回调MyCallbackImp提供的call方法
        myCallback.call();
    }

}
