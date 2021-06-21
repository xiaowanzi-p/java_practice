package com.example.java_practice.arithmetic.head;

import com.example.java_practice.datastructure.head.Head;

import java.util.List;

public class HeadExercise {

    /**
     * 获取TopK的元素
     */
    public static <T extends Comparable<? super T>> String getTopN(List<T> list, int topSize) {
        //建堆
        Head<T> head = new Head<>(topSize,false);
        //加入数据
        for (T t : list) {
            //堆未满则直接加入
            if (head.getSize() < head.getCapacity()) {
                head.add(t);
                continue;
            }
            //堆满则跟堆顶数据进行判断
            T top = head.getTop();
            Comparable cTop = top;
            Comparable cData = t;
            if (cData.compareTo(cTop) > 0) {
                head.removeTop();
                head.add(t);
            }
        }
        return head.toString();
    }
}
