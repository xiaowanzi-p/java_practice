package com.example.java_practice.arithmetic.select;

import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * @author 君墨笑
 * @date 2023/10/24
 */
public class BinarySelectV2 {

    public static void main(String[] a) {
        int[] ints = {1, 2, 2, 2,3,3,3,3,5,6,6,6,7,7,9,10,13,15,16};
        int select = leftSelect(ints, 7);
        System.out.println(select);
    }


    private static int select(int[] array, int value) {
        int index = -1;
        int length = array.length;
        int left = 0;
        int right = length-1;
        while (right > left) {
            int mid = (left + right)/2;
            if (array[mid] == value) {
                index = mid;
                return index;
            }
            if (array[mid] > value) {
                right = mid - 1;
            }
            if (array[mid] < value) {
                left = mid + 1;
            }
        }
        return index;
    }


    private static int leftSelect(int[] array, int value) {
        int index = -1;
        int length = array.length;
        int left = 0;
        int right = length-1;
        while (right > left) {
            int mid = (left + right)/2;
            if (array[mid] == value) {
                index = mid;
                right = index - 1;
            }
            if (array[mid] > value) {
                right = mid - 1;
            }
            if (array[mid] < value) {
                left = mid + 1;
            }
        }
        return index;
    }
}
