package algorithm.practice.tree;

/**
 *
 * 判断一棵树是否是搜索树
 * @author 君墨笑
 * @date 2023/12/5
 */
public class VerifySearchTree {


    public static void main(String[] a) {
        TreeNode node = new TreeNode();
        verify(node);
    }

    //递归公式：任一子树的左树最大值比根节点小,右树的最小值比根节点大,且左子树右子树都是搜索树
    private static boolean verify(TreeNode root) {
        //递归边界条件
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        //拿到左子树最大值,右子树最小值
        Info leftInfo = calacute(root.left);
        Info rightInfo = calacute(root.right);
        return root.data > leftInfo.max && root.data <= rightInfo.min && verify(root.left) && verify(root.right);
    }


    private static class Info {
        //树最小值
        public int min;
        //树最大值
        public int max;
    }


    /**
     * 计算一棵树的最大值,最小值
     * 递归公式：计算出任一子树的最大值、最小值，与根节点做对比，计算出整棵树的最大值、最小值
     */
    private static Info calacute(TreeNode root) {
        Info info = new Info();
        //递归边界条件
        if (root == null) {
            info.max = 0;
            info.min = 0;
            return info;
        }
        if (root.left == null && root.right == null) {
            info.max = root.data;
            info.min = root.data;
            return info;
        }
        //计算左树的值
        Info leftInfo = calacute(root.left);
        //计算右数的值
        Info rightInfo = calacute(root.right);
        //根节点值比较取得最小值
        int min = Math.min(Math.min(root.data, leftInfo.min), Math.min(root.data, rightInfo.min));
        info.min = min;
        //根节点值比较取得最大值
        int max = Math.max(Math.max(root.data, leftInfo.max), Math.max(root.data, rightInfo.max));
        info.max = max;
        return info;
    }
}
