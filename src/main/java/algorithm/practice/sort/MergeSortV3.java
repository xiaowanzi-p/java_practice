package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 君墨笑
 * @date 2024/7/18
 */
public class MergeSortV3 {

    public static void main(String[] a) {
        int[] array = {2,1,6,4,9,0};
        int[] sort = sort(array);
        System.out.println(JSONObject.toJSONString(sort));
    }

    private static int[] sort(int[] array) {
        //递归结束条件
        if(array.length <= 1) {
            return array;
        }
        //1 2 3 4 5
        //分两个数组
        int mid = array.length/2;
        int[] arr1 = new int[mid];
        int[] arr2 = new int[array.length-mid];
        for(int i=0; i<array.length; i++) {
            if(i < mid) {
                arr1[i] = array[i];
            } else {
                arr2[i-mid] = array[i];
            }
        }
        //递归
        int[] sort1 = sort(arr1);
        int[] sort2 = sort(arr2);
        return merge(sort1,sort2);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int index1 = 0;
        int index2 = 0;
        int length1 = arr1.length;
        int length2 = arr2.length;
        int[] arr = new int[length1+length2];
        int cur = 0;
        while(index1<length1 || index2<length2) {
            if(index1 >= length1) {
                arr[cur] = arr2[index2];
                index2++;
                cur++;
                continue;
            }
            if (index2 >= length2) {
                arr[cur] = arr1[index1];
                index1++;
                cur++;
                continue;
            }
            if(arr1[index1] > arr2[index2]) {
                arr[cur] = arr2[index2];
                index2++;
            } else {
                arr[cur] = arr1[index1];
                index1++;
            }
            cur++;
        }
        return arr;
    }
}
