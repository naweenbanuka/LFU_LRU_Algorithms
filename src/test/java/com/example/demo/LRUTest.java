package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

public class LRUTest {

    @Test
    public void LRU_Test() {
        //arrange
        int capasity = 3;
        LRU lru = new LRU(capasity);

        //action
        lru.put(1, "1");
        lru.put(2, "2");
        lru.put(3, "3");
        String s = lru.get(1);

        Assert.assertEquals("1",s);
    }
}
