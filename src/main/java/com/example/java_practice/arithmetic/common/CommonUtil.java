package com.example.java_practice.arithmetic.common;


public class CommonUtil {

    /**
     * 交换数组中的两个元素
     */
    public static void swap(int[] array, int source, int target) {
        int temp = array[target];
        array[target] = array[source];
        array[source] = temp;
    }
}
