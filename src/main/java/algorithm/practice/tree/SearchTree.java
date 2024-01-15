package algorithm.practice.tree;

public class SearchTree {

    public static void main(String[] a) {
        TreeNode head = new TreeNode();
        head.data = 4;
        TreeNode node1 = new TreeNode();
        node1.data = 3;
        TreeNode node2 = new TreeNode();
        node2.data = 6;
        TreeNode node3 = new TreeNode();
        node3.data = 2;
        TreeNode node4 = new TreeNode();
        node4.data = 7;
        TreeNode node5 = new TreeNode();
        node5.data = 5;
        TreeNode node6 = new TreeNode();
        node6.data = 8;
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        //node1.right = node4;
        node2.left = node5;
        node6.left = node4;
        Info info = process(head);
        System.out.println("最大值: " + info.max);
        System.out.println("最小值: " + info.min);
        System.out.println("搜索: " + info.search);

        //TreeNode node7 = new TreeNode();
    }


    private static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int min = head.data;
        int max = head.data;
        boolean search = true;
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            search = search && left.search && head.data > left.max;
        }
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            search = search && right.search && head.data < right.min;
        }
        return new Info(search,min,max);
    }

    private static class Info {
        /**
         * 是否是搜索树
         */
        public boolean search;
        /**
         * 最小值
         */
        public int min;
        /**
         * 最大值
         */
        public int max;

        public Info(boolean search, int min, int max) {
            this.search = search;
            this.max = max;
            this.min = min;
        }
    }
}
