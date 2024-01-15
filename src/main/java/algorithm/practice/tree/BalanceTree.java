package algorithm.practice.tree;

public class BalanceTree {


    public static void main(String[] a) {
        TreeNode head = new TreeNode();
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        TreeNode node4 = new TreeNode();
        TreeNode node5 = new TreeNode();
        TreeNode node6 = new TreeNode();
        TreeNode node7 = new TreeNode();
        TreeNode node8 = new TreeNode();
        head.setLeft(node1);
        head.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node5.setRight(node7);
        node7.setLeft(node8);
        Info info = process(head);
        System.out.println("树高: " + info.heigth);
        System.out.println("平衡: " + info.balance);

    }


    /**
     * 判断一棵树是否是平衡树
     */
    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0,true);
        }
        //左树
        Info left = process(head.left);
        //右树
        Info right = process(head.right);
        //自己
        int height = Math.max(left.heigth, right.heigth) + 1;
        boolean balance = left.balance && right.balance && Math.abs(left.heigth- right.heigth) <= 1;
        return new Info(height,balance);
    }

    private static class Info {
        /**
         * 树高
         */
        public int heigth;
        /**
         * 是否平衡
         */
        public boolean balance;

        public Info(int heigth, boolean balance) {
            this.heigth = heigth;
            this.balance = balance;
        }
    }
}
