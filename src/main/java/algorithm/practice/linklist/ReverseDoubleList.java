package algorithm.practice.linklist;

/**
 * 反转双链表
 */
public class ReverseDoubleList {

    public static void main(String[] a) {
        DoubleListNode node1 = new DoubleListNode();
        DoubleListNode node2 = new DoubleListNode();
        DoubleListNode node3 = new DoubleListNode();
        DoubleListNode node4 = new DoubleListNode();
        node1.setData(1);
        node2.setData(2);
        node3.setData(3);
        node4.setData(4);
        node1.setNext(node2);
        node2.setNext(node3);
        node2.setPre(node1);
        node3.setPre(node2);
        node3.setNext(node4);
        node4.setPre(node3);
        printList(node1);
        DoubleListNode head = reverseList(node1);
        printList(head);
    }

    //1->2->3->4
    public static DoubleListNode reverseList(DoubleListNode head) {
        DoubleListNode pre = null;
        DoubleListNode next = head.getNext();
        while (next != null) {
            head.setNext(pre);
            head.setPre(next);
            pre = head;
            head = next;
            next = head.getNext();
        }
        head.setNext(pre);
        head.setPre(null);
        return head;
    }


    public static void printList(DoubleListNode head) {
        StringBuilder zheng = new StringBuilder();
        zheng.append(head.getData());
        DoubleListNode next1 = head.getNext();
        while (next1 != null) {
            zheng.append(next1.getData());
            next1 = next1.getNext();
        }
        System.out.println("正向:"+ zheng.toString());

        StringBuilder fan = new StringBuilder();
        DoubleListNode next2 = head.getNext();
        DoubleListNode last = null;
        while (next2 != null) {
            DoubleListNode temp = next2.getNext();
            if (temp == null) {
                last = next2;
            }
            next2 = temp;
        }
        fan.append(last.getData());
        DoubleListNode pre = last.getPre();
        while (pre != null) {
            fan.append(pre.getData());
            pre = pre.getPre();
        }
        System.out.println("反向:"+ fan.toString());
    }
}
