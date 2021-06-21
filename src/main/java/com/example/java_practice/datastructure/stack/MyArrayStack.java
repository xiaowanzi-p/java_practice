package com.example.java_practice.datastructure.stack;

public class MyArrayStack<T> {
    private T[] array;
    private int point = 0;
    private int size = 0;
    private int capacity;

    public MyArrayStack(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }


    /**
     * 压栈
     *
     * @param t
     */
    public boolean push(T t) {
        if (size == capacity) {
            return false;
        }
        array[point] = t;
        point++;
        size++;
        if (point == capacity) {
            point = capacity - 1;
        }
        return true;
    }


    /**
     * 弹栈
     *
     * @return
     */
    public T pop() {
        if (size == 0) {
            return null;
        }
        T t = array[point];
        array[point] = null;
        point--;
        size--;
        if (point < 0) {
            point = 0;
        }
        return t;
    }
}
