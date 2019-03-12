package com.learn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghe.hogan
 */
@RestController
public class RedisExampleController {

    @Autowired
    private RedisExampleService redisExampleService;

    @GetMapping("/redis/test")
    public String testRedis() {
        return redisExampleService.testRedis();
    }
}
