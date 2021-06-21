package com.example.java_practice.arithmetic.sort;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonSort {

    public static <T extends Comparable<? super T>> List<T> sort(List<T> list, int type) {
        if (type == 1) {
            return maoPaoSort(list);
        } else if (type == 2) {
            return insertSort(list);
        } else if (type == 3) {
            return gBSort(list);
        } else {
            return quickSort(list);
        }
    }

    //冒泡排序
    private static <T extends Comparable<? super T>> List<T> maoPaoSort(List<T> list) {
        if (CollectionUtils.isEmpty(list) || list.size() <= 1) {
            return list;
        }
        int size = list.size();
        //外循环size次
        for (int i=0; i<size; i++) {
            //如果有一次内循环中没有一次交换则说明排序完成
            boolean flag = false;
            //内循环相邻数据比较并且交换
            for (int j=0; j<size-i-1; j++) {
                T t1 = list.get(j);
                T t2 = list.get(j+1);
                Comparable c1 = t1;
                Comparable c2 = t2;
                if (c2.compareTo(c1) < 0) {
                    list.set(j,t2);
                    list.set(j+1,t1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return list;
    }

    //插入排序
    private static <T extends Comparable<? super T>> List<T> insertSort(List<T> list) {
        if (CollectionUtils.isEmpty(list) || list.size() <= 1) {
            return list;
        }
        int size = list.size();
        //将集合分为已排序、未排序两个分区
        for (int i=1; i<size; i++) {
            T t1 = list.get(i);
            Comparable c1 = t1;

            //找到插入点
            int index = 0;
            for (int j=i-1; j>=0; j--) {
                T t2 = list.get(j);
                Comparable c2 = t2;
                if (c1.compareTo(c2) < 0) {
                    //后移一位数据
                    list.set(j+1,t2);
                } else {
                    index = j+1;
                    break;
                }
            }
            //插入数据到插入点
            list.set(index,t1);
        }
        return list;
    }

    //归并排序
    private static <T extends Comparable<? super T>> List<T> gBSort(List<T> list) {
        if (CollectionUtils.isEmpty(list) || list.size() <= 1) {
            return list;
        }

        return divideSort(list,0,list.size());
    }

    private static <T extends Comparable<? super T>> List<T> divideSort(List<T> list, int begin, int end) {
        //递归终止条件
        int size = list.size();
        if (size <= 1) {
            return list;
        }

        //分治、选取中间角标
        int mid = (begin + end) / 2;
        List<T> subListA = list.subList(begin, mid);
        List<T> subListB = list.subList(mid, size);

        List<T> sortA = divideSort(subListA, 0, subListA.size());
        List<T> sortB =  divideSort(subListB,0,subListB.size());

        //合并排序两个子集合
        List<T> merge = merge(sortA, sortB);
        return merge;
    }

    private static <T extends Comparable<? super T>> List<T> merge(List<T> listA, List<T> listB) {
        int i = 0;
        int j = 0;
        int sizeA = listA.size();
        int sizeB = listB.size();

        List<T> list = new ArrayList<>();
        while (!(i == sizeA || j == sizeB)) {
            T t1 = listA.get(i);
            T t2 = listB.get(j);
            Comparable c1 = t1;
            Comparable c2 = t2;
            if (c1.compareTo(c2) < 0) {
                list.add(t1);
                i++;
            } else {
                list.add(t2);
                j++;
            }
        }

        //将剩余的数据放入大集合
        if (i != sizeA) {
            list.addAll(listA.subList(i,sizeA));
        }
        if (j != sizeB) {
            list.addAll(listB.subList(j,sizeB));
        }
        return list;
    }


    //快速排序
    private static <T extends Comparable<? super T>> List<T> quickSort(List<T> list) {
        if (CollectionUtils.isEmpty(list) || list.size() <= 1) {
            return list;
        }

        return partitionSort(list);
    }

    private static <T extends Comparable<? super T>> List<T> partitionSort(List<T> list) {
        //递归终止条件
        int size = list.size();
        if (size <= 1) {
            return list;
        }

        //分区，将集合分为大小两个区间
        int partition = partition(list);
        List<T> subListA;
        List<T> subListB;
        if (partition == 0) {
            subListA = list.subList(0,1);
            subListB = list.subList(1,size);
        } else {
            subListA = list.subList(0, partition);
            subListB = list.subList(partition, size);
        }

        //分治子集合
        List<T> sortA = partitionSort(subListA);
        List<T> sortB = partitionSort(subListB);

        //组装子集合
        List<T> result = new ArrayList<>();
        result.addAll(sortA);
        result.addAll(sortB);
        return result;
    }


    private static <T extends Comparable<? super T>> int partition(List<T> list) {
        //三数取中获取选取分区点
        int size = list.size();
        int index = pickPartitionIndex(list);
        T t = list.get(index);
        Comparable c = t;

        int i = 0;
        int j = 0;
        while (j < size) {
            T t1 = list.get(i);
            T t2 = list.get(j);
            Comparable c2 = t2;

            //比较交换
            if (c2.compareTo(c) < 0) {
                list.set(i,t2);
                list.set(j,t1);
                i++;
            }
            j++;
        }

        //交换分区点数据
        T data = list.get(i);
        list.set(index,data);
        list.set(i,t);
        return i;
    }


    //三数取中获取选取分区点
    private static <T extends Comparable<? super T>> int pickPartitionIndex(List<T> list) {
        int size = list.size();
        T t1 = list.get(0);
        T t2 = list.get((size-1)/2);
        T t3 = list.get(size-1);
        Comparable c1 = t1;
        Comparable c2 = t2;
        Comparable c3 = t3;

        List<T> sort = Lists.newArrayList(t1, t2, t3);
        T t = sort.get(1);
        Comparable c = t;
        if (c.compareTo(c1) == 0) {
            return 0;
        }
        if (c.compareTo(c2) == 0) {
            return (size-1)/2;
        }
        if (c.compareTo(c3) == 0) {
            return size-1;
        }
        return size-1;
    }
}
