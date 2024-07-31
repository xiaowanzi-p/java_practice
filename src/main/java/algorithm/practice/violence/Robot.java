package algorithm.practice.violence;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机器人走路问题
 * 1-N的位置
 * 初始机器人在M位置
 * 走到目标点P
 * 规定K步
 * 有多少种解法
 */
public class Robot {

    private static Map<String,Integer> map = new HashMap<>();

    public static void main(String[] a) {
        long start = System.currentTimeMillis();
        int[] num = {0};
        process(5,3,2,3,num);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("解法为:" + num[0] + " 耗时为：" + time);
    }


    /**
     * @param N 固定的范围
     * @param P 目标点的位置
     * @param cur 当前所在位置
     * @param rest 剩余多少步
     * @param num 将解法存入其中
     */
    private static void process(int N, int P, int cur, int rest, int[] num) {
        //没有剩余步数且正好走到到P位置，则算一种解法
        if (rest <= 0) {
            if (cur == P) {
                num[0] = num[0] + 1;
            }
            return;
        }
        //走到了1位置,只能往右走到2
        if (cur == 1) {
            process(N,P,2,rest-1,num);
            return;
        }
        //走到了N位置
        if (cur == N) {
            process(N,P,N-1,rest-1,num);
            return;
        }
        //其余不是边界位置的情况
        //向左走
        process(N,P,cur-1,rest-1,num);
        //向右走
        process(N,P,cur+1,rest-1,num);
    }


    /**
     * 加上缓存
     *
     * @param N 固定的范围
     * @param P 目标点的位置
     * @param cur 当前所在位置
     * @param rest 剩余多少步
     * @param num 将解法存入其中
     */
    private static void process2(int N, int P, int cur, int rest, int[] num) {
        String key = cur + "-" + rest;
        Integer value = map.get(key);
        if (value != null) {
            num[0] = num[0] + value;
            return;
        }
        //没有剩余步数且正好走到到P位置，则算一种解法
        if (rest <= 0) {
            if (cur == P) {
                num[0] = num[0] + 1;
            }
            map.put(key,num[0]);
            return;
        }
        //走到了1位置,只能往右走到2
        if (cur == 1) {
            process(N,P,2,rest-1,num);
            map.put(key,num[0]);
            return;
        }
        //走到了N位置
        if (cur == N) {
            process(N,P,N-1,rest-1,num);
            map.put(key,num[0]);
            return;
        }
        //其余不是边界位置的情况
        //向左走
        process(N,P,cur-1,rest-1,num);
        //向右走
        process(N,P,cur+1,rest-1,num);
        map.put(key,num[0]);
    }
}
