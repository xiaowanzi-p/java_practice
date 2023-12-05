package algorithm.practice.tree;

/**
 * 验证一棵树是否是对称树
 *
 * @author 君墨笑
 * @date 2023/12/5
 */
public class VerifyMirrorTree {


    /**
     * 递归公式：
     * 从根节点开始，左子树的左节点等于右子树的右节点，左子树的右节点等于右子树的左节点
     */
    private static boolean verify(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.right == null ^ root.left == null) {
            return false;
        }
        return compare(root.left,root.right);
    }


    //比较左子树的左节点等于右子树的右节点，左子树的右节点等于右子树的左节点
    private static boolean compare(TreeNode root1, TreeNode root2) {
        if (root1 == null ^ root2 == null) {
            return false;
        }
        if (root1 == null && root2 == null) {
            return true;
        }
        return root1.data == root2.data && compare(root1.left,root2.right) && compare(root1.right,root2.left);
    }
}
