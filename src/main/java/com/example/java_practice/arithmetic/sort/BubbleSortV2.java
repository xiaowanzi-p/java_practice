package com.example.java_practice.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 冒泡排序
 */
public class BubbleSortV2 {

    public static void main(String[] a) {
        int[] array = {8, 2, 1, 9, 3, 6, 2, 6, 7, 8,0,13};
        sort(array);
        System.out.println(JSONObject.toJSONString(array));
    }


    /**
     * 8, 2, 1, 9, 3, 6, 2, 6, 7, 8,0,13
     * 两两比较，将最大的元素放在最后一个位置排好序
     * 继续两两比较，找到下一个最大的元素放在倒数第二后的位置排好序
     * 重复如此，直到所有的元素排序号
     *
     * @param array
     */
    public static void sort(int[] array) {
        for (int i=array.length-2; i>=0; i--) {
            for (int j=0; j<=i; j++) {
                if (array[j] > array[j+1]) {
                    CommonUtil.swap(array,j,j+1);
                }
            }
        }
    }
}
