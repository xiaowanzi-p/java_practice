package com.example.java_practice.arithmetic.select;

import java.util.Arrays;
import java.util.List;

public class BinarySelect {

    //二分查找下标
    public static <T extends Comparable<? super T>> int select(List<T> list, T t, int type) {
        int index = binary(list, t, 0);
        return adapt(list,t,index,type);
    }

    private static <T extends Comparable<? super T>> int binary(List<T> list, T t, int begin) {
        //递归终止条件
        int size = list.size();
        int mid = size / 2;
        Comparable midC = list.get(mid);
        Comparable tC = t;
        if (midC.compareTo(tC) == 0) {
            //真实位置下标
            return mid + begin;
        }
        if (mid == 0) {
            return -1;
        }

        //二分判断
        List<T> subListA = list.subList(0, mid);
        List<T> subListB = list.subList(mid,size);
        int index;
        if (midC.compareTo(tC) < 0) {
            index = binary(subListB,t,mid+begin);
        } else {
            index = binary(subListA,t,0);
        }

        return index;
    }


    private static <T extends Comparable<? super T>> int adapt(List<T> list, T t, int index, int type) {
        if (index == -1) {
            return -1;
        }

        Comparable c = t;
        int size = list.size();

        if (type == 0) {
            return index;
        }

        if (type == 1) {
            //查找第一个给定的元素
            while (index >= 0) {
                index = index-1;
                if (index < 0) {
                    return index+1;
                }
                Comparable c2 = list.get(index);
                if(c.compareTo(c2) != 0) {
                    return index+1;
                }
            }
            return -1;
        }

        if (type == 2) {
            //查找最后一个给定的元素
            while (index < size) {
                index = index+1;
                if (index >= size) {
                    return index-1;
                }
                Comparable c2 = list.get(index);
                if(c.compareTo(c2) != 0) {
                    return index-1;
                }
            }
            return -1;
        }

        if (type == 3) {
            //查找第一个大于等于给定值的元素
            while (index < size) {
                index = index+1;
                if (index >= size) {
                    return -1;
                }
                Comparable c2 = list.get(index);
                if(c.compareTo(c2) != 0) {
                    return index;
                }
            }
            return -1;
        }

        if (type == 4) {
            //查找最后一个小于等于定值的元素
            while (index >= 0) {
                index = index-1;
                if (index < 0) {
                    return -1;
                }
                Comparable c2 = list.get(index);
                if(c.compareTo(c2) != 0) {
                    return index;
                }
            }
            return -1;
        }
        return -1;
    }


    public static void main(String[] a) {
        int[] array = {1,2,3,4,5,6,7};
        int index = binary1(array, 4);
        System.out.println(index);
    }

    public static int binary1(int[] array, int target) {
        int length = array.length;
        int low = 0;
        int hight = length-1;

        while (low <= hight) {
            int mid = (low+hight)/2 + low;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                hight = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
