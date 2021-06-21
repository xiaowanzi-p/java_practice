package com.example.java_practice.datastructure.queue;

import com.alibaba.fastjson.JSONObject;

public class MyCycleArrayQueue<T> {

    private T[] array;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int capacity;

    public MyCycleArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }


    public boolean push(T t) {
        //队列已满
        if (size == capacity) {
            return false;
        }
        array[tail] = t;
        tail++;
        size++;
        if (tail == capacity) {
            tail = 0;
        }
        return true;
    }


    public T pop() {
        if (size == 0) {
            return null;
        }
        T t = array[head];
        array[head] = null;
        head++;
        size--;
        if (head == capacity) {
            head = 0;
        }
        return t;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyCycleArrayQueue Size=");
        builder.append(size);
        builder.append(" [");
        if (size == 0) {
            builder.append("]");
            return builder.toString();
        }

        int count = 0;
        int index = head;
        while (count != size) {
            T t = array[index];
            builder.append(JSONObject.toJSONString(t));
            count++;
            index++;
            if (index == capacity) {
                index = 0;
            }
            if (count != size) {
                builder.append(",");
            }
        }

        builder.append("]");
        return builder.toString();
    }
}
