package com.example.java_practice.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 插入排序
 *
 *
 * @author 君墨笑
 * @date 2023/9/15
 */
public class InsertSortV2 {

    /**
     *
     *
     * @param array
     */
    public static void sort(int[] array) {
        int length = array.length;
        for (int i=0; i<length; i++) {
            for (int j=i; j>0; j--) {
                if (array[j-1] > array[j]) {
                    CommonUtil.swap(array,j-1,j);
                } else {
                    break;
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
