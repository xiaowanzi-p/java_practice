package com.example.structure_arithmetic.arithmetic.sort;

import java.util.List;


public class MaoPaoSort {

    public static void sort(List list) {
        int size = list.size();
        for (int i=0; i<size; i++) {
            System.out.println("循环了第"+(i+1)+"次");
            boolean flag = true;
            for (int j=0; j<size-i-1; j++) {
                Comparable c1 = (Comparable)list.get(j);
                Comparable c2 = (Comparable)list.get(j+1);
                if (c1.compareTo(c2) > 0) {
                    flag = false;
                    swap(list,j,j+1);
                }
            }
            if (flag) {
                return;
            }
        }
    }

    private static void swap(List list, int a, int b) {
        Object o1 = list.get(a);
        Object o2 = list.get(b);
        list.set(b,o1);
        list.set(a,o2);
    }
}
