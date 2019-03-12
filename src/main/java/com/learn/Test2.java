package com.learn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @author gonghe.hogan
 */

public class Test2 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("testStack-%d").build());
        String s = "";
        for (int i =0; i < 1000000; i ++){
            final int  count = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String b = new String("********test" + count + "****");
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
