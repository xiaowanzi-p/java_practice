package algorithm.practice.tree;

/**
 * @author 君墨笑
 * @date 2023/11/30
 */
public class TreeUtil {


    /**
     * 构建搜索二叉树
     */
    public static DoubleTreeNode buildDoubleTree(int[] array) {
        DoubleTreeNode head = null;
        DoubleTreeNode target = null;
        for (int i : array) {
            if (head == null) {
                head = new DoubleTreeNode();
                head.setData(i);
                target = head;
            } else {
                DoubleTreeNode node = new DoubleTreeNode();
                node.setData(i);
                while (target != null) {
                    if (i > target.getData()) {
                        DoubleTreeNode right = target.getRight();
                        if (right != null) {
                            target = right;
                        } else {
                            target.setRight(node);
                            target = head;
                            break;
                        }
                    } else {
                        DoubleTreeNode left = target.getLeft();
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


    /*public static DoubleTreeNode buildDoubleTree(int[] array) {

    }*/
}
