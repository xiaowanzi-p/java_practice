package algorithm.practice.violence;

/**
 * 八皇后问题,标准解有92个
 */
public class EightQueen {


    public static void main(String[] a) {
        int[] num = {0};
        process(8, 0, new int[]{-1, -1, -1, -1, -1, -1, -1, -1}, num);
        System.out.println("数量:" + num[0]);
    }


    /**
     * @param n 总共多少行多少列
     * @param row 当前行
     * @param array 存皇后摆放的位置，下标表示行，value表示列
     */
    private static void process(int n, int row, int[] array, int[] num) {
        //皇后摆放无误
        if (row >= n) {
            //printQueen(array);
            num[0] = num[0] + 1;
            return;
        }
        for (int col=0; col<n; col++) {
            if (isOk(array,row,col)) {
                int temp = array[row];
                array[row] = col;
                process(n,row+1,array,num);
                array[row] = temp;
            }
        }
    }

    private static boolean isOk(int[] array, int row, int col) {
        for (int i=0; i<array.length; i++) {
            //列相等
            if (array[i] != -1 && array[i] == col) {
                return false;
            }
            //对角线相等
            if (array[i] != -1 && Math.abs(i-row) == Math.abs(array[i]-col)) {
                return false;
            }
        }
        return true;
    }

    private static void printQueen(int[] array) {
        for (int i=0; i<array.length; i++) {
            int value = array[i];
            if (value == 0) {
                System.out.println("Q * * * * * * *");
            }
            if (value == 1) {
                System.out.println("* Q * * * * * *");
            }
            if (value == 2) {
                System.out.println("* * Q * * * * *");
            }
            if (value == 3) {
                System.out.println("* * * Q * * * *");
            }
            if (value == 4) {
                System.out.println("* * * * Q * * *");
            }
            if (value == 5) {
                System.out.println("* * * * * Q * *");
            }
            if (value == 6) {
                System.out.println("* * * * * * Q *");
            }
            if (value == 7) {
                System.out.println("* * * * * * * Q");
            }
        }
        System.out.println("============");
    }
}
