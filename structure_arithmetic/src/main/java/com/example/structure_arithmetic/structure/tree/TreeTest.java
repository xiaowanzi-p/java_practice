package com.example.structure_arithmetic.structure.tree;

public class TreeTest {

    public static void main(String[] a) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1);
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        String s = tree.forEach(1);
        System.out.println(s);
        String s1 = tree.forEach(2);
        System.out.println(s1);
        String s2 = tree.forEach(3);
        System.out.println(s2);
    }
}
