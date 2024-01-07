package algorithm.practice.linklist;

import lombok.Getter;
import lombok.Setter;

/**
 * 单链表节点
 */
@Getter
@Setter
public class SingleListNode {
    private int data;
    private SingleListNode next;

    public SingleListNode() {

    }

    public SingleListNode(int data) {
        this.data = data;
    }
}
