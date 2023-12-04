package algorithm.practice.tree;

/**
 * 二叉树遍历
 *
 * @author 君墨笑
 * @date 2023/11/27
 */
public class DoubleTreeForEach {

    public static void main(String[] a) {
        TreeNode root = build();
        pre(root);
        System.out.println("===========");
        middle(root);
        System.out.println("===========");
        post(root);
    }

    /**
     * 前序：头左右
     */
    public static void pre(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return;
        }
        System.out.println(root.getData());
        pre(root.getLeft());
        pre(root.getRight());
    }


    /**
     * 中序：左头右
     */
    public static void middle(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return;
        }
        middle(root.getLeft());
        System.out.println(root.getData());
        middle(root.getRight());
    }

    /**
     * 前序：左右头
     */
    public static void post(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return;
        }
        post(root.getLeft());
        post(root.getRight());
        System.out.println(root.getData());
    }


    public static TreeNode build() {
        TreeNode root = new TreeNode();
        root.setData(1);
        TreeNode node1 = new TreeNode();
        node1.setData(2);
        TreeNode node2 = new TreeNode();
        node2.setData(3);
        TreeNode node3 = new TreeNode();
        node3.setData(4);
        TreeNode node4 = new TreeNode();
        node4.setData(5);
        TreeNode node5 = new TreeNode();
        node5.setData(6);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node3);
        node1.setRight(node4);
        node1.setLeft(node5);
        return root;
    }
}
