package com.example.java_practice.arithmetic.sort;

import java.util.List;

/**
 * 选择排序
 */
public class SelectSort {

    /**
     * 数据分为有序区间、无序区间。遍历无序区间每次从无序区间中选出最小的值放入有序区间的末尾，重复步骤直至全部变成有序区间。
     * 是一个无序区间渐渐向有序区间转化的过程
     *
     * @param list
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        int size = list.size();
        //遍历无序区间
        for (int i=1; i<size; i++) {

        }
    }
}
