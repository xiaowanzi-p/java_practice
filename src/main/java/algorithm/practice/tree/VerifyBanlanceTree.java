package algorithm.practice.tree;

/**
 * 判断一棵树是否是平衡二叉树
 *
 * @author 君墨笑
 * @date 2023/12/4
 */
public class VerifyBanlanceTree {


    public static void main(String[] a) {
        TreeNode head = new TreeNode();
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        TreeNode node4 = new TreeNode();
        TreeNode node5 = new TreeNode();
        TreeNode node6 = new TreeNode();
        TreeNode node7 = new TreeNode();
        head.setLeft(node1);
        head.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node5.setRight(node7);
        System.out.println("平衡树: " + verifyTree(head));
    }

    /**
     * 递归公式：树本身以及任一个子树左树高、右树高的高度差不大于1
     */
    private static boolean verifyTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        if (head.getRight() == null && head.getLeft() == null) {
            return true;
        }
        //树本身
        boolean self = Math.abs(calacute(head.getLeft()) - calacute(head.getRight())) <= 1;
        //树左右子树
        boolean son = verifyTree(head.getLeft()) && verifyTree(head.getRight());
        return self && son;
    }


    //计算树高
    private static int calacute(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.getLeft() == null && head.getRight() == null) {
            return 1;
        }
        return Math.max(calacute(head.getLeft()),calacute(head.getRight())) + 1;
    }
}
