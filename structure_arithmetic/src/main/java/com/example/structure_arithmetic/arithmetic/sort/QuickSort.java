package com.example.structure_arithmetic.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
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



    public static void main(String[] a) {
        int[] array = {3, 5, 9, 1, 6, 2, 4};
        int[] ints = sort1(array);
        System.out.println(JSONObject.toJSONString(ints));
    }

    public static int[] sort1 (int[] array) {
        //递归边界条件
        if (array == null || array.length <=1) {
            return array;
        }
        //分区
        int mid = partition1(array);
        int[] p1 = Arrays.copyOfRange(array, 0, mid);
        int[] p2 = Arrays.copyOfRange(array, mid, array.length);
        //递归排序
        int[] sp1 = sort1(p1);
        int[] sp2 = sort1(p2);
        //归并
        int[] newArray = new int[sp1.length + sp2.length];
        for (int i=0; i<sp1.length; i++) {
            newArray[i] = sp1[i];
        }
        for (int i=0; i<sp2.length; i++) {
            newArray[sp1.length+i] = sp2[i];
        }
        return newArray;
    }


    public static int partition1(int[] array) {
        //选择最后一个元素作为分区点
        int length = array.length;
        int compartor = array[length - 1];

        //分区下标和对比下标
        int pindex = 0;
        int cindex = 0;
        while (cindex < length-1) {
            if (array[cindex] < compartor) {
                if (pindex != cindex) {
                    int d1 = array[cindex];
                    int d2 = array[pindex];
                    array[pindex] = d1;
                    array[cindex] = d2;
                }
                pindex++;
            }
            cindex++;
        }

        //置换分区下标和分区点
        if (pindex != length-1) {
            int d = array[pindex];
            array[length-1] = d;
            array[pindex] = compartor;
        }
        return pindex;
    }
}
