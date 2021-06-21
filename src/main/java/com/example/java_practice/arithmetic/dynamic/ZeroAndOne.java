package com.example.java_practice.arithmetic.dynamic;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 动态规划 0-1背包问题。7个重量不同的物体，放入总重量为10的背包，如何放置可以最接近总重量
 */
public class ZeroAndOne {


    //背包总重量
    private int maxWeight = 10;
    //物品集合
    private int[] goods = new int[7];
    //动态规划图,一维数组下标代表N号物品,二维数组下标代表重量,二维数组的值存储当前物品是否可以到达重量状态
    private int[][] dynamicTable = new int[7][11];

    public ZeroAndOne() {
        for (int i=1; i<8; i++) {
            goods[i-1] = i;
        }
    }


    /**
     * 0-1背包问题
     */
    public void zeroAndOne(int good) {
        if (good == 7) {
            return;
        }
        //上一个物品可以到达的重量
        Set<Integer> last = new HashSet<>();
        //当前物品可以到达的重量
        Set<Integer> current = new HashSet<>();
        //找出上一个物品到达的重量集合
        if (good != 0) {
            int[] table = dynamicTable[good-1];
            if (table != null && table.length != 0) {
                for (int weight=0; weight<11; weight++) {
                    int able = table[weight];
                    if (able == 1) {
                        last.add(weight);
                    }
                }
            }
        }

        //计算当前物品可以到达的符合条件的重量
        int noPutWeight = 0; //不放置当前物品的重量
        int putWeight = goods[good]; //放置当前物品的重量
        if (CollectionUtils.isEmpty(last)) {
            current.add(noPutWeight);
            //如何重量限制才放入
            if (putWeight <= maxWeight) {
                current.add(putWeight);
            }
        } else {
            last.stream().forEach(it -> {
                //不放置当前物品
                current.add(it);
                //放置当前物品
                int currentWeight = putWeight + it;
                if (currentWeight <= maxWeight) {
                    current.add(currentWeight);
                }
            });
        }
        //将当前物品可以到达的重量存入动态规划表
        for (Integer weight : current) {
            dynamicTable[good][weight] = 1;
        }
        //递归下一层
        zeroAndOne(good+1);
    }

    /**
     * 根据动态图获取最佳路线
     */
    public List<Integer> getDynamicRoute() {
        //获取最后一个物品的最佳可到达重量
        int[] table = dynamicTable[6];
        int weight = 0;
        for (int i=0; i<11; i++) {
            int able = table[i];
            if (able == 1) {
                weight = i;
            }
        }
        //根据最佳重量获取路线
        List<Integer> routes = new ArrayList<>();
        for (int i=6; i>=0; i--) {
            if (i == 0) {
                if (dynamicTable[i][weight] == 1) {
                    routes.add(goods[i]);
                }
                break;
            }
            //找出上一层的该重量是否可达
            int flag = dynamicTable[i - 1][weight];
            ////该层放置
            if (flag != 1) {
                routes.add(goods[i]);
                weight = weight - goods[i];
            }
        }
        return routes;
    }
}
