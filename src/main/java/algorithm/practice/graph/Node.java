package algorithm.practice.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 图上的点
 */
@Getter
@Setter
public class Node {
    /**
     * 数据
     */
    private int data;

    /**
     * 入度
     */
    private int in;

    /**
     * 出度
     */
    private int out;

    /**
     * 直接连接点
     */
    private List<Node> next;

    /**
     * 直接边
     */
    private List<Edge> edges;

    public Node(int data, int in, int out) {
        this.in = in;
        this.out = out;
        this.data = data;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
