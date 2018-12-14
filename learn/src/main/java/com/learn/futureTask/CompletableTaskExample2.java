package com.learn.futureTask;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class CompletableTaskExample2 {

    private static Integer handle(Integer taskNum){
        log.info("execute single task: list add operation.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return taskNum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Long start = System.currentTimeMillis();

        List<String> list = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> taskList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        final CompletableFuture[] completableFutures = taskList.stream().map(integer ->
                CompletableFuture.supplyAsync(() -> handle(integer), executorService)
                        .thenApply(integer1 -> Integer.toString(integer1))
                        .whenComplete((s, throwable) -> {
                            list.add(s);
                            log.info("task {} finished. result : {}, exception e : {}", s, s, throwable);
                        })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
        log.info("result :{} " , list.toString());

        log.info("耗时：{} ", System.currentTimeMillis() - start );

    }

    public static void test1(){
        List<String> list = new ArrayList<>();
        List<Integer> taskList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        final CompletableFuture[] objects = taskList
                .stream()
                .map(integer -> Integer.toString(integer))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(objects).join();

    }
}
