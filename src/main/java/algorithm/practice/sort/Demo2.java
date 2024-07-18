package algorithm.practice.sort;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author 君墨笑
 * @date 2024/3/27
 */
public class Demo2 {


    public static void main(String[] a) {
        //int search = search(new int[]{3, 1}, 1);
        longestPalindrome("babad");
    }


    public static String longestPalindrome(String s) {
        if(s.length() <= 1) {
            return s;
        }
        //处理字符串使其一定是对称的
        StringBuilder builder = new StringBuilder();
        builder.append("#");
        for(int i=0; i<s.length(); i++) {
            builder.append(s.charAt(i));
            builder.append("#");
        }
        String target = builder.toString();
        //以每个index为下标的字符作为中心店，左右两边扩散
        int max = -1;
        int center = -1;
        for(int index=0; index<target.length(); index++) {
            int left = index-1;
            int right = index+1;
            while(left >= 0 && right <= target.length()-1) {
                if(target.charAt(left) == target.charAt(right)) {
                    //结算最大长度
                    int size = right-left+1;
                    if(size > max) {
                        max = size;
                        center = index;
                    }
                    left--;
                    right++;
                }
                break;
            }
        }
        if(max == -1) {
            return "";
        }
        String process = target.substring(center-max/2,center+1+max/2);
        StringBuilder res = new StringBuilder();
        for(int i=0; i<process.length(); i++) {
            if(process.charAt(i) != '#') {
                res.append(process.charAt(i));
            }
        }
        return res.toString();
    }

    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        //二分法
        int left = 0;
        int right = nums.length-1;
        while(left <= right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) {
                return mid;
            }

            if(nums[0] < nums[mid]) {
                //左边有序，继续二分
                if(nums[0] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //右边有序，继续二分
                if(nums[mid] <= target && target <= nums[nums.length-1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }






    public static int removeDuplicates(int[] nums) {
        if(nums.length == 1) {
            return 1;
        }
        int done = 0;
        int next = 1;
        int count = 1;
        while(next < nums.length) {
            if(nums[done] != nums[next]) {
                swap(nums,done+1,next);
                done++;
                count = 1;
            }
            if(nums[done] == nums[next] && count < 2) {
                swap(nums,done+1,next);
                done++;
                count++;
            }
            next++;
        }
        return done+1;
    }


    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static List<String> letterCombinations(String digits) {
        if(digits == null || digits == "") {
            return null;
        }

        HashMap<Character,char[]> map = new HashMap();
        map.put('2',new char[]{'a','b','c'});
        map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'});
        map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'});
        map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'});
        map.put('9',new char[]{'w','x','y','z'});

        List<String> res = new ArrayList();
        char[] digitsChar = digits.toCharArray();
        char[] path = new char[digitsChar.length];
        process(digitsChar,map,0,path,res);
        return res;
    }


    public static void process(char[] digits, HashMap<Character,char[]> map, int index, char[] path, List<String>  res) {
        if(index == path.length) {
            res.add(String.valueOf(path));
            return;
        }

        char digit = digits[index];
        char[] nums = map.get(digit);
        for(int i=0; i<nums.length; i++) {
            path[index] = nums[i];
            process(digits,map,index+1,path,res);
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode head = new ListNode();
        ListNode tail = head;
        //是否初始化头结点
        boolean flag = false;
        //10进制位的数量
        int ten = 0;
        while(cur1 != null || cur2 != null) {
            int val = 0;
            if(cur1 != null && cur2 != null) {
                val = (cur1.val + cur2.val + ten) % 10;
                ten = (cur1.val + cur2.val + ten) / 10;
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            if(cur1 != null && cur2 == null) {
                val = (cur1.val + ten) % 10;
                ten = (cur1.val + ten) / 10;
                cur1 = cur1.next;
            }
            if(cur2 != null && cur1 == null) {
                val = (cur2.val + ten) % 10;
                ten = (cur2.val + ten) / 10;
                cur2 = cur2.next;
            }
            if(!flag) {
                head.val = val;
                flag = true;
            } else {
                ListNode node = new ListNode();
                node.val = val;
                tail.next = node;
                tail = node;
            }
        }
        if(ten != 0) {
            ListNode node = new ListNode();
            node.val = ten;
            tail.next = node;
            tail = node;
        }
        return head;
    }


    public static boolean wordBreak(String s, List<String> wordDict) {
        return process(s,0,wordDict);
    }

    public static boolean process(String s, int index, List<String> wordDict) {
        if(index >= s.length()) {
            return true;
        }
        boolean flag = false;
        for(int i=index; i<s.length(); i++) {
            String val = s.substring(index,i+1);
            if(!wordDict.contains(val)) {
                continue;
            }
            flag = process(s,i+1,wordDict);
        }
        return flag;
    }


    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> pre = null;
        for(int i=1; i<=numRows; i++) {
            List<Integer> list = new ArrayList();
            list.add(0,1);
            if (i != 1) {
                list.add(i-1,1);
            }
            if(pre != null && i>2) {
                for(int j=1; j<=i-2; j++) {
                    list.add(j,pre.get(j) + pre.get(j-1));
                }
            }
            pre = list;
            res.add(list);
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        return process(nums,0);
    }

    public static List<List<Integer>> process(int[] nums, int target) {
        Arrays.sort(nums);
        int index = 0;
        List<List<Integer>> res = new ArrayList();
        while(index < nums.length) {
            if(nums.length - index < 3) {
                break;
            }
            if(index != 0 && nums[index] == nums[index-1]) {
                index++;
                continue;
            }
            List<List<Integer>> list = twoSum(nums,index+1,nums.length-1,target-nums[index]);
            if(list != null && list.size() != 0) {
                for(List<Integer> val : list) {
                    val.add(nums[index]);
                    res.add(val);
                }
            }
            index++;
        }
        return res;
    }



    public static List<List<Integer>> twoSum(int[] nums, int l, int r, int target) {
        List<List<Integer>> res = new ArrayList();
        int ori = l;
        while(l < r) {
            if(l != ori && nums[l-1] == nums[l]) {
                l++;
                continue;
            }
            int val = nums[l] + nums[r];
            if(val > target) {
                r--;
            }
            if(val < target) {
                l++;
            }
            if(val == target) {
                List<Integer> list = new ArrayList();
                list.add(nums[l]);
                list.add(nums[r]);
                res.add(list);
            }
            l++;
        }
        return res;
    }
}
