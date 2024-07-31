package algorithm.practice.leetcode;

/**
 * 反转整数
 */
public class ReverseInt {

    public static void main(String[] a) {
        int process = process(1463847412);
        System.out.println("结果为:" + process);
    }


    public static int process(int x) {
        //全部转成负数计算
        boolean flag = x >= 0 ? true : false;
        x = flag ? -x : x;
        int m = Integer.MIN_VALUE/10;
        int o = Integer.MIN_VALUE%10;
        int v = Integer.MAX_VALUE;
        String s = "111";
        int res = 0;
        while(x != 0) {
            if(res < m || (res == m && x%10 < o)) {
                return 0;
            }
            res = res*10 + x%10;
            x = x/10;
        }
        if(flag) {
            res = Math.abs(res);
        }
        return res;
    }
}
