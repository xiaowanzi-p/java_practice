package com.example.structure_arithmetic.structure.head;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BigHead<T extends Comparable<? super T>> {

    private Object[] array;
    private int capacity;
    private int size = 0;

    public BigHead(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public boolean add(T t) {
        //插入数据
        if (size >= capacity) {
            return false;
        }
        size++;
        array[size] = t;
        //自下而上堆化
        lowUpHeapify(size);
        return true;
    }


    public T remove() {
        if (size == 0) {
            return null;
        }
        Object top = array[1];
        if (size == 1) {
            return (T) top;
        }
        //交换树尾和树根节点
        swap(size,1);
        array[size] = null;
        size--;
        //自上而下堆化
        upLowHeapify(1);
        return (T) top;
    }

    private void lowUpHeapify(int begin) {
        int target = begin;
        int father = begin / 2;
        while (father > 0) {
            Comparable fatherData = (Comparable)array[father];
            Comparable targetData = (Comparable)array[target];
            if (targetData.compareTo(fatherData) > 0) {
                //交换数据
                swap(father,target);
                target = father;
                father = target / 2;
            } else {
                break;
            }
        }
    }

    private void upLowHeapify(int begin) {
        int target = begin;
        int left = begin * 2;
        int right = begin * 2 + 1;
        while (left <= size || right <= size) {
            //找出最大的节点
            int max = max(left, right);
            Comparable md = (Comparable)array[max];
            Comparable td = (Comparable)array[target];
            if (td.compareTo(md) < 0) {
                swap(target,max);
                if (max == left) {
                    target = left;
                } else {
                    target = right;
                }
                left = target * 2;
                right = target * 2 + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int a, int b) {
        Comparable ad = (Comparable)array[a];
        Comparable bd = (Comparable)array[b];
        array[b] = ad;
        array[a] = bd;
    }


    private int max(int a, int b) {
        if (a > size) {
            return b;
        }
        if (b > size) {
            return a;
        }
        Comparable ad = (Comparable) array[a];
        Comparable bd = (Comparable) array[b];
        if (ad.compareTo(bd) > 0) {
            return a;
        } else {
            return b;
        }
    }

}
