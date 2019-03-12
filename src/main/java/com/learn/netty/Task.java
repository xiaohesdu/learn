package com.learn.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class Task implements TimerTask {

    private final long taskId;
    private final int duration;
    private final TimeUnit timeUnit;
    private final HashedWheelTimer timer;

    public Task(long taskId, int duration, TimeUnit timeUnit, HashedWheelTimer timer) {
        this.taskId = taskId;
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.timer = timer;
    }


    @Override
    public void run(Timeout timeout) throws Exception {
        log.info("task {} execute.", taskId);
        timer.newTimeout(this, duration, timeUnit);
    }
}
