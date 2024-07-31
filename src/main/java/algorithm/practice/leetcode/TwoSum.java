package algorithm.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 有序数组的两数之和下标
 */
public class TwoSum {


    public static void main(String[] a) {
        int[] ints = {2, 7, 11, 15};
        twoSum(ints,9);
        ArrayList<Object> list = new ArrayList<>();
    }

    public static int[] twoSum(int[] numbers, int target) {
        //左右下标
        int l = 0;
        int r = numbers.length - 1;
        while(l < r) {
            int value = numbers[l] + numbers[r];
            if(value > target) {
                r--;
            }
            if(value < target) {
                l++;
            }
            if(value == target) {
                int index = l-1;
                if(l == 0) {
                    return new int[]{l,r};
                }
                if(numbers[index] != numbers[l]) {
                    return new int[]{l,r};
                }
                l++;
            }
        }
        return null;
    }
}
