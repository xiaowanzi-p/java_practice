package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 冒泡排序
 *
 * @author pengshaoxiang
 */
public class BubbleSort {

    public static void main(String[] a) {
        int[] array = {1, 7, 4, 2, 5, 6, 6, 8};
        sort(array);
        System.out.println(JSONObject.toJSONString(array));
    }
    //1,2,3,4,5,6,7,8
    private static void sort(int[] array) {
        int length = array.length;
        for (int i=length-2; i>=0; i--) {
            for (int j=0; j<=i; j++) {
                if (array[j] > array[j+1]) {
                    CommonUtil.swap(array,j,j+1);
                }
            }
        }
    }
}
