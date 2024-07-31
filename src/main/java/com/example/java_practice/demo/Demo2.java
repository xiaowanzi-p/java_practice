package com.example.java_practice.demo;

import algorithm.practice.tree.TreeNode;

import java.util.*;

public class Demo2 {

    public static void main(String[] a) {
        String minWindow = minWindow("ADOBECODEBANC", "ABC");
        System.out.println("结果：" + minWindow);
        LinkedList<Object> list = new LinkedList<>();
        

    }


    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList();
        }
        List<List<Integer>> res = new ArrayList();
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        boolean flag = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList();
            for(int i = 0; i<size; i++) {
                if(flag) {
                    TreeNode node = queue.pollLast();
                    list.add(node.val);
                    if(node.left != null) {
                        queue.addFirst(node.left);
                    }
                    if(node.right != null) {
                        queue.addFirst(node.right);
                    }
                } else {
                    TreeNode node = queue.pollFirst();
                    list.add(node.val);
                    if(node.right != null) {
                        queue.addLast(node.right);
                    }
                    if(node.left != null) {
                        queue.addLast(node.left);
                    }
                }
            }
            flag = !flag;
            res.add(list);
        }
        return res;
    }


    public static String minWindow(String s, String t) {
        char[] arr = t.toCharArray();
        //欠债表
        int all = arr.length;
        Map<Character,Integer> map = new HashMap();
        for(char ch : arr) {
            Integer count = map.get(ch);
            if(count == null) {
                map.put(ch,1);
            } else {
                count++;
                map.put(ch,count);
            }
        }
        //滑动窗口
        char[] target = s.toCharArray();
        int left = 0;
        int right = 0;
        int minSize = 9999;
        int[] index = new int[2];

        while(left <= right) {
            //结算
            if(all <= 0) {
                int length = right-left;
                if(length < minSize) {
                    index[0] = left;
                    index[1] = right;
                    minSize = length;
                }
                //归还
                if(map.containsKey(target[left])) {
                    int count = map.get(target[left]);
                    map.put(target[left],count+1);
                    all++;
                }
                //左滑动
                left++;
                continue;
            }

            if(map.containsKey(target[right])) {
                int count = map.get(target[right]);
                map.put(target[right],count-1);
                if (count-1>=0) {
                    all--;
                }
            }
            //边界处理
            if(right == target.length-1) {
                left++;
                continue;
            }
            right++;
        }

        if(minSize == -1) {
            return "";
        }
        return s.substring(index[0],index[1]);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        //奇数偶数
        boolean flag = (length1 + length2) % 2 == 0;
        if(flag) {
            int val1 = getK(nums1,nums2,(length1 + length2)/2);
            int val2 = getK(nums1,nums2,(length1 + length2)/2 + 1);
            return (double) (val1 + val2) / 2;
        } else {
            int val2 = getK(nums1,nums2,(length1 + length2)/2 + 1);
            return (double) val2;
        }
    }

    //获取排序数组中排名K的元素
    public static int getK(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int max = Math.max(length1,length2);
        int min = Math.min(length1,length2);
        if(k <= min) {
            return getUpMid(nums1,0,k-1,nums2,0,k-1);
        }
        if(k > min & k <= max) {
            int left = k - min - 1;
            int right = left + k - 1;
            if(min == length1) {
                if(nums2[left] >= nums1[length1-1]) {
                    return nums2[left];
                }
                return getUpMid(nums1,0,length1-1,nums2,left+1,right);
            }
            if(min == length2) {
                if(nums1[left] >= nums2[length2-1]) {
                    return nums1[left];
                }
                return getUpMid(nums2,0,length2-1,nums1,left+1,right);
            }
        }
        if(k > max && k <= (min + max)) {
            int maxLeft = k - min - 1;
            int minLeft = k - max - 1;
            if(min == length1) {
                if(nums1[minLeft] >= nums2[length2-1]) {
                    return nums1[minLeft];
                }
                if(nums2[maxLeft] >= nums1[length1-1]) {
                    return nums2[maxLeft];
                }
                return getUpMid(nums1,minLeft+1,length1-1,nums2,maxLeft+1,length2-1);
            }
            if(min == length2) {
                if(nums2[minLeft] >= nums1[length1-1]) {
                    return nums2[minLeft];
                }
                if(nums1[maxLeft] >= nums2[length2-1]) {
                    return nums1[maxLeft];
                }
                return getUpMid(nums2,minLeft+1,length2-1,nums1,maxLeft+1,length1-1);
            }
        }
        return -1;
    }

    //获取等长数组
    public static int getUpMid(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {
        //奇数偶数长度
        boolean even = (r1 - l1 + 1) % 2 == 0;
        if(even) {
            //上中位下标
            int mid1 = (r1 - l1) / 2 + l1;
            int mid2 = (r2 - l2) / 2 + l2;
            if(nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            }
            if(nums1[mid1] > nums2[mid2]) {
                return getUpMid(nums1,l1,mid1,nums2,mid2+1,r2);
            } else {
                return getUpMid(nums2,l2,mid2,nums1,mid1+1,r1);
            }
        } else {
            //上中位下标
            int mid1 = (r1 - l1) / 2 + l1;
            int mid2 = (r2 - l2) / 2 + l2;
            if(nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            }
            if(nums1[mid1] > nums2[mid2]) {
                if((mid1-1) < l1) {
                    return nums2[mid2];
                }
                if(nums2[mid2] >= nums1[mid1-1]) {
                    return nums2[mid2];
                } else {
                    return getUpMid(nums1,l1,mid1-1,nums2,mid2+1,r2);
                }
            } else {
                if((mid2-1) < l2) {
                    return nums1[mid1];
                }
                if(nums1[mid1] >= nums2[mid2-1]) {
                    return nums1[mid1];
                } else {
                    return getUpMid(nums2,l2,mid2-1,nums1,mid1+1,r1);
                }
            }
        }
    }
}
