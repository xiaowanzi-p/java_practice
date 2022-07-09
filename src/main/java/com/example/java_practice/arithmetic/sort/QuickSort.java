package com.example.java_practice.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        System.out.println("排序之前"+ list);
        //分区排序
        List<T> data = partition_sort(list, 0, list.size() - 1);
        System.out.println("排序之后"+ data);
    }


    private static <T extends Comparable<? super T>> List<T> partition_sort(List<T> list, int begin, int end) {
        //如果排到最后一个元素则是递归结束条件
        if (list.size() <= 1) {
            return list;
        }

        //对大集合进行分区，找到中间角标
        int mid = partition(list);
        List<T> listA;
        List<T> listB;
        if (mid == 0) {
            listA = list.subList(0,1);
            listB = list.subList(1,list.size());
        } else {
            listA = list.subList(begin, mid);
            listB = list.subList(mid, end + 1);
        }
        //对两个子集合进行分治，排序后进行合并
        List<T> sortA = partition_sort(listA, 0, listA.size() - 1);
        List<T> sortB = partition_sort(listB, 0, listB.size() - 1);
        List<T> result = new ArrayList<>();
        result.addAll(sortA);
        result.addAll(sortB);
        return result;
    }


    private static <T extends Comparable<? super T>> int partition(List<T> list) {
        //每次选最后一个数据作为分区点
        T povid = list.get(list.size() - 1);
        Comparable comparable = povid;

        //i指针作为分区节点指针
        //i指针左边为小于povid的区间
        //i指针右边的为大于的区间
        int i = 0;
        int j = 0;
        //遍历大集合
        while (j < list.size()-1) {
            T tJ = list.get(j);
            T tI = list.get(i);
            Comparable c = tJ;
            //如果遍历的元素小于分区点，则放入小区间的末尾，也就是i指针的位置
            if (c.compareTo(comparable) < 0) {
                //数据交换，将小区间数据放入i指针位置
                list.set(i,tJ);
                list.set(j,tI);
                i++;
            }
            //如果不小于，则i指针不动，遍历节点j自增
            j++;
        }
        //遍历完成后，交换分区点数据到i节点上，作为中间节点
        T t = list.get(i);
        list.set(i,povid);
        list.set(list.size()-1,t);
        return i;
    }


    public static void main(String[] a) {
        int[] array = {1, 5, 2, 4, 8, 6, 9};
        int[] ints = sort1(array);
        System.out.println(JSONObject.toJSONString(ints));
    }

    public static int[] sort1(int[] array) {
        //递归结束条件
        int length = array.length;
        if (array == null || length <=1) {
            return array;
        }
        //分区
        int mid = partition1(array);
        int[] array1 = Arrays.copyOfRange(array, 0, mid);
        int[] array2 = Arrays.copyOfRange(array, mid, length);
        //递归
        int[] sortArray1 = sort1(array1);
        int[] sortArray2 = sort1(array2);
        //合并
        int[] newArray = new int[sortArray1.length + sortArray2.length];
        for (int i=0; i<sortArray1.length; i++) {
            newArray[i] = sortArray1[i];
        }
        for (int i=0; i<sortArray2.length; i++) {
            newArray[sortArray1.length+i] = sortArray2[i];
        }
        return newArray;
    }

    public static int partition1(int[] array) {
        //最后一个数据作为分区比较点
        int length = array.length;
        int compator = array[length - 1];

        //遍历指针和分区节点指针
        int bl = 0;
        int cp = 0;

        while (bl < length-1) {
            int bd = array[bl];
            if (bd < compator) {
                if (bl != cp) {
                    int cd = array[cp];
                    array[cp] = bd;
                    array[bl] = cd;
                }
                cp++;
            }
            bl++;
        }

        //替换分区节点
        if (cp != length-1) {
            int cd = array[cp];
            array[length-1] = cd;
            array[cp] = compator;
        }
        return cp;
    }
}
