package algorithm.practice.linklist;

import com.example.java_practice.arithmetic.common.CommonUtil;

/**
 * 给个单链表，给定一个比较数值，将链表划分三个区域：小于、等于、大于
 */
public class PartitionList {

    public static void main(String[] a) {
        SingleListNode head = new SingleListNode(6);
        SingleListNode node1 = new SingleListNode(2);
        SingleListNode node2 = new SingleListNode(5);
        SingleListNode node3 = new SingleListNode(1);
        SingleListNode node4 = new SingleListNode(3);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        SingleListNode partition = partition(head, 3);
        CommonUtil.foreach(partition);
    }


    private static SingleListNode partition(SingleListNode head, int target) {
        SingleListNode lessHead = null;
        SingleListNode lessTail = null;
        SingleListNode equalHead = null;
        SingleListNode equalTail = null;
        SingleListNode moreHead = null;
        SingleListNode moretail = null;
        SingleListNode current = head;
        while (current != null) {
            if (current.getData() < target) {
                if (lessHead == null) {
                    lessHead = current;
                    lessTail = current;
                } else {
                    lessTail.setNext(current);
                    lessTail = current;
                }
            }
            if (current.getData() == target) {
                if (equalHead == null) {
                    equalHead = current;
                    equalTail = current;
                } else {
                    equalTail.setNext(current);
                    equalTail = current;
                }
            }
            if (current.getData() > target) {
                if (moreHead == null) {
                    moreHead = current;
                    moretail = current;
                } else {
                    moretail.setNext(current);
                    moretail = current;
                }
            }
            SingleListNode temp = current;
            current = current.getNext();
            temp.setNext(null);
        }

        if (lessTail != null) {
            if (equalHead != null) {
                lessTail.setNext(equalHead);
            } else {
                lessTail.setNext(moreHead);
            }
        }
        if (equalTail != null) {
            equalTail.setNext(moreHead);
        }
        if (lessHead != null) {
            return lessHead;
        }
        if (equalHead != null) {
            return equalHead;
        }
        return moreHead;
    }
}
