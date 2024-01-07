package algorithm.practice.linklist;

/**
 * 快慢指针练习
 */
public class FastAndSlow {

    public static void main(String[] a) {
        SingleListNode head = new SingleListNode(1);
        SingleListNode node1 = new SingleListNode(2);
        SingleListNode node2 = new SingleListNode(3);
        head.setNext(node1);
        node1.setNext(node2);
        SingleListNode node3 = new SingleListNode(4);
        node2.setNext(node3);
        System.out.println("第一个：" + practice1(head).getData());
        System.out.println("第二个：" + practice2(head).getData());
        System.out.println("第三个：" + practice3(head).getData());
        System.out.println("第四个：" + practice4(head).getData());
        /*SingleListNode node4 = new SingleListNode(5);
        SingleListNode node5 = new SingleListNode(6);*/
    }


    /**
     * 奇数长度返回中间节点，偶数长度返回上中节点
     */
    private static SingleListNode practice1(SingleListNode head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return head;
        }
        SingleListNode fast = head;
        SingleListNode slow = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 奇数长度返回中点，偶数长度返回下中点
     */
    private static SingleListNode practice2(SingleListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        SingleListNode fast = head;
        SingleListNode slow = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast.getNext() != null) {
            return slow.getNext();
        } else {
            return slow;
        }
    }

    /**
     * 奇数长度返回中点前一个，偶数长度返回上中点前一个
     */
    private static SingleListNode practice3(SingleListNode head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return head;
        }
        SingleListNode fast = head;
        SingleListNode slow = head;
        SingleListNode pre = null;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            //存储慢指针快照,在慢指针后移动
            pre = slow;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return pre;
    }

    /**
     * 奇数长度返回中点前一个，偶数长度返回下中点前一个
     */
    private static SingleListNode practice4(SingleListNode head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return head;
        }
        SingleListNode fast = head;
        SingleListNode slow = head;
        SingleListNode pre = null;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            //存储慢指针快照,在慢指针后移动
            pre = slow;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        //偶数场景
        if (fast.getNext() != null) {
            return slow;
        } else {
            //奇数场景
            return pre;
        }
    }
}
