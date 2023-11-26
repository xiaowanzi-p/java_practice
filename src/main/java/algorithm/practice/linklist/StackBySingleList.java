package algorithm.practice.linklist;

public class StackBySingleList {

    //栈顶指针
    private static SingleListNode stack = null;

    public static void main(String[] a) {
        System.out.println(pop());
        push(20);
        push(30);
        push(40);
        System.out.println(pop());
        push(50);
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }

    //压栈
    private static void push(int data) {
        if (stack == null) {
            SingleListNode node = new SingleListNode();
            node.setData(data);
            stack = node;
            return;
        }
        SingleListNode node = new SingleListNode();
        node.setData(data);
        node.setNext(stack);
        stack = node;
    }

    //弹栈
    private static int pop() {
        if (stack == null) {
            return -1;
        }
        int data = stack.getData();
        SingleListNode next = stack.getNext();
        stack = next;
        return data;
    }
}
