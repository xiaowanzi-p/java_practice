package com.example.java_practice.demo;


import com.example.java_practice.arithmetic.dynamic.MatrixNodeDistance;

public class Demo {
    public static void main(String[] a) {
        MatrixNodeDistance nodeDistance = new MatrixNodeDistance();
        nodeDistance.printMatrix();
        nodeDistance.dynamicDistance(0);
        System.out.println(nodeDistance.getTargetDistance());
        System.out.println(nodeDistance.getTargetRoute());
        Integer distance = nodeDistance.dynamicDistance();
        System.out.println(distance);
        System.out.println(nodeDistance.getTargetRoute());
    }
}


