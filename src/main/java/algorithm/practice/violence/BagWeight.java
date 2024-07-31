package algorithm.practice.violence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 0-1背包问题
 */
public class BagWeight {

    public static void main(String[] a) {
        int i = 2;
        int v = 1;
        double v1 = (double) (i + v) / 2;
        System.out.println(v1);
        /*int[] w = {4, 2, 9, 5, 3};
        int[] v = {7, 8, 1, 10, 2};
        int bag = 8;
        String s = "11";
        List<Integer> process = process(v, w, 0, bag, new ArrayList<>());
        for (int i : process) {
            System.out.println("背包重量:" + w[i] + ", 价值:" + v[i]);
        }
        int dpProcess = dpProcess(v, w, bag);*/

        //System.out.println("背包重量: " + dpProcess);
    }

    private static List<Integer> process(int[] v, int[] w, int index, int rest, List<Integer> list) {
        if (rest <= 0) {
            return list;
        }
        if (index >= w.length) {
            return list;
        }
        if (rest < w[index]) {
            //当前不要
            List<Integer> no = process(v,w,index+1,rest,new ArrayList<>());
            return no;
        } else {
            //当前要
            list.add(index);
            List<Integer> yes = process(v,w,index+1,rest-w[index],new ArrayList<>());
            //当前不要
            List<Integer> no = process(v,w,index+1,rest,new ArrayList<>());
            //判断价值最大的集合返回
            int noValue = 0;
            for (int i : no) {
                noValue = noValue + v[i];
            }
            int yesValue = v[index];
            for (int i : yes) {
                yesValue = yesValue + v[i];
            }
            if (noValue > yesValue) {
                return no;
            } else {
                list.addAll(yes);
                return list;
            }
        }
    }


    private static int dpProcess(int[] v, int[] w, int bag) {
        //第一个下标当前位置，第二个下标剩余背包重量
        int n = v.length;
        int[][] dp = new int[n+1][bag+1];
        for (int index=n-1; index>=0; index--) {
            for (int rest=0; rest<=bag; rest++) {
                if (rest < w[index]) {
                    dp[index][rest] = dp[index+1][rest];
                } else {
                    dp[index][rest] = Math.max(v[index] + dp[index+1][rest-w[index]],dp[index+1][rest]);
                }
            }
        }
        return dp[0][bag];
    }
}
