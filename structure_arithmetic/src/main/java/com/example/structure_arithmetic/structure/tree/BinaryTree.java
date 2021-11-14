package com.example.structure_arithmetic.structure.tree;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryTree<T extends Comparable<? super T>> {

    private TreeNode<T> root;

    @Getter
    @Setter
    public class TreeNode<T> {
        private T data;
        private TreeNode<T> left;
        private TreeNode<T> right;
    }


    public void add(T t) {
        if (root == null) {
            TreeNode treeNode = new TreeNode();
            treeNode.setData(t);
            root = treeNode;
        } else {
            addLeadNode(t);
        }
    }

    private void addLeadNode(T t) {
        TreeNode target = root;
        TreeNode preTarget = root;
        String direction = "";
        //确定可以插入的节点
        while (target != null) {
            Comparable data = (Comparable)target.getData();
            Comparable ct = t;
            preTarget = target;
            if (ct.compareTo(data) < 0) {
                //左节点
                direction = "left";
                target = target.getLeft();
            } else {
                //右节点
                direction = "right";
                target = target.getRight();
            }
        }
        //插入节点到确定的节点中
        TreeNode<T> node = new TreeNode<>();
        node.setData(t);
        if (direction.contains("left")) {
            preTarget.setLeft(node);
        } else {
            preTarget.setRight(node);
        }
    }

    public String forEach(int type) {
        StringBuilder builder = new StringBuilder();
        if (type == 1) {
            preForEach(builder, root);
        }
        if (type == 2) {
            midForEach(builder,root);
        }
        if (type == 3) {
            postForEach(builder,root);
        }
        return builder.toString();
    }

    private void preForEach(StringBuilder builder, TreeNode node) {
        //递归公式：自左右
        //递归边界
        if (node == null) {
            return;
        }
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        if (left == null && right == null) {
            builder.append(JSONObject.toJSONString(node.getData()) + ",");
            return;
        }
        //打印自身
        builder.append(JSONObject.toJSONString(node.getData()) + ",");
        //遍历左节点
        preForEach(builder,left);
        //遍历右节点
        preForEach(builder,right);
    }

    private void midForEach(StringBuilder builder, TreeNode node) {
        //递归公式：左自右（结果是排序好的）
        //递归边界
        if (node == null) {
            return;
        }
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        if (left == null && right == null) {
            builder.append(JSONObject.toJSONString(node.getData()) + ",");
            return;
        }
        //遍历左节点
        midForEach(builder,left);
        //打印自身
        builder.append(JSONObject.toJSONString(node.getData()) + ",");
        //遍历右节点
        midForEach(builder,right);
    }

    public void postForEach(StringBuilder builder, TreeNode node) {
        //递归公式：左右自
        //递归边界
        if (node == null) {
            return;
        }
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        if (left == null && right == null) {
            builder.append(JSONObject.toJSONString(node.getData()) + ",");
            return;
        }
        //遍历左节点
        midForEach(builder,left);
        //遍历右节点
        midForEach(builder,right);
        //打印自身
        builder.append(JSONObject.toJSONString(node.getData()) + ",");
    }

}
