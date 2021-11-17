package com.example.structure_arithmetic.structure.head;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmallHead<T extends Comparable<? super T>> {

    private Object[] array;
    private int capacity;
    private int size = 0;

    public SmallHead(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public boolean add(T t) {
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
        T data = (T) array[1];
        //交换
        swap(1,size);
        array[size] = null;
        size--;
        if (size == 0) {
            return data;
        }
        //自上而下堆化
        upLowHeapify(1);
        return data;
    }

    private void lowUpHeapify(int begin) {
        int target = begin;
        int father = begin / 2;
        while (father > 0) {
            Comparable td = (Comparable) array[target];
            Comparable fd = (Comparable) array[father];
            if (td.compareTo(fd) < 0) {
                swap(target,father);
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
            int min = min(left, right);
            Comparable td = (Comparable) array[target];
            Comparable md = (Comparable) array[min];
            if (td.compareTo(md) > 0) {
                swap(target,min);
                if (min == left) {
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

    private int min(int a, int b) {
        if (a > size) {
            return b;
        }
        if (b > size) {
            return a;
        }
        Comparable ad = (Comparable) array[a];
        Comparable bd = (Comparable) array[b];
        if (ad.compareTo(bd) < 0) {
            return a;
        } else {
            return b;
        }
    }
}
