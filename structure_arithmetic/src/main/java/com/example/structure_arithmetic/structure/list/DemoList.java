package com.example.structure_arithmetic.structure.list;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoList<T> {

    //头节点-哨兵节点
    private Node<T> head;
    //尾节点
    private Node<T> tail;

    public DemoList() {
        head = new Node();
        tail = head;
    }

    @Getter
    @Setter
    public class Node<T> {
        private Node<T> pre;
        private Node<T> next;
        private T data;
    }

    public void add(T t) {
        //增加的节点
        Node<T> node = new Node<>();
        node.setData(t);
        node.setPre(tail);
        tail.setNext(node);
        //更新尾结点
        tail = node;
    }

    public boolean remove(T t) {
        //找到对应的节点
        Node target = head.next;
        while (target != null) {
            Object data = target.getData();
            if (data.equals(t)) {
                Node pre = target.pre;
                Node next = target.next;
                if (next != null) {
                    pre.next = next;
                    next.pre = pre;
                } else {
                    pre.next = null;
                }
                return true;
            } else {
                target = target.next;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node node = head.next;
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
