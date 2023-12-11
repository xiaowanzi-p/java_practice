package algorithm.practice.stack;

import java.util.Stack;

/**
 * 使用栈结构实现队列
 *
 * @author 君墨笑
 * @date 2023/12/11
 */
public class QueueByStack {

    private static Stack<Integer> stack = new Stack<>();
    private static Stack<Integer> help = new Stack<>();

    public static void main(String[] a) {
        System.out.println("弹出：" + pop());
        push(1);
        push(2);
        push(3);
        System.out.println("弹出：" + pop());
        System.out.println("弹出：" + pop());
        push(4);
        push(5);
        push(6);
        System.out.println("弹出：" + pop());
        System.out.println("弹出：" + pop());
        System.out.println("弹出：" + pop());
        System.out.println("弹出：" + pop());
        System.out.println("弹出：" + pop());
    }


    private static void push(int value) {
        //先将help栈弹出所有数据依次push进入stack栈
        if (help.empty()) {
            stack.push(value);
        } else {
            while (!help.empty()) {
                Integer pop = help.pop();
                stack.push(pop);
            }
            stack.push(value);
        }
    }


    private static int pop() {
        //先将stack栈的所有数据弹出依次push进入help栈，然后再help栈弹出
        if (stack.empty()) {
            if (help.empty()) {
                return -1;
            } else {
                return help.pop();
            }
        } else {
            while (!stack.empty()) {
                Integer pop = stack.pop();
                help.push(pop);
            }
            return help.pop();
        }
    }
}
