package qinfeng.zheng.date_20210904;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/6 22:44
 * @dec 二叉树的序列化只有前序后序+ 水平序列化， 中序不行！！！因为不准确
 */
public class A_05_二叉树的序列化_后序 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    // 使用后序的方式对二叉树进行序列化
    public static Queue<String> postSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        postS(queue, head);
        return queue;
    }

    private static void postS(Queue<String> queue, Node head) {
        if (head == null) {
            queue.add(null);
        } else {
            postS(queue, head.left);
            postS(queue, head.right);
            queue.add(String.valueOf(head.value));
        }
    }


    // 后序的反序列化操作，注意将序列化的Queue转换成Stack,所以实现代码与前序一样了
    public static Node postb(Stack<String> nodelist) {
        String value = nodelist.pop();
        if (value == null) {
            return null;
        }
        Node cur = new Node(Integer.valueOf(value));
        cur.right = postb(nodelist);
        cur.left = postb(nodelist);
        return cur;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node6;
        node3.right = node7;
        Queue<String> queue = postSerial(node1);
        System.out.println(queue);
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        Node head = postb(stack);


    }
}
