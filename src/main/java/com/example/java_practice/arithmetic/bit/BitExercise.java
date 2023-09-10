package com.example.java_practice.arithmetic.bit;

import org.apache.commons.lang3.StringUtils;

public class BitExercise {

    public static void main(String[] a) {
        printBit(-10);
    }

    /**
     * 打印二进制
     */
    public static void printBit(int a) {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<=31; i++) {
            int c = a & (1 << i);
            String s = c == 0 ? "0" : "1";
            builder.append(s);
        }
        String str = builder.toString();
        System.out.println(StringUtils.reverse(str));
    }
}
