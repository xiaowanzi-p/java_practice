package com.example.java_practice.datastructure.graph;


import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Getter
public class Graph<T> {

    //邻接表存储的图结构，key为原始顶点(哈希表存储)，value为链表(可用红黑数、跳表代替)保存原始顶点的出度数据
    private Map<OriginNode<T>,List<DegreeNode>> map = new HashMap<>(50);
    //是否是有向图
    private boolean isDirect;
    //首顶点
    private OriginNode<T> head;

    public Graph(boolean isDirect) {
        this.isDirect = isDirect;
    }

    //originData原始顶点数据, outDegreeData原始节点的出度节点数据
    public void add(T originData, T outDegreeData, int weight) {
       if (originData == null) {
           return;
       }
       OriginNode<T> originNode = new OriginNode<>(originData);
       if (map.isEmpty()) {
           head = originNode;
       }
       if (!map.containsKey(originNode)) {
           map.put(originNode,Lists.newArrayList());
       }

       if (outDegreeData != null) {
          OriginNode<T> outOriginNode = new OriginNode(outDegreeData);
          DegreeNode<T> OutDegreeNode = new DegreeNode<>(outOriginNode, weight);
          map.get(originNode).add(OutDegreeNode);
          if (!isDirect) {
              if (!map.containsKey(outOriginNode)) {
                  map.put(outOriginNode,Lists.newArrayList());
              }
              DegreeNode<T> inDegreeNode = new DegreeNode<>(originNode, weight);
              map.get(outOriginNode).add(inDegreeNode);
          }
       }
    }

    //移除图的边联系数据originData原始顶点，outDegreeData需要移除的出度顶点
    public void remove(T originData, T outDegreeData) {
        OriginNode<T> originNode = new OriginNode<>(originData);
        OriginNode outNode = new OriginNode(outDegreeData);
        map.get(originNode).remove(outNode);
        if (!isDirect) {
            map.get(outNode).remove(originNode);
        }
    }

    //BFS广度优先搜索
    public String bfs(T target) {
        //待遍历节点集合
        LinkedList<T> waitList = new LinkedList<>();
        //记录已经遍历访问过的节点
        Map<T, Object> visited = new HashMap<>();
        //到目标节点的路径
        Map<T, T> route = new HashMap<>();
        //先找第一个顶点
        waitList.add(head.data);
        visited.put(head.data,1);
        //遍历查找节点集合
        while (!CollectionUtils.isEmpty(waitList)) {
            T data = waitList.removeFirst();
            OriginNode node = new OriginNode(data);
            //查找下一个度的节点
            List<DegreeNode> list = map.get(node);
            if (!CollectionUtils.isEmpty(list)) {
                list.stream().forEach(it -> {
                    T t = (T) it.getData().data;
                    if (visited.get(t) == null) {
                        visited.put(t,1);
                        waitList.add(t);
                        route.put(t,data);
                    }
                });
            }
        }

        //打印路径
        String result = printBfs(target, route);
        return result;
    }

    //获取图的入度列表
    public Map<OriginNode<T>,List<DegreeNode>> inDegreeList() {
        //获取所有节点
        Set<OriginNode<T>> set = new HashSet<>();
        Set<Map.Entry<OriginNode<T>, List<DegreeNode>>> entries = map.entrySet();
        for (Map.Entry<OriginNode<T>, List<DegreeNode>> entry : entries) {
            OriginNode<T> key = entry.getKey();
            List<DegreeNode> value = entry.getValue();
            set.add(key);
            value.stream().forEach(it -> set.add(it.data));
        }

        Map<OriginNode<T>,List<DegreeNode>> degreeMap = new HashMap<>();
        //入度列表
        for (OriginNode<T> node : set) {
            List<DegreeNode> list = new ArrayList<>();
            for (Map.Entry<OriginNode<T>, List<DegreeNode>> entry : entries) {
                OriginNode<T> key = entry.getKey();
                List<DegreeNode> value = entry.getValue();
                DegreeNode<T> degreeNode = new DegreeNode<>(node, 0);
                if (value.contains(degreeNode)) {
                    list.add(new DegreeNode(key,0));
                }
            }
            degreeMap.put(node,list);
        }

        return degreeMap;
    }


    //原始数据节点
    @Getter
    @Setter
    public static class OriginNode<T> {
        //数据
        private T data;
        public OriginNode(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OriginNode<?> that = (OriginNode<?>) o;
            return data.equals(that.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    //度节点
    @Getter
    @Setter
    public static class DegreeNode<T> {
        //度节点数据指针
        private OriginNode<T> data;
        //与原始节点的权重，出度才有
        private int weight;

        public DegreeNode(OriginNode<T> data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DegreeNode<?> that = (DegreeNode<?>) o;
            return Objects.equals(data, that.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }


    private String printBfs(T target, Map<T, T> route) {
        List<T> list = new ArrayList<>();
        list.add(target);
        T pre = route.get(target);
        while (!pre.equals(head.data)) {
            list.add(pre);
            pre = route.get(pre);

        }
        list.add(pre);

        StringBuilder builder = new StringBuilder();
        for (int i=list.size()-1; i>=0; i--) {
            builder.append(list.get(i));
            if (i != 0) {
                builder.append("->");
            }
        }
        return builder.toString();
    }
}
