package algorithm.practice.tree;

public class MirrorTree {

    public static void main(String[] a) {
        TreeNode head = new TreeNode();
        head.data = 1;
        TreeNode node1 = new TreeNode();
        node1.data = 2;
        TreeNode node2 = new TreeNode();
        node2.data = 2;
        TreeNode node3 = new TreeNode();
        node3.data = 3;
        TreeNode node4 = new TreeNode();
        node4.data = 3;
        head.left = node1;
        head.right = node2;
        node1.right = node3;
        node2.left = node4;
        System.out.println("结果：" + process(head));
    }

    private static boolean process(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head.left,head.right);
    }

    private static boolean process(TreeNode head1, TreeNode head2) {
        if (head1 == null ^ head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        boolean equal1 = process(head1.left, head2.right);
        boolean equal2 = process(head1.right, head2.left);
        return equal1 && equal2 && head1.data == head2.data;
    }
}
