package algorithm.practice.tree;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * 前缀树
 *
 * @author 君墨笑
 * @date 2023/12/14
 */
public class PrefixTree {

    //前缀树头节点
    private static TreeNode head = new TreeNode();

    @Getter
    @Setter
    public static class TreeNode {
        //通过节点的数量
        private int pass;
        //节点为尾结束的数量
        private int end;
        private HashMap<Character,TreeNode> map = new HashMap<>();
    }

    public static void main(String[] a) {
        String str1 = "ad";
        String str2 = "adcd";
        insert(str1);
        insert(str2);
        System.out.println("包含：" + contain(str1));
        System.out.println("包含：" + contain(str2));
        System.out.println("包含：" + containPrefix(str1));
        remove(str2);
        TreeNode node = head;
        System.out.println("包含：" + contain(str1));
        System.out.println("包含：" + contain(str2));
        System.out.println("包含：" + containPrefix(str1));

    }

    //将字符串加入前缀树
    private static void insert(String value) {
        if (StringUtils.isBlank(value)) {
            return;
        }
        //拆分
        char[] chars = value.toCharArray();
        TreeNode currentNode = head;
        for (char str : chars) {
            TreeNode node = currentNode.getMap().get(str);
            if (node == null) {
                node = new TreeNode();
                currentNode.getMap().put(str,node);
            }
            node.setPass(node.getPass() + 1);
            currentNode = node;
        }
        currentNode.setEnd(currentNode.getEnd() + 1);
    }

    //给定字符串,返回前缀树内添加过几次该字符串
    private static int contain(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        //拆分
        char[] chars = value.toCharArray();
        TreeNode currentNode = head;
        for (char str : chars) {
            TreeNode node = currentNode.getMap().get(str);
            if (node == null) {
                return 0;
            }
            currentNode = node;
        }
        return currentNode.getEnd();
    }

    //给定字符串,返回前缀树内包含该前缀的字符串有几个
    private static int containPrefix(String value) {
        if (StringUtils.isBlank(value)) {
            return -1;
        }
        //拆分
        char[] chars = value.toCharArray();
        TreeNode currentNode = head;
        for (char str : chars) {
            TreeNode node = currentNode.getMap().get(str);
            if (node == null) {
                return -1;
            }
            currentNode = node;
        }
        return currentNode.getPass();
    }

    //前缀树中移除给定的字符串
    private static void remove(String value) {
        if (StringUtils.isBlank(value)) {
            return;
        }
        if (contain(value) == 0) {
            return;
        }
        //拆分
        char[] chars = value.toCharArray();
        TreeNode currentNode = head;
        for (char str : chars) {
            TreeNode node = currentNode.getMap().get(str);
            node.setPass(node.getPass() - 1);
            if (node.getPass() == 0) {
                currentNode.getMap().remove(str);
                return;
            }
            currentNode = node;
        }
        currentNode.setEnd(currentNode.getEnd() - 1);
    }
}
