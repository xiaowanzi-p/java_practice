package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 *
 * @author 君墨笑
 * @date 2023/12/7
 */
public class QuickSort {

    public static void main(String[] a) {
        int[] a1 = {4, 4, 4, 4, 4};
        int[] a2 = {4};
        int[] a3 = {4,3};
        int[] a4 = {1,5,2,4,7,2,2,2,8,2};
        int[] a5 = {1,5,2,4,7,4,12,8,15};
        int[] a6 = {7,5,2,4,7,4,12,8,1};
        sort(a1);
        sort(a2);
        sort(a3);
        sort(a4);
        sort(a5);
        sort(a6);
        System.out.println(JSONObject.toJSONString(a1));
        System.out.println(JSONObject.toJSONString(a2));
        System.out.println(JSONObject.toJSONString(a3));
        System.out.println(JSONObject.toJSONString(a4));
        System.out.println(JSONObject.toJSONString(a5));
        System.out.println(JSONObject.toJSONString(a6));
    }


    private static void sort(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length == 1) {
            return;
        }
        process(array,0,array.length-1);
    }


    private static void process(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        if (array.length < 2) {
            return;
        }
        int[] partition = partition(array, left, right);
        process(array,left,partition[0]);
        process(array,partition[1],right);
    }


    private static int[] partition(int[] array, int left, int right) {
        int less = left-1;
        int more = right;
        int index = left;
        int target = array[right];
        while (index < more) {
            //当前下标值与目标值比较,小于目标值,则当前下标值与小区间less+1值交换.index++,less++
            if (array[index] < target) {
                CommonUtil.swap(array,index,less+1);
                index++;
                less++;
            } else if (array[index] > target) {
                //当前下标值与目标值比较,大于目标值,则当前下标值与大区间more-1值交换.index不变,more--
                CommonUtil.swap(array,index,more-1);
                more--;
            } else {
                //当前下标值与目标值比较,等于目标值,则当前下标值++
                index++;
            }
        }
        //交换目标值位置与大区间下标
        CommonUtil.swap(array,right,more);
        return new int[]{less,more+1};
    }
}
