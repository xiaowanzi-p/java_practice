package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 归并排序
 *
 * @author 君墨笑
 * @date 2023/12/5
 */
public class MergeSort {

    public static void main(String[] a) {
        int[] array1 = {1, 6, 3, 2, 4, 7, 9, 9};
        int[] array2 = {1};
        int[] array3 = {1,8};
        System.out.println(JSONObject.toJSONString(sort(array1)));
        System.out.println(JSONObject.toJSONString(sort(array2)));
        System.out.println(JSONObject.toJSONString(sort(array3)));
    }



    private static int[] sort(int[] array) {
        //递归边界条件
        if (array == null || array.length == 1) {
            return array;
        }
        //求中点
        int mid = (array.length-1)/2;
        int[] array1 = new int[mid+1];
        int[] array2 = new int[(array.length-1) - (mid+1) +1];
        for (int i=0;i<array.length;i++) {
            if (i<=mid) {
                array1[i] = array[i];
            } else {
                array2[i-mid-1] = array[i];
            }
        }
        //递归
        int[] sort1 = sort(array1);
        int[] sort2 = sort(array2);
        return merge(sort1,sort2);
    }


    private static int[] merge(int[] array1, int[] array2) {
        int[] array = new int[array1.length + array2.length];
        int self = 0;
        int one = 0;
        int two = 0;
        while (one < array1.length || two < array2.length) {
            if (one >= array1.length) {
                array[self] = array2[two];
                two++;
                self++;
                continue;
            }
            if (two >= array2.length) {
                array[self] = array1[one];
                one++;
                self++;
                continue;
            }
            if (array1[one] < array2[two]) {
                array[self] = array1[one];
                one++;
                self++;
                continue;
            }
            if (array1[one] >= array2[two]) {
                array[self] = array2[two];
                two++;
                self++;
            }
        }
        return array;
    }
}
