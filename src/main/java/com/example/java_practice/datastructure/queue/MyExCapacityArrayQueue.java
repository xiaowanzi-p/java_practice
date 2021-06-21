package com.example.java_practice.datastructure.queue;

import com.alibaba.fastjson.JSONObject;

public class MyExCapacityArrayQueue<T> {

    private int head = 0;
    private int tail = 0;
    private int capacity = 0;
    private int size = 0;
    private T[] array = null;
    private final static int TIMES = 2;

    public MyExCapacityArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public void push(T t) {
        //判断是否需要扩容
        if (tail == capacity) {
            T[] old = array;
            array = (T[]) new Object[capacity*TIMES];
            //拷贝旧数据
            if (head != tail) {
                for (int i=head; i<capacity; i++) {
                    array[i] = old[i];
                }
            }
        }
        array[tail] = t;
        tail++;
        size++;
    }

    public T pop() {
        //判断是否已经没有数据
        if (tail == head) {
            return null;
        }
        T t = array[head];
        head++;
        size--;
        return t;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size=");
        builder.append(size);
        builder.append(" [");
        if (size == 0) {
            builder.append("]");
            return builder.toString();
        }
        for (int i=head; i<tail; i++) {
            T t = array[i];
            builder.append(JSONObject.toJSONString(t));
            if (i != tail-1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
