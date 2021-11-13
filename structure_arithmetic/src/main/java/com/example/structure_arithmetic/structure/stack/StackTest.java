package com.example.structure_arithmetic.structure.stack;

public class StackTest {

    public static void main(String[] a) {
        ListStack<Integer> stack = new ListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
