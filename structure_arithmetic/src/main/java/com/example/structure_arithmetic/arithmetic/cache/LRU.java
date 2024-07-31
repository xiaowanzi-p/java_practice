package com.example.structure_arithmetic.arithmetic.cache;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    //下标、数据节点
    public static Map<Integer,Node> map = new HashMap<>();
    //双向链表
    public static Node head = new Node();
    public static Node tail = new Node();
    //容量
    public static int capacity = 3;
    static {
        head.next = tail;
        tail.pre = head;
    }


    //数据
    public static class Data {
        public int key;
        public int val;
        public Data(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    //双向链表
    public static class Node {
        public Data data;
        public Node pre;
        public Node next;
    }

    public static void main(String[] a) {
        Data data1 = new Data(1, 100);
        Data data2 = new Data(2, 200);
        Data data3 = new Data(3, 300);
        Data data4 = new Data(4, 400);
        put(data1);
        put(data2);
        put(data3);
        System.out.println("结果为：" + get(1).val);
        put(data4);
        System.out.println("结果为: " + get(4).val);
        Data data = get(2);
        if (data == null) {
            System.out.println("结果是空");
        }
    }

    //获取数据
    public static Data get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        //将节点移除，放在链表头部
        removeNode(node);
        addHead(node);
        return node.data;
    }


    public static void put(Data data) {
        //如果大于总容量则触发淘汰
        if (map.size() >= capacity) {
            //淘汰链表末尾的数据
            Node node = removeTail();
            map.remove(node.data.key);
        }
        Node node = new Node();
        node.data = data;
        //放入数据map
        map.put(data.key,node);
        //将节点放入head
        addHead(node);
    }

    private static void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        node.next = null;
        node.pre = null;
    }

    private static void addHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.next = next;
        node.pre = head;
        next.pre = node;
    }

    private static Node removeTail() {
        Node pre = tail.pre;
        Node pre2 = pre.pre;
        pre2.next = tail;
        tail.pre = pre2;
        pre.next = null;
        pre.pre = null;
        return pre;
    }

}
