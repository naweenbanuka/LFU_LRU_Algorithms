package com.example.demo;

import com.example.demo.storages.Common;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class CacheTest {

    @Test
    public void LRU_Test() {

        //arrange
        int capacity = 5;
        Cache cache = new Cache();
        Common common = cache.setCache(Cache.Strategy.LRU, capacity);

        //actions

        //Storing first value 10 with key (1) in the cache
        common.set(1, 10);

        //Storing second value 20 with key (2) in the cache
        common.set(2, 20);

        //Storing third value 30 with key (3) in the cache
        common.set(3, 30);

        //Storing fourth value 40 with key (4) in the cache
        common.set(4, 40);

        //Storing fifth value 50 with key (5) in the cache
        common.set(5, 50);


        System.out.println("Value for the key: 1 is " +
                common.get(1)); // returns 10 and moved to front
        Assert.assertEquals(10, common.get(1));

        // evicts key 1 and store a key (6) with value 60 in the cache
        common.set(6, 60);

        System.out.println("Value for the key: 2 is " +
                common.get(2)); //  returns -1 (not found)
        Assert.assertEquals(-1, common.get(2));

        //evicts key 3 and store a key (7) with value 70 in the cache
        common.set(7, 70);

        System.out.println("Value for the key: 3 is " +
                common.get(3)); // returns -1 (not found)
        Assert.assertEquals(-1, common.get(3));

        System.out.println("Value for the key: 4 is " +
                common.get(4)); // returns 40 moved to front
        Assert.assertEquals(40, common.get(4));

        System.out.println("Value for the key: 5 is " +
                common.get(5)); // return 50 moved to front
        Assert.assertEquals(50, common.get(5));
    }

    @Test
    public void LFU_Test() {

        //arrange
        int capacity = 5;
        Cache cache = new Cache();
        Common common = cache.setCache(Cache.Strategy.LFU, capacity);

        //actions

        //Storing first value 10 with key (1) in the cache with default frequency.
        common.set(1, 10);

        //Storing second value 20 with key (2) in the cache with default frequency.
        common.set(2, 20);

        //Storing third value 30 with key (3) in the cache with default frequency.
        common.set(3, 30);

        //Storing fourth value 40 with key (4) in the cache with default frequency.
        common.set(4, 40);

        //Storing fifth value 50 with key (5) in the cache with default frequency.
        common.set(5, 50);


        System.out.println("Value for the key: 1 is " +
                common.get(1)); // returns 10 and increased frequency
        Assert.assertEquals(10, common.get(1));

        // evicts key 2 and store a key (6) with value 60 in the cache  with default frequency.
        common.set(6, 60);

        System.out.println("Value for the key: 2 is " +
                common.get(2)); // returns -1 (not found)
        Assert.assertEquals(-1, common.get(2));

        //evicts key 3 and store a key (7) with value 70 in the cache with default frequency.
        common.set(7, 70);

        System.out.println("Value for the key: 3 is " +
                common.get(3)); // returns -1 (not found)
        Assert.assertEquals(-1, common.get(3));

        System.out.println("Value for the key: 4 is " +
                common.get(4)); // returns 40
        Assert.assertEquals(40, common.get(4));

        System.out.println("Value for the key: 5 is " +
                common.get(5)); // return 50
        Assert.assertEquals(50, common.get(5));

    }
}
