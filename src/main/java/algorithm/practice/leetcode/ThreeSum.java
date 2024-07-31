package algorithm.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class ThreeSum {

    public static void main(String[] a) {
        //List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        String s = "1";
        String substring = s.substring(0, 0);
        System.out.println(substring);
        Character character = 'A';


    }

    public static List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums,0);
    }


    public static List<List<Integer>> threeSum(int[] nums, int target) {
        //从小到大排序
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList();
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > target || (nums.length - i) < 3) {
                continue;
            }
            if(i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int value = target - nums[i];
            List<List<Integer>> arr = twoSum(nums,i+1,nums.length-1,value);
            if(arr != null && arr.size() != 0) {
                for(List<Integer> v : arr) {
                    v.add(nums[i]);
                    list.add(v);
                }
            }
        }
        return list;
    }

    public static List<List<Integer>> twoSum(int[] nums, int l, int r, int target) {
        ArrayList<List<Integer>> list = new ArrayList();
        int ori = l;
        while(l < r) {
            int value = nums[l] + nums[r];
            if(value > target) {
                r--;
            }
            if(value < target) {
                l++;
            }
            if(value == target) {
                if(l == ori) {
                    ArrayList<Integer> arr = new ArrayList();
                    arr.add(nums[l]);
                    arr.add(nums[r]);
                    list.add(arr);
                    l++;
                    continue;
                }
                if(nums[l-1] != nums[l]) {
                    ArrayList<Integer> arr = new ArrayList();
                    arr.add(nums[l]);
                    arr.add(nums[r]);
                    list.add(arr);
                }
                l++;
            }
        }
        return list;
    }
}
