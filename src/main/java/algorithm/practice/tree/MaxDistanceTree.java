package algorithm.practice.tree;

public class MaxDistanceTree {

    public static void main(String[] a) {

    }


    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0,0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        int max1 = Math.max(left.max,right.max);
        int max2 = left.height + right.height + 1;
        int max = Math.max(max1,max2);
        return new Info(height,max);
    }

    private static class Info {
        /**
         * 树高
         */
        public int height;
        /**
         * 最大距离
         */
        public int max;

        public Info(int height, int max) {
            this.height = height;
            this.max = max;
        }
    }
}
