package algorithm.practice.tree;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * 多叉树遍历
 *
 * @author 君墨笑
 * @date 2023/11/27
 */
public class MultiTreeForEach {


    public static void main(String[] a) {
        MultiTreeNode root = buildTree();
        forhead(root);
        System.out.println("===================");
        post(root);
    }


    /**
     * 前序遍历
     * 头左右
     */
    public static void forhead(MultiTreeNode root) {
        //头
        System.out.println(root.getData());
        //递归终止条件,叶子节点则不继续递归了
        if (CollectionUtils.isEmpty(root.getNodes())) {
            return;
        }
        //从左到右递归节点
        for (MultiTreeNode node : root.getNodes()) {
            forhead(node);
        }
    }

    /**
     * 后序遍历
     * 左右头
     */
    public static void post(MultiTreeNode root) {
        //递归终止条件,叶子节点则不继续递归了
        if (CollectionUtils.isEmpty(root.getNodes())) {
            System.out.println(root.getData());
            return;
        }
        //从左到右递归节点
        for (MultiTreeNode node : root.getNodes()) {
            post(node);
        }
        //自身
        System.out.println(root.getData());
    }



    public static MultiTreeNode buildTree() {
        MultiTreeNode root = new MultiTreeNode();
        root.setData(1);
        MultiTreeNode node1 = new MultiTreeNode();
        node1.setData(2);
        MultiTreeNode node2 = new MultiTreeNode();
        node2.setData(3);
        MultiTreeNode node3 = new MultiTreeNode();
        node3.setData(4);
        ArrayList<MultiTreeNode> multiTreeNodes = Lists.newArrayList(node1, node2, node3);
        root.setNodes(multiTreeNodes);
        MultiTreeNode node4 = new MultiTreeNode();
        node4.setData(10);
        MultiTreeNode node5 = new MultiTreeNode();
        node5.setData(11);
        ArrayList<MultiTreeNode> multiTreeNodes1 = Lists.newArrayList(node4, node5);
        node1.setNodes(multiTreeNodes1);
        MultiTreeNode node6 = new MultiTreeNode();
        node6.setData(20);
        node3.setNodes(Lists.newArrayList(node6));
        return root;
    }
}
