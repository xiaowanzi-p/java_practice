package algorithm.practice.violence;

import java.util.ArrayList;
import java.util.List;

/**
 * 0-1背包问题
 */
public class BagWeight {

    public static void main(String[] a) {
        int[] w = {4, 2, 9, 5, 3};
        int[] v = {7, 8, 1, 10, 2};
        int bag = 1;
        List<Integer> process = process(v, w, 0, bag, new ArrayList<>());
        for (int i : process) {
            System.out.println("背包重量:" + w[i] + ", 价值:" + v[i]);
        }
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
}
