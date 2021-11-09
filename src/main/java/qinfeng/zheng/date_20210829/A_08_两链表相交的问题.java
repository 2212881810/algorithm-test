package qinfeng.zheng.date_20210829;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/5 22:08
 * @dec 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 */
public class A_08_两链表相交的问题 {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getFirstLoopNode2(head1);
        Node loop2 = getFirstLoopNode2(head2);
        // 两个链表都是无环的
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        // 两个链表都是有环的,不存在一个链表有环，一个链表无环的情况
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    // 两链表相交，返回相交的第一个节点，如果不相交，返回null
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) { // 两链表的入环节点相同，但不能说明这个节点就是它们第一个相交节点
            cur1 = head1;
            cur2 = head2;

            int count = 0;
            while (cur1 != loop1) { // 因为loop1 == loop2，所以环中的节点不用比较了，所以就不用比较到最后了！！！有个环也不会存在最后一个节点
                count++;
                cur1 = cur1.next;
            }


            while (cur2 != loop2) {
                count--;
                cur2 = cur2.next;
            }

            cur1 = count > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            // 干掉长链表上多余的节点
            while (count != 0) {
                count--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {  // 相等时，就是第一个相交的节点
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            //遍历环
            while (cur1 != loop1) {  //当cur1 == loop1时，说明遍历了整个环
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }

    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2) {
        // 如果有一个链表是null ,那肯定不相交了，返回null
        if (head1 == null || head2 == null) {
            return null;
        }

        int count = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        // 注意这个小技巧：while的条件是cur1.next != null , 因为后面要用这个cur1进行条件判断
        // 如果直接用cur1 != null, 那么后面就没法就cur1 进行判断了，因为它是个null,
        while (cur1.next != null) {
            count++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            count--;
            cur2 = cur2.next;
        }
        // 因为 head1,head2都是无环链表，如果相交，那么最后一个节点一定是同一个节点
        if (cur1 != cur2) {
            return null;
        }

        // count : 链表head1的长度减去链表head2的长度
        cur1 = count > 0 ? head1 : head2; //  将长的链表用cur1表示
        cur2 = cur1 == head1 ? head2 : cur1;

        // 将长的链表多的那部分节点过掉
        while (count != 0) {
            count--;
            cur1 = cur1.next;
        }


        while (cur1 != cur2) { // 当cur1 == cur2时，就是第1个相交的节点
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        // return cur2;

        return cur1;
    }


    //获取链表的第一个入环节点,如果无环，返回null
    // 这种方式，使用Set来判断,空间复杂度O(N)
    public static Node getFirstLoopNode(Node head) {
        Set<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            } else {
                set.add(cur);
            }
            cur = cur.next;
        }
        return null;
    }

    //获取链表的第一个入环节点,如果无环，返回null
    //这种方式使用快慢节点来实现，时间复杂度O(1)
    public static Node getFirstLoopNode2(Node head) {
        // 小于3个节点，不可能成环
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node s = head.next;
        Node f = head.next.next;

        while (s != f) { // 找到快慢节点第一次相遇的节点
            if (f != null && f.next != null) {
                s = s.next;
                f = f.next.next;
            }else
                return null;
        }

        f = head;  // 快节点回头开始位置
        while (s != f) {  // 快慢节点第二次相遇的位置即是入环节点
            s = s.next;
            f = f.next;
        }
        return s;
    }


    public static void main(String[] args) {
        //Node head1 = null;
        //head1 = new Node(1);
        //head1.next = new Node(2);
        //head1.next.next = new Node(3);
        //head1.next.next.next = new Node(4);
        //head1.next.next.next.next = new Node(5);
        //head1.next.next.next.next.next = new Node(6);
        //head1.next.next.next.next.next.next = new Node(7);
        //head1.next.next.next.next.next.next.next = head1.next.next.next; // 7->4
        //
        ////Node firstLoopNode = getFirstLoopNode(head1);
        //Node firstLoopNode = getFirstLoopNode2(head1);
        //System.out.println(firstLoopNode);


        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
