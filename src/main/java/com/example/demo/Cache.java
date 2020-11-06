package com.example.demo;

import com.example.demo.storages.Common;
import com.example.demo.storages.LFU;
import com.example.demo.storages.LRU;

public class Cache {

    public enum Strategy {
        LRU,
        LFU
    }

    public Cache() {
    }

    public Common setCache(Strategy strategy, int capacity) {
        Common cacheMethod = null;
        switch (strategy) {
            case LFU:
                cacheMethod = new LFU(capacity);
                break;

            case LRU:
                cacheMethod = new LRU(capacity);
                break;
        }
        return cacheMethod;
    }

}