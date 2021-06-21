package com.example.java_practice.datastructure.tree;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryTree<T extends Comparable<? super T>> {

    private int size = 0;
    private TreeNode<T> root;

    //增加节点
    public void addNode(T t) {
        if (size == 0) {
            root = new TreeNode<>();
            root.data = t;
            size++;
        } else {
            //找到添加的叶子节点然后添加
            addLeafNode(t);
            size++;
        }
    }

    //获取节点的树高度
    public int treeHight(TreeNode<T> root) {
        return maxHight(root);
    }

    public String toString(int type) {
        //前序遍历 自—左—右
        return forEach(type);
    }

    private int maxHight(TreeNode<T> root) {
        //递归结束条件
        if (root == null) {
            return 0;
        }
        TreeNode<T> left = root.left;
        TreeNode<T> right = root.right;
        if (left == null && right == null) {
            return 0;
        }

        //递归公式
        Integer leftHight = maxHight(left);
        Integer rightHight = maxHight(right);
        return Math.max(leftHight,rightHight) + 1;
    }

    private String forEach(int type) {
        StringBuilder builder = new StringBuilder();
        builder.append("Tree[");
        if (size != 0) {
            //递归公式 根节点 + 左节点 + 右节点
            if (type == 1) {
                preRecursion(root,builder);
            }
            if (type == 2) {
                midRecursion(root,builder);
            }
            if (type == 3) {
                postRecursion(root,builder);
            }
        }
        if (size != 0) {
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append("]");
        builder.append(", Size="+size);
        return builder.toString();
    }

    //后序遍历
    private void postRecursion(TreeNode<T> node, StringBuilder builder) {
        //递归结束条件
        if (node == null) {
            return;
        }
        TreeNode<T> left = node.left;
        TreeNode<T> right = node.right;
        if (left == null && right == null) {
            builder.append(JSONObject.toJSONString(node.data) + ",");
            return;
        }
        //左
        postRecursion(left,builder);
        //右
        postRecursion(right,builder);
        //自身
        builder.append(JSONObject.toJSONString(node.data) + ",");
    }

    //中序遍历，排序好的
    private void midRecursion(TreeNode<T> node, StringBuilder builder) {
        //递归结束条件
        if (node == null) {
            return;
        }
        TreeNode<T> left = node.left;
        TreeNode<T> right = node.right;
        if (left == null && right == null) {
            builder.append(JSONObject.toJSONString(node.data) + ",");
            return;
        }
        //左
        midRecursion(left,builder);
        //自身
        builder.append(JSONObject.toJSONString(node.data) + ",");
        //右
        midRecursion(right,builder);
    }

    //前序遍历
    private void preRecursion(TreeNode<T> node, StringBuilder builder) {
        //递归结束条件
        if (node == null) {
            return;
        }
        //自身
        builder.append(JSONObject.toJSONString(node.data) + ",");
        TreeNode<T> left = node.left;
        TreeNode<T> right = node.right;
        if (left == null && right == null) {
            return;
        }
        //左
        preRecursion(left,builder);
        //右
        preRecursion(right,builder);
    }

    private static class TreeNode<T> {
        public TreeNode<T> left;
        public TreeNode<T> right;
        public T data;
    }

    private void addLeafNode(T t) {
        Comparable comparable = t;
        TreeNode<T> node = root;
        while (node != null) {
            Comparable n = node.data;
            //判断大小左右节点
            //左节点
            if (comparable.compareTo(n) < 0) {
                //左节点
                TreeNode<T> left = node.left;
                if (left != null) {
                    node = left;
                } else {
                    TreeNode<T> leaf = new TreeNode<>();
                    leaf.data = t;
                    node.left = leaf;
                    return;
                }
            } else {
                //右节点
                TreeNode<T> right = node.right;
                if (right != null) {
                    node = right;
                } else {
                    TreeNode<T> leaf = new TreeNode<>();
                    leaf.data = t;
                    node.right = leaf;
                    return;
                }
            }
        }
    }
}
