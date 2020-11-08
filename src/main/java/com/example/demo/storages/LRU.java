package com.example.demo.storages;

import java.util.Hashtable;

public class LRU implements Common {

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
    private Hashtable<Integer, Node> index = new Hashtable<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    // @param capacity
    public LRU(int capacity) {

        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    @Override
    public int get(int key) {

        if (index.containsKey(key)) {
            Node node = index.get(key);
            move_to_head(node);
            return node.value;
        }

        return -1;
    }

    @Override
    public void set(int key, int value) {

        if (get(key) != -1) {
            index.get(key).value = value;
            Node moveNode = index.get(key);
            move_to_head(moveNode);
        }

        Node insert = new Node(key, value);
        insert.next = head.next;
        insert.prev = head;
        head.next.prev = insert;
        head.next = insert;

        index.put(key,insert);

        if (index.size() > capacity) {
            removeTail();
        }

    }

    private void move_to_head(Node current) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.next = head.next;
        current.prev = head;
        head.next.prev = current;
        head.next = current;
    }

    private void removeTail() {
        Node toRemove = tail.prev;
        toRemove.prev.next = tail;
        tail.prev = toRemove.prev;
        toRemove.prev = null;
        toRemove.next = null;
        index.remove(toRemove.key);
    }


}