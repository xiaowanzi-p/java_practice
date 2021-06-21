package com.example.java_practice.datastructure.graph;


import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
public class Graph<T> {

    //邻接表存储的图结构，key为原始顶点(哈希表存储)，value为链表(可用红黑数、跳表代替)保存原始顶点的出度数据
    private Map<OriginNode<T>,List<DegreeNode>> map = new HashMap<>(50);
    //是否是有向图
    private boolean isDirect;

    public Graph(boolean isDirect) {
        this.isDirect = isDirect;
    }

    //originData原始顶点数据, outDegreeData原始节点的出度节点数据
    public void add(T originData, T outDegreeData, int weight) {
       if (originData == null) {
           return;
       }
       OriginNode<T> originNode = new OriginNode<>(originData);
       if (!map.containsKey(originNode)) {
           map.put(originNode,Lists.newArrayList());
       }

       if (outDegreeData != null) {
          OriginNode<T> outOriginNode = new OriginNode(outDegreeData);
          if (!map.containsKey(outOriginNode)) {
              map.put(outOriginNode,Lists.newArrayList());
          }
          DegreeNode<T> OutDegreeNode = new DegreeNode<>(outOriginNode, weight);
          map.get(originNode).add(OutDegreeNode);
          if (!isDirect) {
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
}
