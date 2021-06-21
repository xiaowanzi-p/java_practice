package com.example.java_practice.arithmetic.sort;

import java.util.List;

/**
 * 插入排序
 */
public class InsertSort {

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        int size = list.size();
        //遍历无序区间
        for (int i=1; i<size; i++) {
            //无序区间的值
            T t1 = list.get(i);
            Comparable c1 = (Comparable)t1;
            //插入点, 起始位置保持不变
            int insert = i;
            //遍历有序区间
            for (int j=i-1; j>=0; j--) {
                T t2 = list.get(j);
                Comparable c2 = (Comparable)t2;
                if (c1.compareTo(c2) < 0) {
                    //将数据向后平移
                    list.set(j+1,t2);
                    if (j==0) {
                        //说明插入的值在有序区间最小
                        insert = 0;
                    }
                } else {
                    insert = j+1;
                    break;
                }

            }
            //插入数据
            list.set(insert,t1);
        }
    }
}
