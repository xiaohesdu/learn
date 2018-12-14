package com.learn.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class BloomFilterExample2 {

    private static final int size = 1000000;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    private static void initFilter(){
        for (int i = 0; i < size; i++){
            bloomFilter.put(i);
        }
    }



    public static void main(String[] args) {
        initFilter();
        Random random = new Random();
        final int randomInt = random.nextInt(size);
        final long beginTime = System.nanoTime();
        if (bloomFilter.mightContain(randomInt)){
            System.out.println("命中缓存");
        }
        log.info("bloom filter spend time : {} ns", System.nanoTime() - beginTime);

        int errorFilterCount = 0;
        int totalCount = 10000;
        for (int i = 10000; i < 20000; i++){
            final int exceedSize = i + size;
            if (bloomFilter.mightContain(exceedSize)){
                errorFilterCount++;
            }
        }
        log.info("total {}, error filter {}",totalCount, errorFilterCount );
    }
}
