package qinfeng.zheng.date_20210829;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/4 17:26
 * @dec 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 */
public class A_04_特殊链表拷贝 {
    public static class Node {
        int value;
        Node next;
        Node rand;

        public Node(int val) {
            this.value = val;
        }
    }

    // 使用hashMap来实现的链表的拷贝
    public static Node copyLinkedListWithRandom(Node head) {

        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();


        Node cur = head;
        //第1次遍历老链表，将每个Node节点进行拷贝，并且建立好老链表节点与拷贝节点的映射关系
        while (cur != null) {
            // 将所有的Node的节点都拷贝一分，存放在map中，并且通过 老Node ~ 新Node映射！
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        // 重新遍历老链表来构造新链表的顺序
        cur = head; // 重用变量而已
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        // 返回新链表的头节点
        return map.get(head);
    }


    public static Node copyLinkedListWithRandom2(Node head) {
        if (head == null) {
            return null;
        }


        Node cur = head;
        Node next = null;
        // 1. 拷贝节点，将链表 1->2->3->4 变成 1->1->2->2->3->3->4->4
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        // 2. 建立拷贝节点random节点之间的关系
        cur = head;
        Node copyCur = null;
        while (cur != null) {
            next = cur.next.next;
            copyCur = cur.next;
            copyCur.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        // 3. 拆分成两个链表
        Node copyHead = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyCur = cur.next;
            cur.next = next;  // 复原老链表
            copyCur.next = cur.next != null ? cur.next.next : null; //构建copy出来的链表
            cur = next;
        }

        return copyHead;
    }

    public static void printNode(Node head) {
        Node cur = head;
        System.out.print("next指针值:");
        while (cur != null) {
            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println();

        cur = head;
        System.out.print("random指针值:");
        while (cur != null) {
            Integer integer = cur.rand != null ? cur.rand.value : null;
            System.out.print(integer + " -> ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.rand = node3;
        node2.rand = node4;
        node3.rand = node1;
        node4.rand = node2;

        printNode(node1);
        //Node node = copyLinkedListWithRandom(node1);
        Node node = copyLinkedListWithRandom2(node1);
        System.out.println("----------------");
        printNode(node);
    }

}
