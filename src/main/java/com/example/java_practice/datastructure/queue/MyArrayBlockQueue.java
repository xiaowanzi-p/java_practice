package com.example.java_practice.datastructure.queue;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockQueue<T> {

    private T[] array;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int capacity;


    private ReentrantLock lock = new ReentrantLock();
    private Condition offer = lock.newCondition();
    private Condition take = lock.newCondition();

    public MyArrayBlockQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }


    /**
     * 阻塞向队列push数据
     *
     * @param t
     */
    public void offer(T t) throws InterruptedException {
        try {
         lock.lock();
         //是否队列已满，并沉睡
            if (size == capacity) {
                offer.await();
            }
            //添加数据
            array[tail] = t;
            tail++;
            size++;
            //重置尾指针
            if (tail == capacity) {
                tail = 0;
            }
            //唤醒take线程
            take.signal();
        } finally {
         lock.unlock();
        }
    }


    /**
     * 阻塞获取队列数据
     */
    public T take() throws InterruptedException {
        T t = null;
        try {
           lock.lock();
           //判断队列是否已经空, 沉睡
            if (size == 0) {
                take.await();
            }
            //取出数据
            t = array[head];
            array[head] = null;
            size--;
            head++;
            //重置头指针
            if (head == capacity) {
                head = 0;
            }
            offer.signal();
        } finally {
            lock.unlock();
        }
        return t;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyArrayBlockQueue Size=");
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
