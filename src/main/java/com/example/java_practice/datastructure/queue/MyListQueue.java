package com.example.java_practice.datastructure.queue;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.datastructure.list.MyLinkList;

public class MyListQueue<T> {

    private MyLinkList<T> list = new MyLinkList();

    private int head = 0;
    private int tail = 0;
    private int size = 0;

    //入队
    public void push(T t) {
        list.add(t);
        size++;
        tail++;
    }

    //出队
    public T pop() {
        //头尾指针下标相等则说明队列没有元素了
        if (head == tail) {
            return null;
        }

        T element = list.get(head);
        head++;
        size--;
        return element;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyListQueue[");
        if (head == tail) {
            builder.append("]");
            return builder.toString();
        }

        for (int i=head; i<tail; i++) {
            String string = JSONObject.toJSONString(list.get(i));
            if (i == tail-1) {
                builder.append(string);
            } else {
                builder.append(string+",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
