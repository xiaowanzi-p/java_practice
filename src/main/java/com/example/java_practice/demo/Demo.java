package com.example.java_practice.demo;

import com.example.java_practice.datastructure.graph.Graph;

public class Demo {
    public static void main(String[] a) {
        Graph graph = new Graph(true);
        graph.add(1,2,0);
        graph.add(1,3,0);
        graph.add(3,2,0);
        graph.add(3,4,0);
        graph.add(2,5,0);
        graph.add(4,5,0);
        graph.add(5,6,0);
        graph.add(6,7,0);
        graph.add(7,8,0);
        graph.add(4,8,0);
        String result = graph.bfs(8);
        System.out.println(result);
    }







}


