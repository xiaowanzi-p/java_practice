package com.example.java_practice.datastructure.list;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

public class MyLinkList<T> {

    //尾结点
    private Node<T> tail;
    //头结点
    private Node<T> head;
    //链表长度
    private int size = 0;

    //初始化头结点为哨兵节点
    public MyLinkList() {
        Node<T> node = new Node<>();
        node.setSentinel(true);
        head = node;
        tail = head;
    }

    //增加元素
    public boolean add(T element) {
        Node<T> node = new Node<>();
        node.setElement(element);
        node.setPre(tail);
        tail.setNext(node);
        //重置尾结点
        tail = node;
        //长度加1
        size++;
        return true;
    }

    //根据下标获取元素,从0开始
    public T get(int index) {
        if (index < 0 || index > size-1 || size == 0) {
            return null;
        }
        if (index == 0) {
            return head.getNext().element;
        }
        if (index == size-1) {
            return tail.getElement();
        }
        int count = 0;
        Node<T> node = head.getNext();
        while (node != null) {
            Node<T> next = node.getNext();
            node = next;
            count++;
            if (index == count) {
                break;
            }
        }
        return node.getElement();
    }

    //根据下标移除元素
    public T removeByIndex(int index) {
        if (index < 0 || index > size-1 || size == 0) {
            return null;
        }
        //头结点
        if (index == 0) {
            Node<T> current = head.getNext();
            Node<T> next = current.getNext();
            head.setNext(next);
            if (next != null) {
                next.setPre(head);
            } else {
                tail = head;
            }
            size--;
            return current.getElement();
        }
        //尾结点
        if (index == size-1) {
            Node<T> node = tail;
            Node<T> pre = tail.getPre();
            pre.setNext(null);
            tail = pre;
            size--;
            return node.getElement();
        }
        //找到对应下标元素
        int count = 0;
        Node<T> node = head.getNext();
        Node<T> pre = head;
        Node<T> next = node.getNext();
        while (node != null) {
            Node<T> current = node.getNext();
            node = current;
            pre = current.getPre();
            next = current.getNext();
            count++;
            if (count == index) {
                break;
            }
            if (next == null) {
                break;
            }
        }
        //移除下标元素
        if (pre != null) {
            pre.setNext(next);
        }
        if (next != null) {
            next.setPre(pre);
        }
        if (next == null) {
            //重置尾结点
            tail = pre;
        }
        //重置size
        size--;

        return node.getElement();
    }


    //根据数据移除元素
    public void removeByElement(T element) {
        if (element == null || size == 0) {
            return;
        }

        //迭代寻找相等元素然后移除
        Node<T> node = head.getNext();
        while (node != null) {
            boolean flag = node.getElement().equals(element);
            if (flag) {
                Node<T> pre = node.getPre();
                Node<T> next = node.getNext();
                if (pre != null) {
                    pre.setNext(next);
                }
                if (next != null) {
                    next.setPre(pre);
                }
                if (next == null) {
                    //重置尾结点
                    tail = pre;
                }
                node = next;
                size--;
            } else {
                node = node.getNext();
            }
        }
    }

    /**
     * 根据下标设置数据
     *
     * @param index
     * @return
     */
    public boolean set(int index, T t) {
        if (index < 0 || t == null) {
            return false;
        }
        //找出对应下标的节点
        int count = 0;
        Node<T> node = head.next;
        Node<T> pre = head;
        boolean flag = false;
        while (node != null) {
            if (index == count) {
                node.setElement(t);
                flag = true;
                break;
            }
            count++;
            pre = node;
            node = node.getNext();
        }

        if (node == null && index == count && !flag) {
            //增加新节点
            add(t);
            flag = true;
        }
        return flag;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyLinkList[");
        if (size != 0) {
            Node<T> node = head.getNext();
            while (node != null) {
                String s = JSONObject.toJSONString(node.getElement());
                node = node.getNext();
                if (node != null) {
                    builder.append(s+",");
                } else {
                    builder.append(s);
                }
            }
        }
        builder.append("]");
        builder.append(" Size:"+size);
        return builder.toString();
    }

    @Getter
    @Setter
    private final class Node<T> {
        private boolean sentinel = false;
        private T element;
        private Node<T> pre;
        private Node<T> next;
    }
}
