package algorithm.practice.sort;


import java.util.*;

/**
 * @author 君墨笑
 * @date 2024/3/21
 */
public class Demo {

    public static void main(String[] a) {
        //int duplicates = removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
        /*double process = process(1, 2, 4);
        System.out.println(process);
        String s = "111";
*/
        int[][] ints = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        merge(ints);
        ArrayList<Object> list = new ArrayList<>();
    }


    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if(o1[0] - o2[0] < 0) {
                    return -1;
                }
                if (o1[0] - o2[0] == 0) {
                    return 0;
                }
                return 1;
            }
        });

        List<int[]> list = new ArrayList();
        int min = intervals[0][0];
        int max = intervals[0][1];
        for(int i=0; i<intervals.length; i++) {
            int[] arr = intervals[i];
            int l = arr[0];
            int r = arr[1];
            if(l<=max && r<=max) {
                continue;
            }
            if(l<=max && r>max) {
                max = r;
                continue;
            }
            if(l>max) {
                list.add(new int[]{min,max});
                min = l;
                max = r;
            }
        }
        int[][] res = new int[list.size()][2];
        for(int i=0; i<list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    public static double process(double res, double x, int n) {
        if(n == 0) {
            return res;
        }
        res = res * x;
        return process(res,x,n-1);
    }


    public static int removeDuplicates(int[] nums) {
        if(nums.length == 1) {
            return 1;
        }
        int done = 0;
        int cur = 1;
        HashMap<Integer,Integer> map = new HashMap();
        map.put(nums[done],1);

        while(cur < nums.length) {
            if(nums[cur] == nums[done] && map.get(nums[done]) > 0) {
                swap(nums,cur,done+1);
                int value = map.get(nums[done]) - 1;
                map.put(nums[done],value);
                done++;
                cur++;
                continue;
            }
            if(nums[cur] == nums[done] && map.get(nums[done]) <= 0) {
                cur++;
                continue;
            }
            if(nums[cur] != nums[done]) {
                swap(nums,cur,done+1);
                done++;
                cur++;
                map.put(nums[done],1);
            }
        }
        return done+1;
    }


    public static void swap(int[] nums, int index1, int index2) {
        int tempt = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tempt;
    }


    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int m = 0;

        while(l <= r) {
            m = (l+r)/2;
            //二分查找
            if(nums[m] == target) {
                return m;
            }
            //l r m三值只要有一个不相等则可以进行二分
            //一直找到三值不相等的左下标
            if(nums[l] == nums[m] && nums[m] == nums[r]) {
                while(l != m && nums[l] == nums[m]) {
                    l++;
                }
                if(l == m) {
                    l = m + 1;
                    continue;
                }
            }

            //此处说明l m r 有一个不相等

            if(nums[l] != nums[m]) {
                //l m 不相等
                if(nums[l] > nums[m]) {
                    if(target > nums[m] && target <= nums[r]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                } else {
                    if(target >= nums[l] && target < nums[m]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }
            } else {
                //m r不相等
                if(nums[m] > nums[r]) {
                    if(target >= nums[l] && target < nums[m]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else {
                    if(target > nums[m] && target <= nums[r]) {
                        l = m + 1;
                    } else {
                        r = r - 1;
                    }
                }
            }

        }
        return -1;
    }

    public static boolean isValid(String s) {
        if(s.length() == 1) {
            return false;
        }
        Stack<Character> stack = new Stack();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            if(c == ')') {
                Character v = stack.pop();
                if(v == null || v != '(') {
                    return false;
                }
            }
            if(c == ']') {
                Character v = stack.pop();
                if(v == null || v != ']') {
                     return false;
                }
            }
            if(c == '}') {
                Character v = stack.pop();
                if(v == null || v != '}') {
                    return false;
                }
            }
        }
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
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

        char[][] str = new char[][]{
                {'a','b','c'}, //2
                {'d','e','f'}, //3
                {'g','h','i'}, //4
                {'j','k','l'}, //5
                {'m','n','o'}, //6
                {'p','q','r','s'}, //7
                {'t','u','v'}, //8
                {'w','x','y','z'} //9
        };

        char[] array = digits.toCharArray();
        char[] path = new char[array.length];
        ArrayList<String> list = new ArrayList();
        process(0,array,map,path,list);
        return list;
    }


    public static void process(int index, char[] array, HashMap<Character,char[]> map, char[] path, List<String> list) {
        if(index == path.length) {
            StringBuilder builder = new StringBuilder();
            for(int i=0; i< path.length; i++) {
                builder.append(path[i]);
            }
            list.add(builder.toString());
            return;
        }
        char number = array[index];
        char[] str2 = map.get(number);
        for(int i=0; i<str2.length; i++) {
            char value = str2[i];
            path[index] = value;
            process(index+1,array,map,path,list);
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }

        ListNode head = null;
        ListNode tail = null;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while(cur1 != null || cur2 != null) {
            ListNode target = null;
            if(cur1 == null) {
                target = cur2;
                cur2 = cur2.next;
            }
            if(cur2 == null) {
                target = cur1;
                cur1 = cur1.next;
            }
            if(cur1 != null && cur2 != null && cur1.val <= cur2.val) {
                target = cur1;
                cur1 = cur1.next;
            }
            if(cur1 != null && cur2 != null && cur1.val > cur2.val) {
                target = cur2;
                cur2 = cur2.next;
            }
            if(head == null) {
                head = new ListNode();
                head.val = target.val;
                tail = head;
            } else {
                ListNode node = new ListNode();
                node.val = target.val;
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }
}
