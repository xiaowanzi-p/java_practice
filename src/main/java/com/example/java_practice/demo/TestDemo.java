package com.example.java_practice.demo;

import java.util.IdentityHashMap;

public class TestDemo {

    static {
        System.out.println("TestDemo开始加载");
    }

    public static void main(String[] args) {
        System.out.println("enter TestDemo main method");
        while (true) {
            try {
                demo();
                Thread.sleep(3000L);
            } catch (Exception e) {

            }
        }
    }

    private static void demo() {
        System.out.println("result is 100");
    }
}
