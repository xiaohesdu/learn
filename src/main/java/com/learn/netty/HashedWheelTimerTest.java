package com.learn.netty;

import io.netty.util.HashedWheelTimer;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */

public class HashedWheelTimerTest {

    private HashedWheelTimer timer ;

    public HashedWheelTimerTest(int tickDuration, TimeUnit timeUnit, int ticksPerWheel){
        timer = new HashedWheelTimer(tickDuration, timeUnit, ticksPerWheel);
    }

    public void testHashedWheelTimer(int max){
        for (int i = 0; i < max; i++){
            Task task = new Task(i, 60, TimeUnit.SECONDS, timer);
            timer.newTimeout(task, getRandom(0, 300), TimeUnit.SECONDS);
        }
    }

    private int getRandom(int min, int max){
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max) % (max - min + 1) + min;
    }

    public static void main(String[] args) {
        HashedWheelTimerTest test = new HashedWheelTimerTest(1, TimeUnit.SECONDS, 300);
        test.testHashedWheelTimer(1);
    }


}
