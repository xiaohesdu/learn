package com.learn.mode.dynamicproxy.cglib;

/**
 * @author gonghe.hogan
 */

public class ClinetCglib {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        StudentCglib  studentCglibProxy = (StudentCglib) proxy.getProxy(StudentCglib.class);
        studentCglibProxy.giveMoney();
        studentCglibProxy.study();
    }
}
