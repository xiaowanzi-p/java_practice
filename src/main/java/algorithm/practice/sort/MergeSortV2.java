package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

import java.util.Arrays;
import java.util.Collections;

/**
 * 归并排序
 *
 * @author pengshaoxiang
 */
public class MergeSortV2 {

    public static void main(String[] a) {
        int[] array = {2,1,6,4,9,0};
        int[] sort = sort(array);
        System.out.println(JSONObject.toJSONString(sort));
    }
    //1,2,3,4,5
    private static int[] sort(int[] array) {
        //递归边界
        if (array.length <= 1) {
            return array;
        }
        //划分数组
        int mid = (array.length-1)/2;
        int[] arr1 = new int[mid + 1];
        int[] arr2 = new int[array.length-1 - mid];
        for (int i=0; i<=mid; i++) {
            arr1[i] = array[i];
        }
        int index = 0;
        for (int i=mid+1; i<array.length; i++) {
            arr2[index] = array[i];
            index++;
        }
        int[] sort1 = sort(arr1);
        int[] sort2 = sort(arr2);
        return merge(sort1,sort2);
    }


    private static int[] merge(int[] sort1,int[] sort2) {
        int[] array = new int[sort1.length + sort2.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < sort1.length || j < sort2.length) {
            if (i >= sort1.length) {
                array[index] = sort2[j];
                j++;
                index++;
                continue;
            }
            if (j >= sort2.length) {
                array[index] = sort1[i];
                i++;
                index++;
                continue;
            }
            if (sort1[i] > sort2[j]) {
                array[index] = sort2[j];
                j++;
                index++;
            } else {
                array[index] = sort1[i];
                i++;
                index++;
            }
        }
        return array;
    }
}
