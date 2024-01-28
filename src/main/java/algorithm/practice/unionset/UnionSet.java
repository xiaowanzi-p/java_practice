package algorithm.practice.unionset;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 并查集
 */
public class UnionSet {

    public static void main(String[] a) {
        ArrayList<Integer> list = Lists.newArrayList(5, 2, 7, 3, 5, 8);
        UnionSet unionSet = new UnionSet(list);
        System.out.println("是否在一个集合: " + unionSet.isSameCollect(4, 2));
        System.out.println("是否在一个集合: " + unionSet.isSameCollect(5, 2));
        unionSet.unionCollect(5,2);
        System.out.println("是否在一个集合: " + unionSet.isSameCollect(5, 2));
    }

    //点集
    private HashMap<Integer,Node> nodeMap = new HashMap<>();
    //节点父节点集合
    private HashMap<Node,Node> parentMap = new HashMap<>();
    //代表节点的集合大小
    private HashMap<Node,Integer> sizeMap = new HashMap<>();

    private static class Node {
        private int data;
        public Node(int data) {
            this.data = data;
        }
    }


    public UnionSet(List<Integer> list) {
        //初始化并查集
        for (Integer data : list) {
            Node node = new Node(data);
            nodeMap.put(data,node);
            parentMap.put(node,node);
            sizeMap.put(node,1);
        }
    }


    /**
     * 是否是相同集合
     */
    public boolean isSameCollect(int a, int b) {
        //元素不在并查集内
        if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) {
            return false;
        }
        return findFather(a) == findFather(b);
    }


    /**
     * 合并两个元素
     */
    public void unionCollect(int a, int b) {
        if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) {
            return;
        }
        Node father1 = findFather(a);
        Node father2 = findFather(b);
        Integer size1 = sizeMap.get(father1);
        Integer size2 = sizeMap.get(father2);
        if (size1 <= size2) {
            parentMap.put(father1,father2);
            sizeMap.remove(father1);
        } else {
            parentMap.put(father2,father1);
            sizeMap.remove(father2);
        }
    }


    private Node findFather(int data) {
        Node node = nodeMap.get(data);
        Node father = parentMap.get(node);
        Queue<Node> queue = new LinkedList<>();
        while (node != father) {
            node = father;
            father = parentMap.get(node);
            queue.add(node);
        }
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            parentMap.put(poll,father);
        }
        return father;
    }

}
