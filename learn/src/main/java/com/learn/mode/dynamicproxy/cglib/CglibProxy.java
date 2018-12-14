package com.learn.mode.dynamicproxy.cglib;

import com.learn.mode.dynamicproxy.MonitorUtil;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author gonghe.hogan
 */

public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    //里面底层的字节码技术，为一个Class类型创建其子类的一个实例
    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     *intercept方法会拦截所有目标类（Object o）的方法调用（Method method），
     * 然后在里面实现织入逻辑，核心的方法是 methodProxy.invokeSuper，
     * methodProxyshij实际上是是代理类的实例，这里也就是调用其父类方法
     * 它不是基于反射实现方法调用的，而是直接调用。用了一个FastClassInfo来存储对象（委托类对象和代理类对象）和方法（实际上是方法的下标）
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MonitorUtil.start();
        final Object result = methodProxy.invokeSuper(o, objects);
        MonitorUtil.end();
        return result;
    }
}
