package com.example.structure_arithmetic.structure.queue;

import com.alibaba.fastjson.JSONObject;
import com.example.structure_arithmetic.structure.list.DemoList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListQueue<T> {

    //头结点-哨兵节点
    private Node<T> head;
    //尾结点
    private Node<T> tail;


    public ListQueue() {
        head = new Node<>();
        tail = head;
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
        tail.next = node;
        tail = node;
    }

    public T pop() {
        Node<T> target = head.next;
        if (target == null) {
            return null;
        }
        Node<T> next = target.next;
        if (next == null) {
            head.setNext(null);
        } else {
            head.setNext(next);
        }
        return target.getData();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> node = head.next;
        while (node != null) {
            Object data = node.data;
            node = node.next;
            if (node != null) {
                builder.append(JSONObject.toJSONString(data) + ",");
            } else {
                builder.append(JSONObject.toJSONString(data));
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
