package com.example.java_practice.datastructure.queue;

public class Demo {

    public static void main(String[] args) {
        MyListBlockQueue<Integer> queue = new MyListBlockQueue<>(3);
        try {
            Thread thread = new Thread(()->{
                for (int i=0; i<100; i++) {
                    try {
                        queue.offer(i);
                        System.out.println("Offer 数据:"+i);
                    } catch (Exception e) {

                    }
                }
            });
            thread.start();
            while (true) {
                Integer take = queue.take();
                System.out.println("Take 数据:"+take);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}