package com.learn.mode.prodConsu;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */

public class Consumer1 implements Runnable{

    private volatile boolean isRunning = true;
    private BlockingQueue<PcData> blockingQueue;

    public Consumer1(BlockingQueue<PcData> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run(){
        while (isRunning){
            try {
                final PcData poll = blockingQueue.poll(1,TimeUnit.SECONDS);
                if (poll != null){
                    System.out.println(new Date() + Thread.currentThread().getName() + " consumer one pc : " + poll.getNum());
                }else {
                    System.out.println("can not get date.");
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
