package com.learn.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class ConcurrentHashMapExample1 {


    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer > map = new ConcurrentHashMap<>();
        int sameTime = 200;
        int totalCount = 200;
        final ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(sameTime);
        CountDownLatch countDownLatch = new CountDownLatch(totalCount);

        for (int i = 0; i < totalCount;i++){
            semaphore.acquire();
            final int count = i;
            executorService.submit(() -> {
                map.put(count+"", count);
                countDownLatch.countDown();
            });

            semaphore.release();
        }

        countDownLatch.await();
        System.out.println("执行完毕");
        executorService.shutdown();

    }
}
