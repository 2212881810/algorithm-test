package qinfeng.zheng.date_20210826;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/26 22:23
 * @dec
 */
public class A_01_单链表_反转 {
    public static class Node {
        Integer data;
        Node next;
        public Node() {

        }
        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 单链表反转
     *
     * @param head 原链表的头节点
     * @return 反转后新链表的头节点
     */
    public static Node reverseNode(Node head) {
        Node prev = null;
        Node next = null;
        while (head != null) {
            next = head.next;

            // 这两行代码其实挺巧妙的,真的！！！
            head.next = prev;
            prev = head;


            head = next;
        }
        return prev;
    }

    public static Node testReverseNode(Node head) {
        List<Node> list = new ArrayList<>();
        while (head != null) {
            Node node = new Node();
            node.data = head.data;
            list.add(node);
            head = head.next;
        }
        for (int i = (list.size() - 1); i > 0; i--) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(list.size() - 1);
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node node = testReverseNode(node1);
        System.out.println(node);

        Node node6 = reverseNode(node1);
        System.out.println(node6);
    }
}
