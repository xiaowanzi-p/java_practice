package com.example.java_practice.datastructure.queue;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.datastructure.list.MyLinkList;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyListBlockQueue<T> {

    private MyLinkList<T> list = new MyLinkList();
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int capacity;

    private ReentrantLock offer = new ReentrantLock();
    private ReentrantLock take = new ReentrantLock();
    private Condition offerCondition = offer.newCondition();
    private Condition takeCondition = take.newCondition();

    public MyListBlockQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T t) throws InterruptedException {
        try {
            offer.lock();
            //队列已满
            if (size == capacity) {
                offerCondition.await();
            }
            list.set(tail,t);
            size++;
            tail++;
            if (tail == capacity) {
                tail = 0;
            }
        } finally {
            offer.unlock();
        }

        //唤醒take线程获取数据
        try {
            take.lock();
            //再次判断确保队列中有数据
            if (size != 0) {
                takeCondition.signal();
            }
        } finally {
            take.unlock();
        }
    }

    public T take() throws InterruptedException {
        T t = null;
        try {
            take.lock();
            //队列已空
            if (size == 0) {
                takeCondition.await();
            }
            t = list.get(head);
            head++;
            size--;
            if (head == capacity) {
                head = 0;
            }
        } finally {
            take.unlock();
        }
        //唤醒offer线程
        try {
            offer.lock();
            //确保队列没满
            if (size < capacity) {
                offerCondition.signal();
            }
        } finally {
            offer.unlock();
        }
        return t;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyListBlockQueue Size=");
        builder.append(size);
        builder.append(" [");
        if (size == 0) {
            builder.append("]");
            return builder.toString();
        }

        int count = 0;
        int index = head;
        while (count != size) {
            T t = list.get(index);
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
