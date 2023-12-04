package algorithm.practice.tree;

/**
 * 计算树的高度
 *
 * @author 君墨笑
 * @date 2023/12/4
 */
public class CalculateTreeHight {

    public static void main(String[] s) {
        TreeNode node1 = TreeUtil.buildRandomDoubleTree();
        System.out.println("树高：" + calculate(node1));
        TreeNode node2 = TreeUtil.buildDoubleTree(new int[]{1, 8, 3, 4, 6, 5});
        System.out.println("树高：" + calculate(node2));
        TreeNode node3 = TreeUtil.buildUnilateralDoubleTree(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("树高：" + calculate(node3));
        TreeNode node4 = new TreeNode();
        System.out.println("树高：" + calculate(node4));
    }


    /**
     * 递归公式:左树的高度、右树的高度取最大+1
     */
    private static int calculate(TreeNode head) {
        //递归边界
        if (head == null) {
            return 0;
        }
        //叶子节点的高度为1
        if (head.getLeft() == null && head.getRight() == null) {
            return 1;
        }
        return Math.max(calculate(head.getLeft()),calculate(head.getRight())) + 1;
    }
}
