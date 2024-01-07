package algorithm.practice.sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 快排
 *
 * @author pengshaoxiang
 */
public class QuickSortV3 {


    public static void main(String[] a) {
        int[] a1 = {1};
        int[] a2 = {1,2};
        int[] a3 = {2,1};
        int[] a4 = {2,2};
        int[] a5 = {10,2,5,2,7,7,9,5,6};
        sort(a1,0,a1.length-1);
        sort(a2,0,a2.length-1);
        sort(a3,0,a3.length-1);
        sort(a4,0,a4.length-1);
        sort(a5,0,a5.length-1);
        System.out.println(JSONObject.toJSONString(a1));
        System.out.println(JSONObject.toJSONString(a2));
        System.out.println(JSONObject.toJSONString(a3));
        System.out.println(JSONObject.toJSONString(a4));
        System.out.println(JSONObject.toJSONString(a5));
    }


    private static void sort(int[] array, int left, int right) {
        //递归边界条件
        if (array.length == 1) {
            return;
        }
        if (left >= right) {
            return;
        }
        //分区
        int[] partiton = partiton(array, left, right);
        sort(array,left,partiton[0]-1);
        sort(array,partiton[1]+1, right);
    }


    private static int[] partiton(int[] array, int left, int right) {
        //小于区域
        int less = left-1;
        //大于区域
        int more = right;
        //当前
        int current = left;
        //比较值
        int target = right;
        while (current < more) {
            if (array[current] == array[target]) {
                current++;
            }
            if (array[current] < array[target]) {
                CommonUtil.swap(array,current,less+1);
                less++;
                current++;
            }
            if (array[current] > array[target]) {
                CommonUtil.swap(array,current,more-1);
                more--;
            }
        }
        CommonUtil.swap(array,more,target);
        return new int[]{less+1,more};
    }
}
