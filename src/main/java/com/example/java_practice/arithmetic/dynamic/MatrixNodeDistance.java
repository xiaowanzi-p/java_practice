package com.example.java_practice.arithmetic.dynamic;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 矩阵元素节点最短距离问题
 */
public class MatrixNodeDistance {

    //初始矩阵
    int[][] matrix = new int[4][4];
    //动态规划表
    int[][] dynamicTable = new int[4][4];
    //上一个阶段所有矩阵节点最短距离
    private Map<Pair,Integer> map = new HashMap<>();
    //当前阶段所有矩阵节点的最短距离
    private Map<Pair,Integer> currentMap = new HashMap<>();
    //目标节点
    private Pair<Integer,Integer> target = Pair.of(3,3);

    public MatrixNodeDistance() {
        for (int i=0; i<4; i++) {
            int[] node = this.matrix[i];
            for (int j=0; j<4; j++) {
                int number = new Random().nextInt(10);
                node[j] = number;
            }
        }
    }

    //动态规划矩阵距离——状态转移表
    //总 stage = 2*(n-1), 0开始
    public void dynamicDistance(int stage) {
        //递归结束条件,已经包含目标矩阵节点则返回
        if (map.containsKey(target) || stage == 7) {
            return;
        }

        if (map.isEmpty()) {
            int distance = this.matrix[0][0];
            dynamicTable[0][0] = distance;
            map.put(Pair.of(0,0),distance);
            //递归下一个阶段
            dynamicDistance(stage+1);
        } else {
            //根据上一个阶段的节点计算出当前阶段的节点最短距离
            for (Map.Entry<Pair,Integer> entry : map.entrySet()) {
                Pair<Integer,Integer> key = entry.getKey();
                Integer preDistance = entry.getValue();
                Integer raw = key.getLeft();
                Integer colum = key.getRight();
                //右移
                if (colum < 3) {
                    int rightDistance = this.matrix[raw][colum + 1];
                    addCurrent(Pair.of(raw,colum+1),preDistance+rightDistance);
                }
                //下移
                if (raw < 3) {
                    int downDistance = this.matrix[raw + 1][colum];
                    addCurrent(Pair.of(raw+1,colum),preDistance+downDistance);
                }
            }
            //交换当前状态值为之前状态值，并清空之前状态值
            Map<Pair,Integer> tempt = map;
            map = currentMap;
            currentMap = tempt;
            currentMap.clear();
            //递归下一个阶段
            dynamicDistance(stage+1);
        }
    }

    //打印原始矩阵
    public void printMatrix() {
        for (int i=0; i<4; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j=0; j<4; j++) {
                int data = this.matrix[i][j];
                builder.append(data);
                builder.append(" ");
            }
            System.out.println(builder.toString());
        }
    }

    //获取目标node的最短路径
    public int getTargetDistance() {
        return dynamicTable[target.getLeft()][target.getRight()];
    }

    //只加入最短的距离
    private boolean addCurrent(Pair<Integer,Integer> key, Integer distance) {
        boolean flag = false;
        Integer value = currentMap.get(key);
        if (value == null || (distance.intValue() < value.intValue())) {
            currentMap.put(key,distance);
            //填充动态规划表
            dynamicTable[key.getLeft()][key.getRight()] = distance;
            flag = true;
        }
        return flag;
    }

    private void addNodeRoute(Pair<Integer,Integer> current, Pair<Integer,Integer> next) {

    }
}
