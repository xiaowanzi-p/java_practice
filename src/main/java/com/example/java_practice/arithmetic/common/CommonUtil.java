package com.example.java_practice.arithmetic.common;


import algorithm.practice.linklist.SingleListNode;

public class CommonUtil {

    /**
     * 交换数组中的两个元素
     */
    public static void swap(int[] array, int source, int target) {
        int temp = array[target];
        array[target] = array[source];
        array[source] = temp;
    }

    public static void swap(char[] array, int source, int target) {
        char temp = array[target];
        array[target] = array[source];
        array[source] = temp;
    }


    /**
     * 获取随机数组
     */
    public static int[] randomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength);
        int[] ints = new int[length];
        for (int i=0; i<length; i++) {
            int value = (int) (Math.random() * maxValue);
            ints[i] = value;
        }
        return ints;
    }


    public static void foreach(SingleListNode head) {
        SingleListNode current = head;
        StringBuilder builder = new StringBuilder();
        while (current != null) {
            builder.append(current.getData());
            current = current.getNext();
        }
        System.out.println(builder.toString());
    }
}
