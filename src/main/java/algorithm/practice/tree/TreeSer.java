package algorithm.practice.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树的序列化
 */
public class TreeSer {

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
        Queue<Integer> list = new LinkedList<>();
        ser(head,list);
        System.out.println("序列化结束");
        TreeNode vser = vser(list);
        System.out.println("反序列化结束");
    }


    /**
     * 前序序列化
     */
    private static void ser(TreeNode head, Queue<Integer> queue) {
        //递归边界
        if (head == null) {
            queue.add(null);
            return;
        }
        queue.add(head.data);
        ser(head.left,queue);
        ser(head.right,queue);
    }


    private static TreeNode vser(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        Integer poll = queue.poll();
        if (poll == null) {
            return null;
        }
        TreeNode node = new TreeNode();
        node.data = poll;
        node.left = vser(queue);
        node.right = vser(queue);
        return node;
    }
}
