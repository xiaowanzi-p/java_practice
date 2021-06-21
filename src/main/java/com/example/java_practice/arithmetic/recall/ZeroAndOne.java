package com.example.java_practice.arithmetic.recall;

import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * 0-1背包问题
 */
public class ZeroAndOne {

    //最大重量限制10KG
    private int maxWeight = 10;
    //物品集合
    private int[] goods = new int[7];

    private List<List<Integer>> list = new ArrayList<>();
    private int[] save = new int[10];

    public ZeroAndOne() {
        for (int i=1; i<8; i++) {
            goods[i-1] = i;
        }
    }

    public void pick(int index) {
        //递归条件,当物品探查完毕或者不符合预期结果条件
        if (index == 7 || !isOk(index)) {
            addList();
            return;
        }
        for (int i=0; i<2; i++) {
            //第一次放置，第二次不放置
            if (i == 0) {
                save[index] = goods[index];
                pick(index+1);
            } else {
                save[index] = 0;
                pick(index+1);
            }
        }
    }

    //挑选出来最大的组合
    public List<Integer> pickMax() {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        for (List<Integer> l : list) {
            int total = 0;
            for (Integer i : l) {
                total = total + i;
            }
            map.put(total,l);
        }

        Integer key = map.lastKey();
        return map.get(key);
    }

    public void print() {
        for (List<Integer> l : list) {
            System.out.println(JSONObject.toJSONString(l));
            System.out.println();
        }
    }

    //是否可以放置
    private boolean isOk(int index) {
        int total = 0;
        for (int i : save) {
            total = total + i;
        }
        int good = goods[index];
        if ((total + good) > maxWeight) {
            return false;
        }
        return true;
    }

    private void addList() {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i : save) {
            data.add(i);
        }
        list.add(data);
    }
}
