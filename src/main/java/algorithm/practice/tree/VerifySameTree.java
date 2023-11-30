package algorithm.practice.tree;

import algorithm.practice.linklist.SingleListNode;

/**
 * 比较两颗树是否一致
 *
 * @author 君墨笑
 * @date 2023/11/30
 */
public class VerifySameTree {

    public static void main(String[] a) {

    }


    private static boolean isSame(DoubleTreeNode head1, DoubleTreeNode head2) {
        //递归边界条件
        if (head1 == null ^ head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        return head1.getData() == head2.getData() && isSame(head1.getLeft(),head2.getLeft()) && isSame(head1.getRight(),head2.getRight());
    }


}
