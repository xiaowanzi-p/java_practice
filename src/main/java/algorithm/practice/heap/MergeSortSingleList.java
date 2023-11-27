package algorithm.practice.heap;

import algorithm.practice.linklist.SingleListNode;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 *
 * 合并多个排序好的单链表,且合并后保持有序
 *
 * @author 君墨笑
 * @date 2023/11/27
 */
public class MergeSortSingleList {


    public static void main(String[] a) {
       /* SingleListNode node1 = buildList(new int[]{1, 2, 3});
        SingleListNode node2 = buildList(new int[]{4, 5, 6});
        SingleListNode node3 = buildList(new int[]{3, 8, 9});
        SingleListNode head = merge(new SingleListNode[]{node1,node2,node3});*/
        SingleListNode node1 = buildList(new int[]{1});
        SingleListNode head2 = merge(new SingleListNode[]{node1});
        printList(head2);
    }

    private static SingleListNode merge(SingleListNode[] array) {
        if (array == null) {
            return null;
        }
        //将多个链表第一个节点一起构建一个最小堆
        PriorityQueue<SingleListNode> queue = new PriorityQueue<>(new Comparator<SingleListNode>() {
            @Override
            public int compare(SingleListNode o1, SingleListNode o2) {
                return o1.getData() - o2.getData();
            }
        });
        for (SingleListNode node : array) {
            if (node != null) {
                queue.add(node);
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        //弹出堆的第一个元素
        SingleListNode head = queue.poll();
        SingleListNode tail = head;
        SingleListNode next = head.getNext();
        if (next != null) {
            queue.add(next);
        }
        SingleListNode poll = queue.poll();
        while (poll != null) {
            tail.setNext(poll);
            tail = poll;
            SingleListNode next1 = poll.getNext();
            if (next1 != null) {
                queue.add(next1);
            }
            poll = queue.poll();
        }
        return head;
    }


    private static void printList(SingleListNode head) {
        if (head == null) {
            System.out.println("空");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(head.getData());
        SingleListNode next = head.getNext();
        while (next != null) {
            builder.append(next.getData());
            next = next.getNext();
        }
        System.out.println(builder.toString());
    }


    private static SingleListNode buildList(int[] array) {
        SingleListNode head = null;
        SingleListNode tail = null;
        for (int value : array) {
            if (head == null) {
                head = new SingleListNode();
                head.setData(value);
                tail = head;
            } else {
                SingleListNode node = new SingleListNode();
                node.setData(value);
                tail.setNext(node);
                tail = node;
            }
        }
        return head;
    }
}
