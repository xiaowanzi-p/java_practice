package algorithm.practice.tree;

public class BalanceTree2 {


    public static void main(String[] a) {

    }


    private static boolean process(TreeNode head1, TreeNode head2) {
        if (head1 == null ^ head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.data != head2.data) {
            return false;
        }
        //左树相等
        boolean left = process(head1.left, head2.left);
        //右树相等
        boolean right = process(head1.right, head2.right);
        return left && right;
    }
}
