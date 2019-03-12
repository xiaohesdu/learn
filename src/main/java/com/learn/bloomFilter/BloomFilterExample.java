package com.learn.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class BloomFilterExample {

    private static final int size = 10000000;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);
    private static List<Integer> list = new ArrayList<>(size);
    private static Set<Integer> linkedHashSet = new LinkedHashSet<Integer>(size);

    private static void initFilter(){
        for (int i = 0; i < size; i++){
            bloomFilter.put(i);
        }
    }
    private static void initList(){
        for (int i = 0; i < size; i++){
            list.add(i);
        }
    }
    private static void initTreeSet(){
        for (int i = 0; i < size; i++){
            linkedHashSet.add(i);
        }
    }


    public static void main(String[] args) {
        initFilter();
        initList();
        initTreeSet();
        Random random = new Random();
        final int randomInt = random.nextInt(size);
        final long beginTime = System.nanoTime();
        if (bloomFilter.mightContain(randomInt)){
            System.out.println("命中缓存");
        }
        log.info("bloom filter spend time : {} ns", System.nanoTime() - beginTime);

        final long beginTime2 = System.nanoTime();
        if (list.contains(randomInt)){
            System.out.println("命中缓存");
        }
        log.info("list spend time : {} ns", System.nanoTime() - beginTime2);

        final long beginTime3 = System.nanoTime();
        if (linkedHashSet.contains(randomInt)){
            System.out.println("命中缓存");
        }
        log.info("tree set spend time : {} ns", System.nanoTime() - beginTime3);
    }
}
