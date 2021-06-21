package com.example.java_practice.datastructure.list;

public class TestDemo {

    public static void main(String[] args) {
        MyLinkList<String> list = new MyLinkList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("d");
        list.add("d");
        list.add("e");
        System.out.println(list.toString());
        String value = list.get(3);
        System.out.println(value);
        list.removeByElement("d");
        System.out.println(list.toString());
    }
}
