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


    public static void push(int data) {
        //初始化数据
        if (head == null && tail == null) {
            SingleListNode node = new SingleListNode();
            node.setData(data);
            head = node;
            tail = node;
            return;
        }
        //队尾节点拿出来指向下一个节点，同时队尾指针向后移动
        SingleListNode node = new SingleListNode();
        node.setData(data);
        tail.setNext(node);
        tail = node;
    }

    public static int pop() {
        if (head == null) {
            return -1;
        }
        //拿出当前数据
        int data = head.getData();
        //头指针向后移位
        SingleListNode next = head.getNext();
        head = next;
        //头是空则说明队列为空,清除tail数据
        if (head == null) {
            tail = null;
        }
        return data;
    }
}
