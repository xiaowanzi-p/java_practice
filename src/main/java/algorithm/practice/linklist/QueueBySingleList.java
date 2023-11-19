package algorithm.practice.linklist;

/**
 * 单链表实现队列
 */
public class QueueBySingleList {

    //队头指针
    private static SingleListNode head = null;
    //队尾指针
    private static SingleListNode tail = null;

    public static void main(String[] a) {

    }


    public static int push(int data) {
        //初始化数据
        if (head == null && tail == null) {
            SingleListNode node = new SingleListNode();
            node.setData(data);
            head = node;
            tail = node;
        }

    }

    public static int pop() {

    }
}
