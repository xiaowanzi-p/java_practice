package com.example.structure_arithmetic.arithmetic.sort;

import java.util.List;

public class MaoPaoSort {


    public static <T> void sort(List<Comparable<T>> list) {
        int size = list.size();
        for (int i=0; i<size; i++) {
            for (int j=i; j<size-1; j++) {
                Comparable c1 = list.get(j);
                Comparable c2 = list.get(j+1);
                if (c1.compareTo(c2) > 0) {
                    swap(list,j,j+1);
                }
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
