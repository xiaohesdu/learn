package com.learn.performance;

import com.alibaba.fastjson.JSON;
import com.learn.mysql.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * @author gonghe.hogan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class PerformanceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static int threadTotal = 20;
    private static int clientTotal = 500;

    @Before
    public  void setUp(){

    }

    @After
    public void tearDown(){

    }

    private void createSingleDevice() {
//      1.  prepare date
        StudentDto studentDto = new StudentDto();
        studentDto.setAge(10);
        studentDto.setSex("male");
        studentDto.setName("name44" + UUID.randomUUID().toString());

//        2. send request
        final String jsonBody = JSON.toJSONString(studentDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
        final HttpEntity<String> requestHttpEntity = new HttpEntity<>(jsonBody, httpHeaders);
        String url = "/student/update/async";
        testRestTemplate.postForObject(url, requestHttpEntity, Void.class);

    }

    private void updateSingleDevice() {
//      1.  prepare date
        StudentDto studentDto = new StudentDto();
        studentDto.setAge(20);
        studentDto.setSex("male");
        studentDto.setName("name55");


//        2. send request
        final String jsonBody = JSON.toJSONString(studentDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
        final HttpEntity<String> requestHttpEntity = new HttpEntity<>(jsonBody, httpHeaders);
        String url = "/student/update/async";
        testRestTemplate.postForObject(url, requestHttpEntity, Void.class);
    }


    private void deleteSingleDeviceFindThenDelete() {
//      1.  prepare date
//        2. send request
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
        final HttpEntity<String> requestHttpEntity = new HttpEntity<>(null, httpHeaders);
        String url = "/students/find/delete";
        testRestTemplate.postForObject(url, requestHttpEntity, Void.class);
    }

    private void deleteSingleDeviceJustDelete() {
//      1.  prepare date
//        2. send request
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
        final HttpEntity<String> requestHttpEntity = new HttpEntity<>(null, httpHeaders);
        String url = "/students/just/delete";
        testRestTemplate.postForObject(url, requestHttpEntity, Void.class);
    }

    @Test
    public void _01_01createCvmDevice() throws InterruptedException {
        /**
         *
         * clientNum :500, threadTotal:50, total execute time: 153s, average single thread execute time:307ms
         *
         */
        final long beginTime = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal; i++){
            semaphore.acquire();
            executorService.execute(() -> {
                createSingleDevice();
                countDownLatch.countDown();

            });
            semaphore.release();
        }
        countDownLatch.await();
        executorService.shutdown();
        final long endTime = System.currentTimeMillis();
        final long executeTime = endTime - beginTime ;
        log.info("createCvmDevice. clientNum :{}, threadTotal:{}, total execute time: {}s, average single thread execute time:{}ms",clientTotal, threadTotal, executeTime/1000, executeTime/clientTotal);
    }


    @Test
    public void _01_02updateCvmDevice() throws InterruptedException {
        /**
         *
         *  clientNum :500, threadTotal:50, total execute time: 158s, average single thread execute time:317ms
         *
         */
        final long beginTime = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal; i++){
            semaphore.acquire();
            executorService.execute(() -> {
                updateSingleDevice();
                countDownLatch.countDown();
            });
            semaphore.release();
        }
        countDownLatch.await();
        executorService.shutdown();
        final long endTime = System.currentTimeMillis();
        final long executeTime = endTime - beginTime ;
        log.info("updateCvmDevice. clientNum :{}, threadTotal:{}, total execute time: {}s, average single thread execute time:{}ms",clientTotal, threadTotal, executeTime/1000, executeTime/clientTotal);
    }



    @Test
    public void _01_03deleteCvmDevice() throws InterruptedException {
        /**
         *
         *  clientNum :500, threadTotal:50, total execute time: 158s, average single thread execute time:317ms
         *
         */
        final long beginTime = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal; i++){
            semaphore.acquire();
            executorService.execute(() -> {
//                deleteSingleDeviceFindThenDelete();
                deleteSingleDeviceJustDelete();
                countDownLatch.countDown();

            });
            semaphore.release();
        }
        countDownLatch.await();
        executorService.shutdown();
        final long endTime = System.currentTimeMillis();
        final long executeTime = endTime - beginTime ;
        log.info("deleteCvmDevice. clientNum :{}, threadTotal:{}, total execute time: {}s, average single thread execute time:{}ms",clientTotal, threadTotal, executeTime/1000, executeTime/clientTotal);
    }

}
