package com.learn.mode.prodConsu;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gonghe.hogan
 */

public class Product1 implements Runnable{

    AtomicInteger num = new AtomicInteger(1);
    private BlockingQueue<PcData> blockingQueue;
    private volatile boolean isRunning = true;

    public Product1(BlockingQueue<PcData> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PcData pc = new PcData(num.getAndIncrement());
            try {
                final boolean offerSuccess = blockingQueue.offer(pc, 1, TimeUnit.SECONDS);
                if (offerSuccess){
                    System.out.println(new Date() + Thread.currentThread().getName() + " product one pc : " + pc.getNum());
                }else {
                    System.out.println("offer failed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop(){
        isRunning = false;
    }
}
