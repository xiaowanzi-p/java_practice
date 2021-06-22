package com.example.java_practice.demo;


import com.example.java_practice.arithmetic.dynamic.MatrixNodeDistance;
import com.example.java_practice.arithmetic.kahn.Kahn;

public class Demo {
    public static void main(String[] a) {
        Kahn kahn = new Kahn();
        kahn.initData();
        String sort = kahn.sort();
        System.out.println(sort);
    }
}


