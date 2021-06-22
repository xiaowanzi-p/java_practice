package com.example.java_practice.arithmetic.kahn;

import com.example.java_practice.datastructure.graph.Graph;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 拓扑排序
 */
public class Kahn {

    //有向图
    private Graph<String> graph = new Graph<>(true);

    //初始化图内数据
    public void initData() {
        graph.add("内裤","裤子",0);
        graph.add("鞋子","内裤",0);
        //graph.add("内裤","鞋子",0); 此处加上则是环
        graph.add("裤子","鞋子",0);
        graph.add("裤子","腰带",0);
        graph.add("袜子","鞋子",0);
        graph.add("衬衣","外套",0);
        graph.add("衬衣","领带",0);
    }

    public String sort() {
        //找出图的入度列表
        Map<Graph.OriginNode<String>, List<Graph.DegreeNode>> inDegreeList = graph.inDegreeList();
        StringBuilder builder = new StringBuilder();
        //查找入度为0的节点
        while (inDegreeList != null && inDegreeList.size() != 0) {
            Set<Map.Entry<Graph.OriginNode<String>, List<Graph.DegreeNode>>> entries = inDegreeList.entrySet();
            Graph.OriginNode<String> key = null;

            for (Map.Entry<Graph.OriginNode<String>, List<Graph.DegreeNode>> entry : entries) {
                List<Graph.DegreeNode> value = entry.getValue();
                if (CollectionUtils.isEmpty(value)) {
                    key = entry.getKey();
                    break;
                }
            }
            if (key != null) {
                builder.append(key.getData());
                builder.append("->");
                //在inDegreeList中去除该key
                removeInDegree(inDegreeList,key);
            } else {
                break;
            }
        }

        return builder.toString();
    }

    //移除入度
    private void removeInDegree(Map<Graph.OriginNode<String>, List<Graph.DegreeNode>> inDegreeList, Graph.OriginNode<String> key) {
        inDegreeList.remove(key);
        Set<Map.Entry<Graph.OriginNode<String>, List<Graph.DegreeNode>>> entries = inDegreeList.entrySet();
        Graph.DegreeNode<String> keyNode = new Graph.DegreeNode<>(key, 0);
        if (!CollectionUtils.isEmpty(entries)) {
            for (Map.Entry<Graph.OriginNode<String>, List<Graph.DegreeNode>> entry : entries) {
                List<Graph.DegreeNode> value = entry.getValue();
                if (value.contains(keyNode)) {
                    value.remove(keyNode);
                }
            }
        }
    }
}
