package com.example.java_practice.arithmetic.head;

import com.example.java_practice.datastructure.head.Head;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrecentExercise<T extends Comparable<? super T>> {

    public PrecentExercise(long precent) {
        this.precent = precent;
    }

    //构建百分比的堆
    private Head<T> bigHead = new Head<>(100, true);
    private Head<T> smallHead = new Head<>(100, false);
    private long precent;

    //获取需求的百分比位数据
    public T getPrecentData() {
        return bigHead.getTop();
    }

    public void addPrecentData(T data) {
        //初始首数据加入最大堆
        if (bigHead.getSize() == 0) {
            bigHead.add(data);
            return;
        }

        //和最大堆堆顶元素比较，确定新增数据落入哪里
        Comparable top = (Comparable) bigHead.getTop();
        Comparable target = (Comparable) data;
        if (target.compareTo(top) > 0) {
            smallHead.add(data);
        } else {
            bigHead.add(data);
        }

        //计算百分比, 重新迁移数据进行比例平衡
        while (bigHead.getSize() > 1 && comparePrecent(bigHead.getSize(),smallHead.getSize()) > 0) {
            T big = bigHead.removeTop();
            smallHead.add(big);
        }
        while (smallHead.getSize() > 1 && comparePrecent(bigHead.getSize(),smallHead.getSize()) < 0) {
            T small = smallHead.removeTop();
            bigHead.add(small);
        }
    }

    //-1最大堆，0平衡，1最小堆
    private int comparePrecent(long bigSize, long smallSize) {
        BigDecimal standard = BigDecimal.valueOf(precent).divide(BigDecimal.valueOf(100)).setScale(2);
        BigDecimal calculate = calculatePrecent(bigSize, smallSize);
        if (calculate.equals(BigDecimal.ZERO)) {
            return -1;
        }
        if (calculate.equals(standard)) {
            return 0;
        }
        int compare = calculate.compareTo(standard);
        if (compare < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    private BigDecimal calculatePrecent(long bigSize, long smallSize) {
        long total = bigSize + smallSize;
        if (total == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal precent = BigDecimal.valueOf(bigSize).divide(BigDecimal.valueOf(total), 2,RoundingMode.HALF_UP).setScale(2);
        return precent;
    }
}
