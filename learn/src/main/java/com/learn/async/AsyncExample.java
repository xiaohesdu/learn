package com.learn.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */
@RestController
@Slf4j
public class AsyncExample {

    @GetMapping("/async")
    public String testMVCAsync() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3L);
        return "ok.";
    }
}
