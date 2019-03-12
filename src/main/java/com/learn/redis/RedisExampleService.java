package com.learn.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghe.hogan
 */
@Service
@Slf4j
public class RedisExampleService {


    @Cacheable
    public String testRedis(){
        log.info("refresh redis.");
        return "-1";
    }
}
