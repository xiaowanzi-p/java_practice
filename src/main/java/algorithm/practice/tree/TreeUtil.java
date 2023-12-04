package algorithm.practice.tree;

/**
 * @author 君墨笑
 * @date 2023/11/30
 */
public class TreeUtil {


    /**
     * 构建搜索二叉树
     */
    public static TreeNode buildDoubleTree(int[] array) {
        TreeNode head = null;
        TreeNode target = null;
        for (int i : array) {
            if (head == null) {
                head = new TreeNode();
                head.setData(i);
                target = head;
            } else {
                TreeNode node = new TreeNode();
                node.setData(i);
                while (target != null) {
                    if (i > target.getData()) {
                        TreeNode right = target.getRight();
                        if (right != null) {
                            target = right;
                        } else {
                            target.setRight(node);
                            target = head;
                            break;
                        }
                    } else {
                        TreeNode left = target.getLeft();
                        if (left != null) {
                            target = left;
                        } else {
                            target.setLeft(node);
                            target = head;
                            break;
                        }
                    }
                }
            }
        }
        return head;
    }


    /**
     * 构建单边二叉数
     */
    public static TreeNode buildUnilateralDoubleTree(int[] array) {
        TreeNode head = null;
        TreeNode target = null;
        for (int i : array) {
            if (head == null) {
                head = new TreeNode();
                head.setData(i);
                target = head;
            } else {
                TreeNode node = new TreeNode();
                node.setData(i);
                target.setLeft(node);
                target = node;
            }
        }
        return head;
    }

    /**
     * 构建不规则二叉树
     */
    public static TreeNode buildRandomDoubleTree() {
        TreeNode head = new TreeNode();
        head.setData(1);
        TreeNode node1 = new TreeNode();
        node1.setData(5);
        TreeNode node2 = new TreeNode();
        node2.setData(8);
        TreeNode node3 = new TreeNode();
        node3.setData(10);
        TreeNode node4 = new TreeNode();
        node4.setData(12);

        head.setRight(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setRight(node4);
        return head;
    }

}
