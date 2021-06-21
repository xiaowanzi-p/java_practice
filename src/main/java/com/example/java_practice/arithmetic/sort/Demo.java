package com.example.java_practice.arithmetic.sort;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] a) {
        ArrayList<Integer> list = Lists.newArrayList(1, -1, -3, 9, 8, 10, 11, 7, 9);
        System.out.println("原始集合: " + list);
        List<Integer> sortA = CommonSort.sort(list, 1);
        System.out.println("第一排序: " + sortA);
        List<Integer> sortB = CommonSort.sort(list, 2);
        System.out.println("第二排序: " + sortB);
        List<Integer> sortC = CommonSort.sort(list, 3);
        System.out.println("第三排序: " + sortC);
        List<Integer> sortD = CommonSort.sort(list, 4);
        System.out.println("第四排序: " + sortD);
    }
}
