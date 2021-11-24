package com.example.structure_arithmetic.arithmetic.sort;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static void sort(List list) {
        //递归边界条件
        if (CollectionUtils.isEmpty(list) || list.size() <= 1) {
            return;
        }
        //分区
        int mid = partion(list);
        List list1 = list.subList(0, mid);
        List list2 = list.subList(mid, list.size());
        //递归公式
        sort(list1);
        sort(list2);
        //合并
        List finalList = Lists.newArrayList();
        finalList.addAll(list1);
        finalList.addAll(list2);
        list = finalList;
    }


    public static int partion(List list) {
        //选择末尾数据作为分区比较点
        Comparable partion = (Comparable)list.get(list.size() - 1);

        //分区数据占位下标
        int i = 0;
        //遍历下标
        int j = 0;
        while (j < list.size()-1) {
            Comparable cj = (Comparable) list.get(j);
            if (cj.compareTo(partion) < 0) {
                if (i != j) {
                    swap(list,i,j);
                }
                i++;
            }
            j++;
        }
        if (i != list.size()-1) {
            swap(list,i,list.size()-1);
        }
        return i;
    }


    private static void swap(List list, int a, int b) {
        Object o1 = list.get(a);
        Object o2 = list.get(b);
        list.set(a,o2);
        list.set(b,o1);
    }
}
