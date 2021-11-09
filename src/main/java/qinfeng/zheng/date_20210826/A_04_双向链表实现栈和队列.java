package qinfeng.zheng.date_20210826;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 9:47
 * @dec 双向链表实现栈和队列
 */
public class A_04_双向链表实现栈和队列 {

    /**
     * 定义一个双向链表
     *
     * @param <T>
     */
    private static class Node<T> {
        T value;
        Node prev;
        Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 实现了一个双端队列，然后栈和队列在此双端队列上进行实现
     * @param <T>
     */
    private static class DoubleEndsQueue<T> {
        // 声明两个指针
        Node<T> head;
        Node<T> tail;

        public void addFromHead(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.prev = cur;
                head = cur;
            }
        }

        public void addFromTail(T value) {

            Node<T> cur = new Node<>(value);

            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.prev = tail;
                tail = cur;
            }
        }


        public T popFromHead() {
            if (head == null) {  // 0个节点
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {// 只有一个节点
                head = null;
                tail = null;
            } else { // 至少两个节点
                head = head.next;
                head.prev = null;
                cur.next = null;  // 不那么重要吧
            }
            return cur.value;
        }


        public T popFromTail() {
            if (tail == null) {
                return null;
            }

            Node<T> cur = tail;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                cur.next = null;
                tail = tail.prev;
                tail.next = null;
            }


            return cur.value;
        }
    }


    private static class MyStack<T> {
        DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }
    }


    private static class MyQueue<T> {
        DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromTail();
        }
    }


    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("==========================================");


        MyQueue<String> queue = new MyQueue<>();
        queue.push("A1");
        queue.push("A2");
        queue.push("A3");
        queue.push("A4");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

}
