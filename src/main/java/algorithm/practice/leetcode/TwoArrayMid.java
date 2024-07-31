package algorithm.practice.leetcode;

/**
 * 寻找两个正序数组的中位数
 */
public class TwoArrayMid {

    public static void main(String[] a) {
        int[] num1 = {1,2,3,5,6};
        int[] num2 = {4,7,8,9,10};
        double v = findMedianSortedArrays(num1, num2);
        System.out.println("值为：" + v);
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        boolean flag = length % 2 == 0;
        if(flag) {
            //偶数
            int value1 = getK(nums1,nums2,length/2);
            int value2 = getK(nums1,nums2,length/2+1);
            return (double)(value1+value2)/2;
        } else {
            //奇数
            return getK(nums1,nums2,length/2+1);
        }
    }



    public static int getK(int[] arr1, int[] arr2, int k) {
        int length1 = arr1.length;
        int length2 = arr2.length;

        //处理空数组情况
        if(length1 == 0 && length2 == 0) {
            return 0;
        }
        if(length1 == 0 && length2 != 0) {
            for(int i=0; i<length2; i++) {
                if((i+1) == k) {
                    return arr2[i];
                }
            }
        }
        if(length1 != 0 && length2 == 0) {
            for(int i=0; i<length1; i++) {
                if((i+1) == k) {
                    return arr1[i];
                }
            }
        }

        if(length2 >= length1) {
            if(k <= length1) {
                return getUpMid(arr1,0,k-1,arr2,0,k-1);
            }
            if(k > length1 && k <= length2) {
                if(arr2[k - length1 -1] >= arr1[length1-1]) {
                    return arr2[k - length1 -1];
                }
                return getUpMid(arr1,0,length1-1,arr2,k - length1,k - 1);
            }
            if(k > length2 && k <= (length1+length2)) {
                //arr1,k-length2-1,length1-1.
                //arr2,k-length1-1,length2-1.
                if(arr1[k-length2-1] >= arr2[length2-1]) {
                    return arr1[k-length2-1];
                }
                if(arr2[k-length1-1] >= arr1[length1-1]) {
                    return arr2[k-length1-1];
                }
                return getUpMid(arr1,k-length2,length1-1,arr2,k - length1,length2-1);
            }
        } else {
            if(k <= length2) {
                return getUpMid(arr2,0,k-1,arr1,0,k-1);
            }
            if(k > length2 && k <= length1) {
                if(arr1[k - length2 -1] >= arr2[length2-1]) {
                    return arr1[k - length2 -1];
                }
                return getUpMid(arr2,0,length2-1,arr1,k - length2,k - 1);
            }
            if(k > length1 && k <= (length1+length2)) {
                //arr1,k-length2-1,length1-1.
                //arr2,k-length1-1,length2-1.
                if(arr2[k-length1-1] >= arr1[length1-1]) {
                    return arr2[k-length1-1];
                }
                if(arr1[k-length2-1] >= arr2[length2-1]) {
                    return arr1[k-length2-1];
                }
                return getUpMid(arr2,k-length1,length2-1,arr1,k - length2,length1-1);
            }
        }
        return -1;
    }



    //获取
    public static int getUpMid(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2) {
        int a1Length = r1 - l1 + 1;
        int a2Length = r2 - l2 + 1;

        boolean flag = a1Length % 2 == 0;
        if(flag) {
            //偶数
            int value1 = arr1[l1 + a1Length/2-1];
            int value2 = arr2[l2 + a2Length/2-1];
            if(value1 == value2) {
                return value1;
            }
            if(value1 > value2) {
                return getUpMid(arr1,l1,l1 + a1Length/2 - 1,arr2,l2 + a2Length/2,r2);
            } else {
                return getUpMid(arr2,l2,l2 + a2Length/2 - 1,arr1,l1 + a1Length/2,r1);
            }
        } else {
            //奇数
            int value1 = arr1[l1 + a1Length/2];
            int value2 = arr2[l2 + a2Length/2];
            if(value1 == value2) {
                return value1;
            }
            if(value1 > value2) {
                if(a1Length == 1) {
                    return value2;
                }
                if(value2 >= arr1[l1 + a1Length/2 - 1]) {
                    return value2;
                }

                //arr1 l1, l1 + a1Length/2 - 1
                //arr2 l2 + a2Length/2, r2
                return getUpMid(arr1,l1,l1 + a1Length/2 - 1,arr2,l2 + a2Length/2 + 1,r2);
            } else {
                if(a1Length == 1) {
                    return value1;
                }
                if(value1 >= arr2[l2 + a2Length/2 - 1]) {
                    return value1;
                }
                return getUpMid(arr2,l2,l2 + a2Length/2 - 1,arr1,l1 + a1Length/2 + 1,r1);
            }
        }
    }
}
