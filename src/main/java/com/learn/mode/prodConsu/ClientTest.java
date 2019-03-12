package com.learn.mode.prodConsu;

import java.util.concurrent.*;

/**
 * @author gonghe.hogan
 */

public class ClientTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PcData> blockingQueue = new LinkedBlockingQueue<>();
        Product1 product1 = new Product1(blockingQueue);
        Product1 product11 = new Product1(blockingQueue);
        Product1 product111 = new Product1(blockingQueue);

        Consumer1 consumer1 = new Consumer1(blockingQueue);
        Consumer1 consumer11 = new Consumer1(blockingQueue);
        Consumer1 consumer111 = new Consumer1(blockingQueue);

        ExecutorService executorService = new ThreadPoolExecutor(10,10,1,TimeUnit.MINUTES,new LinkedBlockingDeque<>());


        executorService.execute(consumer1);
        executorService.execute(consumer11);
        executorService.execute(consumer111);

        TimeUnit.MILLISECONDS.sleep(1500);
        executorService.execute(product1);
        executorService.execute(product11);
        executorService.execute(product111);

        TimeUnit.SECONDS.sleep(3);
        product1.stop();
        product11.stop();
    }

}
