package algorithm.practice.tree;

public class SameTree {

    public static void main(String[] a) {
        boolean process = process(tree1(), tree2());
        System.out.println("结果: " + process);
    }

    private static TreeNode tree1() {
        TreeNode head = new TreeNode();
        head.data = 1;
        TreeNode node1 = new TreeNode();
        node1.data = 2;
        TreeNode node2 = new TreeNode();
        node2.data = 3;
        TreeNode node3 = new TreeNode();
        node3.data = 4;
        TreeNode node4 = new TreeNode();
        node4.data = 5;
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node2.right = node4;
        return head;
    }

    private static TreeNode tree2() {
        TreeNode head = new TreeNode();
        head.data = 1;
        TreeNode node1 = new TreeNode();
        node1.data = 2;
        TreeNode node2 = new TreeNode();
        node2.data = 3;
        TreeNode node3 = new TreeNode();
        node3.data = 4;
        TreeNode node4 = new TreeNode();
        node4.data = 8;
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node2.right = node4;
        return head;
    }


    private static boolean process(TreeNode head1, TreeNode head2) {
        if (head1 == null ^ head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        //左树
        boolean left = process(head1.left, head2.left);
        //右树
        boolean right = process(head1.right, head2.right);
        //自身
        return head1.data == head2.data && left && right;
    }
}

