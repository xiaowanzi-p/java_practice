package com.example.structure_arithmetic.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MaoPaoSort {

    //test
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




    public static void main(String[] a) {
        int[] array = {7, 9, 3, 6, 8};

        for (int i=0; i<array.length; i++) {
            boolean flag = true;
            for (int j=0; j< array.length-i-1; j++) {
                int d1 = array[j];
                int d2 = array[j+1];
                if (d1 > d2) {
                    array[j] = d2;
                    array[j+1] = d1;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }

        System.out.println(JSONObject.toJSONString(array));
    }
}
