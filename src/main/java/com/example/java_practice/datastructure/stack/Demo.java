package com.example.java_practice.datastructure.stack;

public class Demo {

    public static void main(String[] a) {
        MyArrayStack<String> stack = new MyArrayStack<>(3);
        System.out.println(stack.push("1"));
        System.out.println(stack.push("2"));
        System.out.println(stack.push("3"));
        System.out.println(stack.push("4"));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
