package com.example.demo;

import com.example.demo.storages.Common;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class CacheTest {

    @Test
    public void LRU_Test() {

        //arrange
        int capacity = 3; // This is the cache memory capasity
        Cache cache = new Cache();
        Common common = cache.setCache(Cache.Strategy.LRU, capacity);

        //action
        common.set(2, 2);
        common.set(1, 1);

        System.out.println(common.get(2));
        System.out.println(common.get(1));
        System.out.println(common.get(2));

        common.set(3, 3);
        common.set(4, 4);

        System.out.println(common.get(3));
        System.out.println(common.get(2));
        System.out.println(common.get(1));
        System.out.println(common.get(4));



        //assertion
        Assert.assertEquals(3, common.get(3));
    }

    @Test
    public void LFU_Test() {

        //arrange
        int capacity = 3;
        Cache cache = new Cache();
        Common common = cache.setCache(Cache.Strategy.LFU, capacity);

        //action
        common.set(2, 2);
        common.set(1, 1);

        System.out.println(common.get(2));
        System.out.println(common.get(1));
        System.out.println(common.get(2));

        common.set(3, 3);
        common.set(4, 4);

        System.out.println(common.get(3));
        System.out.println(common.get(2));
        System.out.println(common.get(1));
        System.out.println(common.get(4));



        //assertion
        Assert.assertEquals(-1, common.get(3));
    }
}
