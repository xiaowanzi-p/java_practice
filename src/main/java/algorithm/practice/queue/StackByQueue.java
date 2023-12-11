package algorithm.practice.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用队列结构实现栈
 *
 * @author 君墨笑
 * @date 2023/12/11
 */
public class StackByQueue {

    private static Queue<Integer> queue = new ArrayDeque();
    private static Queue<Integer> help = new ArrayDeque();

    public static void main(String[] a) {
        System.out.println("弹出: " + pop());
        push(1);
        push(2);
        push(3);
        System.out.println("弹出: " + pop());
        System.out.println("弹出: " + pop());
        push(4);
        push(5);
        System.out.println("弹出: " + pop());
        System.out.println("弹出: " + pop());
        System.out.println("弹出: " + pop());
    }


    private static void push(int value) {
        queue.offer(value);
    }

    private static int pop() {
        if (queue.isEmpty()) {
            return -1;
        }
        while (queue.size() > 1) {
            Integer poll = queue.poll();
            help.offer(poll);
        }
        Integer poll = queue.poll();
        //交换对列
        Queue<Integer> tempt = queue;
        queue = help;
        help = tempt;
        return poll;
    }
}
