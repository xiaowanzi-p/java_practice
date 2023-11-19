package algorithm.practice.linklist;

import lombok.Getter;
import lombok.Setter;

/**
 * 双链表节点
 */
@Getter
@Setter
public class DoubleListNode {
    private DoubleListNode pre;
    private int data;
    private DoubleListNode next;
}
