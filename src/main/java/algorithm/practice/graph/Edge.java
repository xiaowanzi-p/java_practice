package algorithm.practice.graph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Edge {

    /**
     * 开始点
     */
    private Node from;

    /**
     * 到达点
     */
    private Node to;

    /**
     * 权重
     */
    private int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
