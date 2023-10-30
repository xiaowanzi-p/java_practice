package com.example.java_practice.arithmetic.select;

/**
 * @author 君墨笑
 * @date 2023/10/24
 */
public class BinarySelectV2 {

    public static void main(String[] a) {

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
}
