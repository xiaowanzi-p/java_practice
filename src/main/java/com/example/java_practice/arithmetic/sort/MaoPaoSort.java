package com.example.java_practice.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 冒泡排序
 */
public class MaoPaoSort {

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        int size = list.size();

        //循环N次，执行N次排序操作
        for (int i=0; i<size; i++) {
            boolean flag = false;
            System.out.println("比较第"+(i+1)+"次");
            //执行排序操作，只比较相邻的元素，每次确定一个位置后都可以减少一次比较
            for (int j=0; j<size-i-1; j++) {
                System.out.println("比较者为:"+j+"和"+(j+1));
                T t1 = list.get(j);
                T t2 = list.get(j + 1);
                Comparable c1 = (Comparable)t1;
                Comparable c2 = (Comparable)t2;
                int value = c1.compareTo(c2);
                //如果相邻元素大小不一致，则交换位置
                if (value > 0) {
                    list.set(j,t2);
                    list.set(j+1,t1);
                    flag = true;
                }
            }
            //如果这一次排序操作中没有发生一次交换动作，则提前跳出循环
            if (!flag) {
                break;
            }
        }
    }


    public static void main(String[] a) {
        int[] array = {3, 6, 1, 9, 7, 8};
        int length = array.length;
        for (int i=0; i<length; i++) {
            for (int j=0; j<length-1-i; j++) {
                int d1 = array[j];
                int d2 = array[j+1];
                if (d1 > d2) {
                    array[j+1] = d1;
                    array[j] = d2;
                }
            }
        }
        System.out.println(JSONObject.toJSONString(array));
    }
}
