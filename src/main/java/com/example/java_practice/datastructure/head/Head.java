package com.example.java_practice.datastructure.head;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 堆
 */
@Setter
@Getter
public class Head<T extends Comparable<? super T>> {
    //存储数据
    private Object[] array;
    //堆容量
    private int capacity;
    //堆中数据的个数
    private int size;
    //是否最大堆
    private boolean isBig;

    public Head(int capacity, boolean isBig) {
        this.capacity = capacity;
        size = 0;
        array = new Object[capacity+1];
        this.isBig = isBig;
    }

    public Head() {
        this.capacity = 50;
        size = 0;
        array = new Object[capacity+1];
        this.isBig = true;
    }

    //堆中增加一个元素
    public void add(T data) {
        //堆已满
        if (size >= capacity) {
            return;
        }
        size++;
        array[size] = data;
        //从下向上堆化
        lowToUpHeapify(size);
    }

    //自下而上堆化
    private void lowToUpHeapify(int begin) {
        int father = begin/2;
        int son = begin;
        while (father > 0) {
            Comparable source = (Comparable) array[son];
            Comparable target = (Comparable) array[father];
            //最大堆的堆化
            if (isBig) {
                if (source.compareTo(target) > 0) {
                    swap(father,son);
                    son = father;
                    father = father/2;
                    continue;
                }
            } else {
                //最小堆的堆化
                if (source.compareTo(target) < 0) {
                    swap(father,son);
                    son = father;
                    father = father/2;
                    continue;
                }
            }
            break;
        }
    }

    //删除堆顶元素, 并重新堆化
    public T removeTop() {
        //堆为空则返回空
        if (size <= 0) {
            return null;
        }
        //最后一个元素移动到堆顶
        T result = (T) array[1];
        array[1] = array[size];
        array[size] = null;
        size--;
        //从上而下堆化
        upToLowHeapify(1);
        return result;
    }

    //自上而下堆化
    private void upToLowHeapify(int begin) {
        int target = begin;
        int left = 2*target; //左子节点
        int right = 2*target+1; //右子节点

        //循环直到没有子节点
        while (array[left] != null || array[right] != null) {
            Comparable c = (Comparable) array[target];
            int swapIndex = getSwapSonIndex(left, right);
            Comparable c2 = (Comparable) array[swapIndex];

            //最大堆
            if (isBig) {
                if (c.compareTo(c2) < 0) {
                    swap(target,swapIndex);
                    target = swapIndex;
                    left = 2*target;
                    right = 2*target + 1;
                    continue;
                }
            } else {
                //最小堆
                if (c.compareTo(c2) > 0) {
                    swap(target,swapIndex);
                    target = swapIndex;
                    left = 2*target;
                    right = 2*target + 1;
                    continue;
                }
            }
            break;
        }
    }

    //获取堆顶数据，但不移除
    public T getTop() {
        if (size <= 0) {
            return null;
        }
        return (T) array[1];
    }

    //堆排序
    public static <T extends Comparable<? super T>> String sort(T[] array) {
        if (array.length <= 0) {
            return "";
        }
        //建堆
        Head<T> head = buildHead(array);
        int tail = head.size;
        while (tail > 1) {
            //交换
            swap(head.array,1,tail);
            tail--;
            heapify(head.array,tail);
        }
        return JSONObject.toJSONString(head.array);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (size > 0) {
            for (int i=1; i<=size; i++) {
                builder.append(JSONObject.toJSONString(array[i]));
                if (i != size) {
                    builder.append(",");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

    //交换两个数据
    private void swap(int father, int son) {
        T t1 = (T) array[father];
        array[father] = array[son];
        array[son] = t1;
    }

    //比较左右节点获取需要交换数据的下标
    private int getSwapSonIndex(int left, int right) {
        if (array[left] == null) {
            return right;
        }
        if (array[right] == null) {
            return left;
        }

        Comparable cLetf = (Comparable) array[left];
        Comparable cRight = (Comparable) array[right];
        int index;
        if (cLetf.compareTo(cRight) < 0) {
            if (isBig) {
                index = right;
            } else {
                index = left;
            }
        } else {
            if (isBig) {
                index = left;
            } else {
                index = right;
            }
        }
        return index;
    }


    //比较左右节点获取最大的下标
    private static int getMaxIndex(Object[] array, int left, int right) {
        Comparable cLetf = (Comparable) array[left];
        Comparable cRight = (Comparable) array[right];
        if (cLetf.compareTo(cRight) < 0) {
            return right;
        } else {
            return left;
        }
    }

    //建堆
    private static <T extends Comparable<? super T>> Head<T> buildHead(T[] array) {
        Head<T> head = new Head<>(array.length,true);
        for (T t : array) {
            head.add(t);
        }
        return head;
    }

    //从上而下堆化
    private static <T extends Comparable<? super T>> void heapify(Object[] array, int index) {
        int target = 1;
        int left = 2*target;
        int right = 2*target + 1;
        while ((array[left] != null || array[right] != null) && target <= index) {
            int maxIndex = getMaxIndex(array, left, right);
            Comparable father = (Comparable) array[target];
            Comparable son = (Comparable) array[maxIndex];
            if (father.compareTo(son) < 0) {
                swap(array,target,maxIndex);
                target = maxIndex;
                left = 2*target;
                right = 2*target + 1;
                continue;
            }
            break;
        }
    }

    //交换两个数据
    private static void swap(Object[] array, int father, int son) {
        Object t1 = array[father];
        array[father] = array[son];
        array[son] = t1;
    }
}
