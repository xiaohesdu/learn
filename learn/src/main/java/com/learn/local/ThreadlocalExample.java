package com.learn.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author gonghe.hogan
 */
@RestController
public class ThreadlocalExample {

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    @GetMapping("/test/local/{num}")
    public int getAddNum(@PathVariable(value = "num") int num){
        try {
            final int rawNum = getNum();
            TimeUnit.SECONDS.sleep(5L);
            setNum(rawNum + num);
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getNum();
    }
}

