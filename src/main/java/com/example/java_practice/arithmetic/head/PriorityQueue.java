package com.example.java_practice.arithmetic.head;

import com.example.java_practice.datastructure.head.Head;
import lombok.Getter;
import lombok.Setter;

/**
 * 优先级队列
 */
@Getter
@Setter
public class PriorityQueue<T extends Comparable<? super T>> {

    private Head<T> head = new Head<>(50,true);

    //入队
    public void push(T t) {
        head.add(t);
    }

    //出队
    public T pop() {
        return head.removeTop();
    }
}
