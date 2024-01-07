package algorithm.practice.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * 环状链表
 */
public class LoopList {

    public static void main(String[] a) {
        SingleListNode head = new SingleListNode(1);
        SingleListNode node1 = new SingleListNode(2);
        SingleListNode node2 = new SingleListNode(3);
        SingleListNode node3 = new SingleListNode(4);
        SingleListNode node4 = new SingleListNode(5);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node2);

        SingleListNode head2 = new SingleListNode(10);
        SingleListNode node5 = new SingleListNode(11);
        SingleListNode node6 = new SingleListNode(12);
        head2.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);
        //node5.setNext(node1);
        SingleListNode loopCos1 = loopCos(head, head2);
        SingleListNode loopCos2 = loopCosV2(head, head2);
        System.out.println("结束");
    }


    /**
     * 判断一个单链表是否有环，并返回第一个入环节点
     */
    private static SingleListNode isLoop(SingleListNode head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return null;
        }
        Set<SingleListNode> set = new HashSet<>();
        SingleListNode current = head;
        while (current != null) {
            if (set.contains(current)) {
                return current;
            } else {
                set.add(current);
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * 判断一个单链表是否有环，并返回第一个入环节点
     */
    private static SingleListNode isLoopV2(SingleListNode head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return null;
        }
        SingleListNode fast = head;
        SingleListNode slow = head;
        //快慢指针如果没有相遇就表示无环，如果相遇表示有环
        boolean isloop = false;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                isloop = true;
                break;
            }
        }
        if (isloop) {
            //快节点回到头结点一次走一步，慢节点也一次走一步，两者第一次相遇的节点就是入环节点
            fast = head;
            while (fast != slow) {
                fast = fast.getNext();
                slow = slow.getNext();
            }
            return fast;
        }
        return null;
    }

    /**
     * 判断两个无环链表是否相交，是则返回第一个相交节点
     */
    private static SingleListNode noLoopCos(SingleListNode head1, SingleListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        HashSet<SingleListNode> set = new HashSet<>();
        SingleListNode index1 = head1;
        while (index1 != null) {
            set.add(index1);
            index1 = index1.getNext();
        }
        SingleListNode index2 = head2;
        while (index2 != null) {
            if (set.contains(index2)) {
                return index2;
            }
            index2 = index2.getNext();
        }
        return null;
    }


    /**
     * 判断两个无环链表是否相交，是则返回第一个相交节点
     */
    private static SingleListNode noLoopCosV2(SingleListNode head1, SingleListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleListNode index1 = head1;
        SingleListNode end1 = null;
        int len1 = 1;
        SingleListNode index2 = head2;
        SingleListNode end2 = null;
        int len2 = 1;
        while (index1 != null) {
            end1 = index1;
            index1 = index1.getNext();
            len1++;
        }
        while (index2 != null) {
            end2 = index2;
            index2 = index2.getNext();
            len2++;
        }
        //不相交
        if (end1 != end2) {
            return null;
        }
        //相交求第一个入环节点
        int abs = Math.abs(len1 - len2);
        SingleListNode more = null;
        SingleListNode less = null;
        if (len1 > len2) {
            more = head1;
            less = head2;
        } else {
            more = head2;
            less = head1;
        }
        int step = 1;
        while (step <= abs) {
            more = more.getNext();
            step++;
        }
        while (more != less) {
            more = more.getNext();
            less = less.getNext();
        }
        return more;
    }


    /**
     * 给两个有环链表，获取相交的第一个入环节点
     */
    private static SingleListNode loopCos(SingleListNode head1, SingleListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleListNode index1 = head1;
        HashSet<SingleListNode> set = new HashSet<>();
        while (index1 != null) {
            if (set.contains(index1)) {
                break;
            } else {
                set.add(index1);
                index1 = index1.getNext();
            }
        }
        SingleListNode index2 = head2;
        HashSet<SingleListNode> set2 = new HashSet<>();
        while (index2 != null) {
            if (set2.contains(index2)) {
                break;
            } else {
                if (set.contains(index2)) {
                    return index2;
                }
                set2.add(index2);
                index2 = index2.getNext();
            }
        }
        return null;
    }


    /**
     * 给两个有环链表，获取相交的第一个入环节点
     */
    private static SingleListNode loopCosV2(SingleListNode head1, SingleListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleListNode loopNode1 = isLoopV2(head1);
        SingleListNode loopNode2 = isLoopV2(head2);
        //入环节点相同
        if (loopNode1 == loopNode2) {
            SingleListNode index1 = head1;
            int len1 = 1;
            while (index1 != loopNode1) {
                index1 = index1.getNext();
                len1++;
            }
            SingleListNode index2 = head2;
            int len2 = 1;
            while (index2 != loopNode2) {
                index2 = index2.getNext();
                len2++;
            }
            int abs = Math.abs(len1 - len2);
            SingleListNode more = null;
            SingleListNode less = null;
            if (len1 > len2) {
                more = head1;
                less = head2;
            } else {
                more = head2;
                less = head1;
            }
            int step = 1;
            while (step <= abs) {
                more = more.getNext();
                step++;
            }
            while (more != less) {
                more = more.getNext();
                less = less.getNext();
            }
            return more;
        }
        //入环节点不同
        boolean isCos = false;
        SingleListNode index1 = loopNode1.getNext();
        while (index1 != loopNode1) {
            if (index1 == loopNode2) {
                isCos = true;
                break;
            }
            index1 = index1.getNext();
        }
        //相交
        if (isCos) {
            return loopNode2;
        }
        return null;
    }
}
