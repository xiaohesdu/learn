package com.learn.mode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author gonghe.hogan
 */

public class StudentInvocationHandler<T> implements InvocationHandler {
//    Invocationhandler持有一个被代理类，他们俩之间是一层静态代理关系。

    T target;

    public StudentInvocationHandler(T target) {
        this.target = target;
    }

//    Invocationhandler通过反射执行被代理类的方法，而且被代理类所有的方法都会最终走到此处的invoke方法下。
//    这里可以自定义AOP逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        proxy是什么对象，为什么没有使用（这里的第一个参数是生成的proxy代理类，它在这里用不上；第二个method则是我们将要动态执行的方法，第三个是方法参数）
        MonitorUtil.start();
        final Object result = method.invoke(target, args);
        MonitorUtil.end();
        return result;
    }
}
