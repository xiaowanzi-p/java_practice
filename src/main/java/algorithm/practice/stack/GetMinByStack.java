package algorithm.practice.stack;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 实现一个栈,要求实现getMin最小元素的方法，且时间复杂度是O1
 * 可以基于已有栈结构实现
 *
 * @author 君墨笑
 * @date 2023/12/11
 */
public class GetMinByStack {

    //原始栈
    private static Stack<Integer> stack = new Stack<>();
    //最小值栈
    private static Stack<Integer> minStack = new Stack<>();

    public static void main(String[] a) {
        push(10);
        push(4);
        push(4);
        push(6);
        push(5);
        push(3);
        System.out.println("最小值: " + getMin());
        System.out.println("弹出: " + pop());
        System.out.println("最小值: " + getMin());
        System.out.println("弹出: " + pop());
        System.out.println("弹出: " + pop());
        System.out.println("最小值: " + getMin());
    }


    private static void push(int value) {
        stack.push(value);
        try {
            Integer pop = minStack.peek();
            if (value < pop) {
                minStack.push(value);
            } else {
                minStack.push(pop);
            }
        } catch (EmptyStackException e) {
            minStack.push(value);
        }
    }

    private static int pop() {
        Integer pop = stack.pop();
        minStack.pop();
        return pop;
    }

    private static int getMin() {
        return minStack.peek();
    }
}
