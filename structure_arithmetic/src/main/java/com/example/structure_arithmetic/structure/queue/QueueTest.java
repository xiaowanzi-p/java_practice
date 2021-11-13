package com.example.structure_arithmetic.structure.queue;

public class QueueTest {

    public static void main(String[] a) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
