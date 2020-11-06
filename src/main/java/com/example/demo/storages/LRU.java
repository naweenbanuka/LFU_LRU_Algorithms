package com.example.demo.storages;

import java.util.Hashtable;

public class LRU implements Common{

    private class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private Hashtable<Integer, Node> hs = new Hashtable<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    // @param capacity, an integer
    public LRU(int capacity) {

        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    @Override
    public int get(int key) {

        if (!hs.containsKey(key)) {
            return -1;
        }

        // remove current node
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // move current node to tail
        move_to_tail(current);

        return hs.get(key).value;

    }

    @Override
    public void set(int key, int value) {

        if (get(key) != -1) {
            hs.get(key).value = value;
            return;
        }

        if (hs.size() == capacity) {
            hs.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);
        hs.put(key, insert);
        move_to_tail(insert);
    }

    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }

}