package com.example.structure_arithmetic.structure.list;


public class ListTest {

    public static void main(String[] a) {
        DemoList<Integer> list = new DemoList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        boolean remove2 = list.remove(6);
        System.out.println(remove2);
        boolean remove = list.remove(2);
        System.out.println(remove);
        System.out.println(list);
        boolean remove1 = list.remove(1);
        System.out.println(remove1);
        System.out.println(list);
        list.remove(3);
        list.remove(4);
        System.out.println(list);

    }
}
