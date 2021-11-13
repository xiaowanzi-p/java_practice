package com.example.structure_arithmetic.structure.stack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListStack<T> {

    private Node<T> head;
    private Node<T> stack;

    public ListStack() {
        head = new Node<>();
        stack = head;
    }

    @Getter
    @Setter
    public class Node<T> {
        private Node<T> pre;
        private Node<T> next;
        private T data;
    }

    public void push(T t) {
        Node<T> node = new Node<>();
        node.setData(t);
        stack.setNext(node);
        node.setPre(stack);
        stack = node;
    }


    public T pop() {
        if (stack.pre == null) {
            return null;
        }
        Node<T> pre = stack.pre;
        T data = stack.getData();
        stack = pre;
        return data;
    }
}
