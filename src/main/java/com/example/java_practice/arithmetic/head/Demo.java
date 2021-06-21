package com.example.java_practice.arithmetic.head;

public class Demo {

    public static void main(String[] a) {
        /*PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.push(4);
        priorityQueue.push(7);
        priorityQueue.push(6);
        priorityQueue.push(1);
        priorityQueue.push(5);
        //14567
        Integer i = priorityQueue.pop();
        while (i != null) {
            System.out.println(i);
            i = priorityQueue.pop();
        }*/

       /* ArrayList<Integer> list = Lists.newArrayList(1, 7, 9, 2, 4, 6, 8, 10);
        String top = HeadExercise.getTopN(list, 3);
        System.out.println(top);*/

        PrecentExercise<Integer> exercise = new PrecentExercise<>(90);
        exercise.addPrecentData(1);
        exercise.addPrecentData(2);
        exercise.addPrecentData(3);
        exercise.addPrecentData(4);
        exercise.addPrecentData(5);
        exercise.addPrecentData(6);
        exercise.addPrecentData(7);
        exercise.addPrecentData(8);
        exercise.addPrecentData(9);
        exercise.addPrecentData(10);
        Integer precentData = exercise.getPrecentData();
        System.out.println(precentData);
    }
}
