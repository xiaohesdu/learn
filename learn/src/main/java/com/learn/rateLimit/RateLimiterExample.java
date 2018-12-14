package com.learn.rateLimit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class RateLimiterExample {

    RateLimiter rateLimiter = RateLimiter.create(10, 2, TimeUnit.SECONDS);

    private static void handle(int i){
        log.info("{}", i);
    }

    public static void main(String[] args) {
        RateLimiterExample rateLimiterExample = new RateLimiterExample();
        RateLimiter rateLimiter = rateLimiterExample.rateLimiter;

        for (int i = 0; i< 100; i++){
            rateLimiter.acquire();
            handle(i);
        }
    }
}
