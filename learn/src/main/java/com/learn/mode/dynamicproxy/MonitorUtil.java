package com.learn.mode.dynamicproxy;

/**
 * @author gonghe.hogan
 */

public class MonitorUtil {

    private static ThreadLocal<Long> time = new ThreadLocal<>();

    public static void start(){
        System.out.println("调用before方法");
        time.set(System.currentTimeMillis());
    }

    public static void end(){
        System.out.println("调用end方法");
        final Long begin = time.get();
        System.out.println("方法执行了" + (System.currentTimeMillis() - begin) + "ms");
    }
}
