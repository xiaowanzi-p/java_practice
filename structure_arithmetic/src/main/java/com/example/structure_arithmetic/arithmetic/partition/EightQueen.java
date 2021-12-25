package com.example.structure_arithmetic.arithmetic.partition;

public class EightQueen {

    //存储八皇后的位置,下标表示row,值表示column
    private int[] array = new int[8];

    public EightQueen() {
        //初始化八皇后全部为空白
        for (int i=0; i<array.length; i++) {
            array[i] = -1;
        }
    }


    public void search(int row) {
        //递归结束条件
        if (row == 8) {
            //打印结果
            print();
            return;
        }

        //递归公式
        for (int column=0; column<8; column++) {
            if (isOk(row,column)) {
                array[row] = column;
                search(row+1);
            }
        }
    }


    private boolean isOk(int row, int column) {
        //判断列
        for (int i=0; i<row; i++) {
            if (array[i] == column) {
                return false;
            }
        }
        //判断左右对角线
        if (row > 0) {
            int left = column - 1;
            int right = column + 1;
            for (int i=row-1; i>=0; i--) {
                if (array[i] == left || array[i] == right) {
                    return false;
                }
                left--;
                right++;
            }
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
        System.out.println();
    }



    public static void main(String[] a) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.search(0);
    }

}
