package com.learn.kafka;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghe.hogan
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/send")
    public void produceData(){
        String topic = "proxy-event";
        String message = "cpu_load_short,sourceId=3,sourceName=testSource,host=server01,region=us-west value=0.64 1434055562000000000";
        kafkaTemplate.send(topic, message);
    }
}
