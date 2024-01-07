package algorithm.practice.linklist;

import java.util.Stack;

/**
 * 判断一个单链表是否为回文结构，正反遍历数据一样
 *
 * @author pengshaoxiang
 */
public class HuiWenList {

    public static void main(String[] a) {
        SingleListNode head = new SingleListNode(1);
        SingleListNode node1 = new SingleListNode(2);
        SingleListNode node2 = new SingleListNode(1);
        head.setNext(node1);
        node1.setNext(node2);
        System.out.println(check(head));
        System.out.println(checkV2(head));
    }


    /**
     * 用栈来解决
     *
     * @param head
     * @return
     */
    private static boolean check(SingleListNode head) {
        if (head == null || head.getNext() == null) {
            return true;
        }
        //用栈
        Stack<Integer> stack = new Stack<>();
        SingleListNode current = head;
        while (current != null) {
            stack.push(current.getData());
            current = current.getNext();
        }

        SingleListNode current2 = head;
        while (current2 != null) {
            if (current2.getData() != stack.pop()) {
                return false;
            }
            current2 = current2.getNext();
        }
        return true;
    }


    /**
     * 不用栈来解决
     *
     * @param head
     * @return
     */
    private static boolean checkV2(SingleListNode head) {
        if (head == null || head.getNext() == null) {
            return true;
        }
        //找到中节点(奇数中间节点，偶数上中节点)
        SingleListNode fast = head;
        SingleListNode slow = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        //反转中点后的半段链表
        SingleListNode secondHead = slow.getNext();
        slow.setNext(null);
        //前节点
        SingleListNode pre = null;
        //当前节点
        SingleListNode current = secondHead;
        //后节点
        SingleListNode next = current.getNext();
        while (next != null) {
            pre = current;
            current = next;
            next = current.getNext();
            if (pre == secondHead) {
                pre.setNext(null);
            }
            current.setNext(pre);
        }
        //反转后的current节点就是头节点
        SingleListNode thirdHead = current;
        //从头遍历两段链表进行元素比较
        SingleListNode compareOne = head;
        SingleListNode comparetwo = thirdHead;
        while (compareOne != null && comparetwo != null) {
            if (compareOne.getData() != comparetwo.getData()) {
                return false;
            } else {
                compareOne = compareOne.getNext();
                comparetwo = comparetwo.getNext();
            }
        }
        return true;
    }
}
