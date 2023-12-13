package algorithm.practice.heap;

import com.alibaba.fastjson.JSON;
import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 构建一个堆
 * 左：2*i+1
 * 右：2*i+2
 * 父:(i-1)/2
 *
 * @author 君墨笑
 * @date 2023/12/11
 */
public class CustomHeap {

    private static int[] array;
    private static int capacity;
    private static int size = 0;
    private static boolean max = true;//默认最大堆

    public static void main(String[] a) {
        build(10);
        add(2);
        add(3);
        add(4);
        add(2);
        add(1);
        add(9);
        add(6);
        System.out.println(JSON.toJSON(array));
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
        System.out.println("弹出：" + poll());
    }

    private static void build(int capacity) {
        CustomHeap.capacity = capacity;
        array = new int[capacity];
    }

    private static void add(int value) {
        if (size >= capacity) {
            System.out.println("堆满了");
            return;
        }
        //当前插入元素下标
        int currentIndex = size;
        array[currentIndex] = value;
        size++;
        int farther = (currentIndex-1)/2;
        if (max) {
            while (array[currentIndex] > array[farther]) {
                CommonUtil.swap(array,currentIndex,farther);
                currentIndex = farther;
                farther = (currentIndex-1)/2;
            }
        }
        if (!max) {
            while (array[currentIndex] < array[farther]) {
                CommonUtil.swap(array,currentIndex,farther);
                currentIndex = farther;
                farther = (currentIndex-1)/2;
            }
        }
    }

    private static int poll() {
        if (size <= 0) {
            return -1;
        }
        //堆顶返回值
        int value = array[0];
        //堆大小减一
        CommonUtil.swap(array,0,size-1);
        size--;
        //向下堆化
        heapify(array,0,size);
        return value;
    }


    private static void heapify(int[] array, int current, int size) {
        if (size <= 0) {
            return;
        }
        boolean flag = true;
        while (flag) {
            int left = 2*current + 1;
            int right = 2*current + 2;
            int compare = compare(array, left, right, size);
            if (compare == -1) {
                flag = false;
                continue;
            }
            if (max) {
                if (array[current] < array[compare]) {
                    CommonUtil.swap(array,compare,current);
                    current = compare;
                } else {
                    flag = false;
                    continue;
                }
            }
            if (!max) {
                if (array[current] > array[compare]) {
                    CommonUtil.swap(array,compare,current);
                    current = compare;
                } else {
                    flag = false;
                    continue;
                }
            }
        }
    }


    private static int compare(int[] array, int left, int right, int size) {
        if (left > size-1 && right > size-1) {
            return -1;
        }
        if (left > size-1) {
            return right;
        }
        if (right > size-1) {
            return left;
        }
        if (max) {
            return array[left] > array[right] ? left : right;
        }
        return array[left] > array[right] ? right : left;
    }
}
