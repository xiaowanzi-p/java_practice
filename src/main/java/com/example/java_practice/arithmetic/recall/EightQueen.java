package com.example.java_practice.arithmetic.recall;

/**
 * 八皇后
 */
public class EightQueen {

    //下标表示行row，值代表列coulun
    private int[] array = new int[8];
    public EightQueen() {
        //初始化值-1置空
        for (int i=0; i<8; i++) {
            array[i] = -1;
        }
    }

    public void eightQueen(int row) {
        //递归终止条件
        if (row == 8) {
            System.out.println();
            print();
            return;
        }
        //遍历每个列确认位置是否放置合适
        for (int i=0; i<8; i++) {
            if (isOk(row,i)) {
                //将合适的位置放好
                array[row] = i;
                //确认下一行的位置
                eightQueen(row+1);
            }
        }
    }

    //判断当前位置是否合适放置Queen
    private boolean isOk(int row, int colum) {
        int leftColum = colum - 1;
        int rightColum = colum + 1;
        for (int i=(row-1); i>=0; i--) {
            //当前列有没有数据
            if (array[i] == colum) {
                return false;
            }
            //左对角线
            if (array[i] == leftColum) {
                return false;
            }
            //右对角线
            if (array[i] == rightColum) {
                return false;
            }
            leftColum--;
            rightColum++;
        }
        return true;
    }

    private void print() {
        for (int i=0; i<8; i++) {
            StringBuilder builder = new StringBuilder();
            int colum = array[i];
            for (int j=0; j<8; j++) {
                if (colum == j) {
                    builder.append("Q ");
                } else {
                    builder.append("* ");
                }
            }
            System.out.println(builder.toString());
        }
    }

}
