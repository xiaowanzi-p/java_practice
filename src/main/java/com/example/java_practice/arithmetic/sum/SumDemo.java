package com.example.java_practice.arithmetic.sum;

/**
 * @author 君墨笑
 * @date 2023/10/11
 */
public class SumDemo {

    public static void main(String[] a) {
        int[] ints = {1, 4, 2, 6, 4, 5};
        int[] sumArray = initSumArray(ints);
        int sum = sum(sumArray, 1, 4);
        System.out.println(sum);
    }


    /**
     * 对数求和-初始化求和数组
     *
     * @param array
     * @return
     */
    private static int[] initSumArray(int[] array) {
        int[] sumArray = new int[array.length];
        int sum = 0;
        for (int i=0;i<array.length;i++) {
            sum = sum + array[i];
            sumArray[i] = sum;
        }
        return sumArray;
    }

    /**
     * 对数求和-求和方案
     *
     * @param left 左边界
     * @param right 有边界
     * @return
     */
    public static int sum(int[] sumArray, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == 0) {
            return sumArray[right];
        }
        return sumArray[right] - sumArray[left-1];
    }
}
