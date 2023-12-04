package algorithm.practice.tree;

/**
 * 比较两颗树是否一致
 *
 * @author 君墨笑
 * @date 2023/11/30
 */
public class VerifySameTree {

    public static void main(String[] a) {
        TreeNode tree1 = TreeUtil.buildDoubleTree(new int[]{1, 4, 6, 2, 4, 8, 7, 9});
        TreeNode tree2 = TreeUtil.buildDoubleTree(new int[]{1, 4, 6, 2, 4, 8, 7, 9});
        TreeNode tree3 = TreeUtil.buildDoubleTree(new int[]{1, 4, 6, 3, 4, 8, 10, 9});
        boolean same1 = isSame(tree1, tree2);
        boolean same2 = isSame(tree1, tree3);
        System.out.println("是否相同：" + same1);
        System.out.println("是否相同：" + same2);
        TreeNode tree4 = TreeUtil.buildUnilateralDoubleTree(new int[]{1, 2, 6, 4, 8});
        TreeNode tree5 = TreeUtil.buildUnilateralDoubleTree(new int[]{1, 2, 6, 4, 8});
        TreeNode tree6 = TreeUtil.buildUnilateralDoubleTree(new int[]{1, 2, 2, 4, 8});
        boolean sam3 = isSame(tree4, tree5);
        boolean sam4 = isSame(tree4, tree6);
        System.out.println("是否相同：" + sam3);
        System.out.println("是否相同：" + sam4);
        TreeNode tree7 = TreeUtil.buildRandomDoubleTree();
        TreeNode tree8 = TreeUtil.buildRandomDoubleTree();
        boolean same5 = isSame(tree7, tree8);
        System.out.println("是否相同：" + same5);
    }


    /**
     * 递归公式：两颗树每个子树的头结点、左子树、右子树完全相等就是相等
     */
    private static boolean isSame(TreeNode head1, TreeNode head2) {
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
