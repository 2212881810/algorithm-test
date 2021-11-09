package qinfeng.zheng.date_20210904;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/6 22:09
 * @dec 二叉树的序列化和反序列化
 */
public class A_04_二叉树的序列化_先序 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int v) {
            this.value = v;
        }
    }


    // 先序, 和先序遍历差不多，只不过是要将null表示出来，然后反序列化的时好用
    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        preS(head, queue);
        return queue;

    }

    private static void preS(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null); // 如果节点是null 就null表示
        } else {
            queue.add(String.valueOf(head.value));
            preS(head.left, queue);
            preS(head.right, queue);
        }
    }

    // 先序反序列化
    public static Node preb(Queue<String> nodeList) {
        String value = nodeList.poll();
        if (value == null) {
            return null;
        }
        Node cur = new Node(Integer.valueOf(value));
        cur.left = preb(nodeList);
        cur.right = preb(nodeList);
        return cur;
    }


}
