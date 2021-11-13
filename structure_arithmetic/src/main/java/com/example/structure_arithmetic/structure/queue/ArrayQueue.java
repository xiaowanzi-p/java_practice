package com.example.structure_arithmetic.structure.queue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrayQueue<T> {
    private T[] array;
    private int head = 0;
    private int tail = 0;
    private int capacity;
    private int size = 0;
    private final int TIME = 2;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (T[])new Object[capacity];
    }

    public void push(T t) {
        if (tail == capacity) {
            //扩容
            T[] old = array;
            array = (T[])new Object[capacity*TIME];
            //数据迁移
            for (int i=head; i<capacity; i++) {
                array[i] = old[i];
            }
            //重置capacity
            capacity = capacity*TIME;
        }
        //插入
        array[tail] = t;
        tail++;
    }

    public T pop() {
        if (head == tail) {
            return null;
        }
        T t = array[head];
        head++;
        return t;
    }

}
