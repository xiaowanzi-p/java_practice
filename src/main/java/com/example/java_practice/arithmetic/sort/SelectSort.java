package com.example.java_practice.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

import java.util.List;

/**
 * 选择排序
 */
public class SelectSort {

    /**
     * 8, 2, 1, 9, 3, 6, 2, 6, 7, 8,0,13
     * 遍历一遍数组将最小的数字放在下标为0的位置
     * 再从下标1遍历一遍数组将最小的位置放在下标为1的位置
     * 以此类推，每次都排序好一个位置
     *
     * @param array
     */
    public static void sort(int[] array) {
        int length = array.length;
        for (int i=0; i<length-1; i++) {
            for (int j=i; j<length; j++) {
                if (array[j] < array[i]) {
                    CommonUtil.swap(array,i,j);
                }
            }
        }
    }

    public static void main(String[] a) {
        int[] array = {8, 2, 1, 9, 3, 6, 2, 6, 7, 8,0,13};
        sort(array);
        System.out.println(JSONObject.toJSONString(array));
    }
}
