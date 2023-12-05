package algorithm.practice.tree;

/**
 * 判断树是否有目标值的累加和路径
 *
 * @author 君墨笑
 * @date 2023/12/5
 */
public class TreeHashPathSum {


    private static boolean success = false;

    public static void main(String[] a) {
        TreeNode node = new TreeNode();
        hasPathSum(node,9);
    }

    /**
     * 递归公式：从根节点递归向下找，每向下找一层就计算出对应左节点累加和、右节点累加和
     * 并判断左右节点是否到达叶子节点，如果到达则与目标值比对，如果相等则成功
     */
    private static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        process(0,target,root);
        return success;
    }

    //计算一直到路径的累加和
    private static void process(int preSum, int target, TreeNode root) {
        //递归边界条件
        if (root == null) {
            if (preSum == target) {
                success = true;
                return;
            }
        }
        //到达叶子节点
        if (root.left == null && root.right == null) {
            if ((root.data + preSum) == target) {
                success = true;
                return;
            }
        }
        preSum = preSum + root.data;
        //左节点路径
        process(preSum,target,root.left);
        //右节点路径
        process(preSum,target,root.right);
    }
}
