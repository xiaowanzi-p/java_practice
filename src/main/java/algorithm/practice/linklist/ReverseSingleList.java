package algorithm.practice.linklist;

/**
 * 反转单链表
 */
public class ReverseSingleList {

    public static void main(String[] a) {
        SingleListNode node1 = new SingleListNode();
        SingleListNode node2 = new SingleListNode();
        SingleListNode node3 = new SingleListNode();
        SingleListNode node4 = new SingleListNode();
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node1.setData(1);
        node2.setData(2);
        node3.setData(3);
        node4.setData(4);
        printList(node1);
        SingleListNode head = reverseList(node1);
        printList(head);
    }


    public static SingleListNode reverseList(SingleListNode head) {
        //1->2->3->4->5
        SingleListNode pre = null;
        SingleListNode next = head.getNext();
        while (next != null) {
            head.setNext(pre);
            pre = head;
            head = next;
            next = head.getNext();
        }
        head.setNext(pre);
        return head;
    }

    public static void printList(SingleListNode head) {

        StringBuilder builder = new StringBuilder();
        builder.append(head.getData());
        SingleListNode next = head.getNext();
        while (next != null) {
            builder.append(next.getData());
            next = next.getNext();
        }
        System.out.println(builder.toString());
    }
}
