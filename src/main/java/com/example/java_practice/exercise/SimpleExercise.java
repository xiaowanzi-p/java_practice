package com.example.java_practice.exercise;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class SimpleExercise {


    /**
     * 查找集合中第K大的元素
     *
     * @param list
     * @param k
     * @param <T>
     */
    public <T extends Comparable<? super T>> T pickSomeBigData(List<T> list, int k) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (k > list.size()) {
            return null;
        }
        T t = partition_pick(list, k);
        return t;
    }


    private <T extends Comparable<? super T>> T partition_pick(List<T> list, int k) {
        if (list.size() <= 1) {
            return list.get(0);
        }
        int size = list.size();
        int mid = partition(list);
        int index = mid + 1;
        if (index == k) {
            return list.get(mid);
        }
        T t;
        //二分查找的思想判断数据位于哪一个区间,在进行递归查找
        if (index > k) {
            List<T> subList = list.subList(0, mid);
            t = partition_pick(subList,k);
        } else {
            List<T> subList = list.subList(mid + 1, list.size());
            t = partition_pick(subList,k-index);
        }
        return t;
    }

    private <T extends Comparable<? super T>> int partition(List<T> list) {
        //每次选择集合最后一个节点数据作为分区点
        T provid = list.get(list.size() - 1);
        Comparable comparable = provid;

        //两个指针, I指针分割点
        int i = 0;
        int j = 0;
        while (j < list.size()-1) {
            T t1 = list.get(i);
            T t2 = list.get(j);
            Comparable c2 = t2;
            if (c2.compareTo(comparable) < 0) {
                list.set(i,t2);
                list.set(j,t1);
                i++;
            }
            j++;
        }

        //交换分区点
        T t = list.get(i);
        list.set(i,provid);
        list.set(list.size() - 1,t);
        return i;
    }
}
