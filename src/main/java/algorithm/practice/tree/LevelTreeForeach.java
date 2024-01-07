package algorithm.practice.tree;

import com.example.java_practice.arithmetic.common.CommonUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历树
 */
public class LevelTreeForeach {

    public static void main(String[] a) {
        TreeNode head = new TreeNode();
        head.data = 1;
        TreeNode node1 = new TreeNode();
        node1.data = 2;
        TreeNode node2 = new TreeNode();
        node2.data = 3;
        TreeNode node3 = new TreeNode();
        node3.data = 4;
        TreeNode node4 = new TreeNode();
        node4.data = 5;
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode();
        TreeNode node6 = new TreeNode();
        node2.left = node5;
        node2.right = node6;
        int maxWidth = getMaxWidth(head);
        System.out.println(maxWidth);
    }

    /**
     * 按层遍历二叉树
     */
    private static void process(TreeNode head) {
        if (head == null) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            builder.append(poll.data);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        System.out.println(builder.toString());
    }


    /**
     * 获取最大宽度
     */
    private static int getMaxWidth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        HashMap<TreeNode,Integer> map = new HashMap<>();
        int currentLevel = 1;
        int currentLevelNodes = 0;
        int max = 0;
        queue.add(head);
        map.put(head,1);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            Integer nodeLevel = map.get(poll);
            if (poll.left != null) {
                queue.add(poll.left);
                map.put(poll.left,nodeLevel+1);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                map.put(poll.right,nodeLevel+1);
            }
            if (nodeLevel == currentLevel) {
                currentLevelNodes++;
            } else {
                max = Math.max(max,currentLevelNodes);
                currentLevelNodes = 1;
                currentLevel++;
            }
        }
        max = Math.max(max,currentLevelNodes);
        return max;
    }

}
