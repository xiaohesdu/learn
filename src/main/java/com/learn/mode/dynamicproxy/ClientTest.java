package com.learn.mode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author gonghe.hogan
 */

public class ClientTest {
    public static void main(String[] args) {
        Person studentPerson = new Student("张三");
        InvocationHandler studentInvocationHandler = new StudentInvocationHandler(studentPerson);
        final Person studentProxy =(Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, studentInvocationHandler);
        studentProxy.giveMoney();
        studentProxy.study();
    }
}
