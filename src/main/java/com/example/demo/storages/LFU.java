package com.example.demo.storages;

import java.util.*;

public class LFU implements Common{

    private int capacity;
    //Save cache access frequency and time
    private final Map<Integer, HitRate> cache = new HashMap();
    //Save cached KV
    private final Map<Integer, Integer> KV = new HashMap();


    public LFU(int capacity) {
        this.capacity = capacity;
    }


    @Override
    public void set(int key, int value) {
        Integer v = KV.get(key);
        if (v == null) {
            if (cache.size() == capacity) {
                Integer k = getKickedKey();
                KV.remove(k);
                cache.remove(k);
            }
            cache.put(key, new HitRate(key, 1, System.nanoTime()));
        } else { //If the keys are the same, only increase the frequency, update time, and do not replace
            HitRate hitRate = cache.get(key);
            hitRate.hitCount += 1;
            hitRate.lastTime = System.nanoTime();
        }
        KV.put(key, value);
    }

    @Override
    public int get(int key) {
        Integer v = KV.get(key);
        if (v != null) {
            HitRate hitRate = cache.get(key);
            hitRate.hitCount += 1;
            hitRate.lastTime = System.nanoTime();
            return v;
        }
        return -1;
    }

    private Integer getKickedKey() {
        HitRate min = Collections.min(cache.values());
        return min.key;
    }

    class HitRate implements Comparable<HitRate> {
        Integer key;
        Integer hitCount;
        Long lastTime;

        public HitRate(Integer key, Integer hitCount, Long lastTime) {
            this.key = key;
            this.hitCount = hitCount;
            this.lastTime = lastTime;
        }

        public int compareTo(HitRate o) {
            int hr = hitCount.compareTo(o.hitCount);
            return hr != 0 ? hr : lastTime.compareTo(o.lastTime);
        }
    }

}