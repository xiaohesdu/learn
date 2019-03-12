package com.learn.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class appClass = Class.forName("com.learn.reflection.Apple");

        log.info(appClass.getName());
        log.info(appClass.getSimpleName());
//        final Constructor constructor = appClass.getConstructor(new Class[]{Integer.class});
//        final Constructor constructor = appClass.getConstructor(int.class);
        final Constructor constructor = appClass.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);
        final Object rawApple = constructor.newInstance(3);

        //通过getDeclaredMethod可以获取所有方法名（包括私有的），然后通过setAccessible访问私有方法
        final Method setName = appClass.getMethod("setName", new Class[]{String.class});
        setName.invoke(rawApple, "newName");
        log.info(rawApple.toString());

        final Field sizeField = appClass.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(rawApple, 5);

        log.info(rawApple.toString());
    }
}
