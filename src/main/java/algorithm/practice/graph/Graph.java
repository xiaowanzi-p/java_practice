package algorithm.practice.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 图
 */
@Getter
@Setter
public class Graph {

    public static void main(String[] a) {
        Node node1 = new Node(1,0,2);
        Node node2 = new Node(5,1,2);
        Node node3 = new Node(6,2,1);
        Node node4 = new Node(7,1,1);
        Node node5 = new Node(8,2,0);
        Edge edge1 = new Edge(node1, node2, 2);
        Edge edge2 = new Edge(node1, node3, 5);
        Edge edge3 = new Edge(node2, node3, 10);
        Edge edge4 = new Edge(node2, node4, 7);
        Edge edge5 = new Edge(node4, node5, 13);
        Edge edge6 = new Edge(node3, node5, 11);
        node1.setNext(Lists.newArrayList(node2,node3));
        node1.setEdges(Lists.newArrayList(edge1,edge2));
        node2.setNext(Lists.newArrayList(node3,node4));
        node2.setEdges(Lists.newArrayList(edge3,edge4));
        node3.setNext(Lists.newArrayList(node5));
        node3.setEdges(Lists.newArrayList(edge6));
        node4.setNext(Lists.newArrayList(node5));
        node4.setEdges(Lists.newArrayList(edge5));

        Map<Integer, Node> map = new HashMap<>();
        map.put(1,node1);
        map.put(5,node2);
        map.put(6,node3);
        map.put(7,node4);
        map.put(8,node5);
        Graph graph = new Graph();
        graph.setNodeMap(map);
        graph.setEdges(Sets.newHashSet(edge1,edge2,edge3,edge4,edge5,edge6));
        List<Node> bfs = graph.bfs(1);
        List<Node> dfs = graph.dfs(1);
        for (Node node : bfs) {
            System.out.print(node.getData());
        }
        System.out.println("=========");
        for (Node node : dfs) {
            System.out.print(node.getData());
        }
        System.out.println("=========");
        List<Node> tpSort = graph.tpSort();
        for (Node node : tpSort) {
            System.out.print(node.getData());
        }
        System.out.println("=========");
        Map<Node, Integer> dijkstra = graph.dijkstra(1);
        for (Map.Entry<Node,Integer> it : dijkstra.entrySet()) {
            System.out.println("最短距离 " + it.getKey().getData() + ":" + it.getValue());
        }
    }


    //点集
    private Map<Integer,Node> nodeMap = new HashMap<>();
    //边集
    private Set<Edge> edges = new HashSet<>();


    /**
     * 宽度优先遍历
     */
    public List<Node> bfs(int data) {
        if (!nodeMap.containsKey(data)) {
            return null;
        }
        Node node = nodeMap.get(data);
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        List<Node> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            //弹出队列才加入数据
            list.add(poll);
            List<Node> next = poll.getNext();
            if (!CollectionUtils.isEmpty(next)) {
                for (Node it : next) {
                    if (!set.contains(it)) {
                        queue.add(it);
                        set.add(it);
                    }
                }
            }
        }
        return list;
    }


    /**
     * 深度优先遍历
     */
    public List<Node> dfs(int data) {
        if (!nodeMap.containsKey(data)) {
            return null;
        }
        Node node = nodeMap.get(data);
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        List<Node> list = new ArrayList<>();
        //压进栈就加入数据
        list.add(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            List<Node> next = pop.getNext();
            if (!CollectionUtils.isEmpty(next)) {
                for (Node it : next) {
                    if (!set.contains(it)) {
                        stack.push(pop);
                        stack.push(it);
                        set.add(it);
                        list.add(it);
                        break;
                    }
                }
            }
        }
        return list;
    }


    /**
     * 拓扑排序，必须是有向无环图
     */
    public List<Node> tpSort() {
        //找出所有入度为0的点，然后一个个弹出就可以了
        Map<Node, Integer> inMap = new HashMap<>();
        List<Node> list = nodeMap.entrySet().stream().map(it -> it.getValue()).collect(Collectors.toList());
        Queue<Node> queue = new LinkedList<>();
        for (Node node : list) {
            inMap.put(node,node.getIn());
            if (node.getIn() == 0) {
                queue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            result.add(poll);
            List<Node> next = poll.getNext();
            if (!CollectionUtils.isEmpty(next)) {
                for (Node node : next) {
                    inMap.put(node,inMap.get(node)-1);
                    if (inMap.get(node) == 0) {
                        queue.add(node);
                    }
                }
            }
        }
        return result;
    }


    /**
     * 迪丽特斯拉算法，给定一个点，求该点到达其余点的最短距离
     */
    public Map<Node,Integer> dijkstra(int data) {
        if (!nodeMap.containsKey(data)) {
            return null;
        }
        Node from = nodeMap.get(data);
        //最短距离的map
        Map<Node,Integer> distance = new HashMap<>();
        //已经选择过的点集
        Set<Node> set = new HashSet<>();
        distance.put(from,0);
        //选择未被选择过且距离最小的点
        Node minNode = getMinDistanceNode(distance, set);
        while (minNode != null) {
            List<Edge> edges = minNode.getEdges();
            if (!CollectionUtils.isEmpty(edges)) {
                for (Edge edge : edges) {
                    Node to = edge.getTo();
                    int weight = edge.getWeight();
                    if (!distance.containsKey(to)) {
                        distance.put(to,distance.get(minNode) + weight);
                    } else {
                        distance.put(to,Math.min(distance.get(minNode) + weight,distance.get(to)));
                    }
                }
            }
            set.add(minNode);
            minNode = getMinDistanceNode(distance, set);
        }
        return distance;
    }


    private Node getMinDistanceNode(Map<Node,Integer> distance, Set<Node> set) {
        Node minNode = null;
        Integer minDisctance = Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> it : distance.entrySet()) {
            Node key = it.getKey();
            Integer value = it.getValue();
            if (!set.contains(key) && value < minDisctance) {
                minNode = key;
                minDisctance = value;
            }
        }
        return minNode;
    }
}
