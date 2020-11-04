package com.example.demo;

import java.util.Hashtable;
import java.util.LinkedList;

public class LRU {

    static Hashtable<Integer, String> integerIntegerHashtable = new Hashtable(3);
    static LinkedList<String> linkedList = new LinkedList();

    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public String get(int key) {
        String result = "Empty";
        String s = integerIntegerHashtable.get(key);
        if (s != null) {
            result = s;
            linkedList.remove(result);
            linkedList.addFirst(result);
            System.out.println("Change Order");
        }
        return result;
    }

    public void put(int key, String value) {
        String s = integerIntegerHashtable.get(key);
        if (s != null) {
            linkedList.addFirst(value);
            System.out.println("add " + value);
        } else {
            if (integerIntegerHashtable.size() == capacity) {
                integerIntegerHashtable.remove(key);
                linkedList.removeLast();
                System.out.println("Remove Least used value");
            }

            if (linkedList.contains(value)) {
                integerIntegerHashtable.put(key, value);
                String last = linkedList.getLast();
                linkedList.addFirst(last);
                linkedList.addLast(value);
                System.out.println("Change Order");
            } else {
                integerIntegerHashtable.put(key, value);
                linkedList.addFirst(value);
                System.out.println("add " + value);
            }

        }
    }
}
