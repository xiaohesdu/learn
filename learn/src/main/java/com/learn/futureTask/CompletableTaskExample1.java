package com.learn.futureTask;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class CompletableTaskExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            System.out.println("task doing...");
            try {
                Thread.sleep(10000);
                throw new RuntimeException("sleep.");
            } catch (InterruptedException e) {
//                e.printStackTrace();
                completableFuture.completeExceptionally(e);
            }
            completableFuture.complete("okokokok");
        }).start();

        final String result = completableFuture.get(15, TimeUnit.SECONDS);
        log.info("result :{} " , result);
    }
}
