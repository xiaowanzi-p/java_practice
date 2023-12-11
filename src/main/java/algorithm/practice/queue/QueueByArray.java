package algorithm.practice.queue;

/**
 * 使用数组循环使用构成队列
 *
 * @author 君墨笑
 * @date 2023/12/11
 */
public class QueueByArray {

    private static int[] array;
    private static int pushIndex = 0;
    private static int popIndex = 0;
    private static int size = 0;
    private static int capacity;

    public static void main(String[] a) {
        buildQueue(4);
        push(10);
        push(11);
        push(12);
        push(13);
        push(14);
        System.out.println("pop：" + pop());
        push(15);
        System.out.println("pop：" + pop());
        System.out.println("pop：" + pop());
        System.out.println("pop：" + pop());
        push(16);
        System.out.println("pop：" + pop());
        System.out.println("pop：" + pop());
        System.out.println("pop：" + pop());
        System.out.println("pop：" + pop());
        System.out.println("pop：" + pop());
    }

    private static void buildQueue(int length) {
        capacity = length;
        array = new int[length];
    }


    private static void push(int value) {
        if (size >= capacity) {
            System.out.println("队列已满");
            return;
        }
        array[pushIndex] = value;
        size++;
        pushIndex = setIndex(pushIndex);
    }

    private static int pop() {
        if (size <= 0) {
            return -1;
        }
        int value = array[popIndex];
        size--;
        popIndex = setIndex(popIndex);
        return value;
    }

    private static int setIndex(int index) {
        int tempt = index + 1;
        if (tempt >= array.length) {
            return 0;
        } else {
            return tempt;
        }
    }
}
