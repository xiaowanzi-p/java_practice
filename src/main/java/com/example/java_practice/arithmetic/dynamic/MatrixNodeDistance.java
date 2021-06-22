package com.example.java_practice.arithmetic.dynamic;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;

import java.util.*;


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
    //目标节点的路线
    private List<Pair<Integer,Integer>> route = new ArrayList<>();

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

    //动态规划矩阵距离-状态转移方程法
    //min_distance(row,colum) = distance(row,colum) + min[min_distance(row-1,colum),min_distance(row,colum-1)]
    public Integer dynamicDistance() {
        route.clear();
        Integer distance = minDistance(target);
        return distance;
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

    //获取目标node
    public String getTargetRoute() {
        targetRoute(target);
        int size = route.size();
        StringBuilder builder = new StringBuilder();
        for (int i=size-1; i>=0; i--) {
            Pair<Integer, Integer> node = route.get(i);
            int data = this.matrix[node.getLeft()][node.getRight()];
            builder.append(data);
            if (i != 0) {
                builder.append("->");
            }
        }
        return builder.toString();
    }

    private void targetRoute(Pair<Integer,Integer> target) {
        //递归结束条件
        if (target == null) {
            return;
        }

        //加入节点
        route.add(target);

        Integer row = target.getLeft();
        Integer colum = target.getRight();
        //倒推向左移
        int left = colum - 1;
        Integer leftDistance = null;
        if (left >= 0) {
            leftDistance = dynamicTable[row][left];
        }

        //倒推向上移
        int up = row - 1;
        Integer upDistance = null;
        if (up >= 0) {
            upDistance = dynamicTable[up][colum];
        }

        //判断路径最小的节点加入
        Pair<Integer,Integer> next = null;
        if (leftDistance == null && upDistance != null) {
            next=  Pair.of(up,colum);
        }
        if (leftDistance != null && upDistance == null) {
            next =  Pair.of(row,left);
        }
        if (leftDistance != null && upDistance != null) {
            if (leftDistance < upDistance) {
                next =  Pair.of(row,left);
            } else {
                next =  Pair.of(up,colum);
            }
        }
        //递归下一个节点
        targetRoute(next);
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

    //递归的计算最短距离
    private Integer minDistance(Pair<Integer,Integer> target) {
        Integer row = target.getLeft();
        Integer colum = target.getRight();
        //递归结束条件
        if (row < 0 || colum < 0) {
            return null;
        }

        int distance = this.matrix[row][colum];
        //左移
        Integer leftDistance = minDistance(Pair.of(row,colum - 1));
        //上移
        Integer upDistance = minDistance(Pair.of(row - 1, colum));
        //最短的距离
        Integer min = min(leftDistance, upDistance);
        //填充动态规划表
        dynamicTable[row][colum] = distance + min;
        return distance + min;
    }

    //计算得出最小的距离
    private Integer min(Integer leftDistance, Integer upDistance) {
        if (leftDistance == null && upDistance != null) {
            return upDistance;
        }
        if (leftDistance != null && upDistance == null) {
            return leftDistance;
        }
        if (leftDistance != null && upDistance != null) {
            if (leftDistance < upDistance) {
                return leftDistance;
            } else {
                return upDistance;
            }
        }
        return 0;
    }
}
