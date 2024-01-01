package algorithm.practice.sort;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 选择排序
 *
 * @author pengshaoxiang
 */
public class SelectSort {

    public static void main(String[] a) {
        int[] array = {1};
        sort(array);
        System.out.println(JSONObject.toJSONString(array));
    }
    //1,2,3,4,5,6,7,8
    private static void sort(int[] array) {
        int length = array.length;
        for (int i=0; i<length-1; i++) {
            for (int j=i; j<length; j++) {
                if (array[i] > array[j]) {
                    CommonUtil.swap(array,i,j);
                }
            }
        }
    }
}
