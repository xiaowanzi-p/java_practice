package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 快排
 *
 * @author pengshaoxiang
 */
public class QuickSortV2 {

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
        if (array.length <= 1) {
            return;
        }
        process(array,0,array.length-1);
    }

    private static void process(int[] array, int left, int right) {
        //递归边界条件
        if (left >= right) {
            return;
        }
        //分区
        int[] partition = partition(array, left, right);
        //递归小于区域、大于区域
        process(array,0,partition[0]-1);
        process(array,partition[1]+1,right);
    }

    private static int[] partition(int[] array, int left, int right) {
        int min = left-1; //小于区域指针
        int max = right; //大于区域指针
        int target = right; //对比指指针
        int index = left; //移动指针
        while (index < max) {
            if (array[index] == array[target]) {
                index++;
            }
            if (array[index] < array[target]) {
                //当前值与小于区域下一个值交换，小于区域向右扩1
                CommonUtil.swap(array,min+1,index);
                min++;
                index++;
            }
            if (array[index] > array[target]) {
                //当前值与大于区域左一个值交换，大于区域向左扩1
                CommonUtil.swap(array,index,max-1);
                max--;
            }
        }
        //比较值与大于区域最左边值交换
        CommonUtil.swap(array,target,max);
        return new int[]{min+1,max};
    }
}
