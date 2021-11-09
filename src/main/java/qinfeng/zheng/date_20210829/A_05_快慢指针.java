package qinfeng.zheng.date_20210829;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/5 13:56
 * @dec
 */
public class A_05_快慢指针 {

    class Node {
        int value;
        Node next;

        public Node(int v, Node next) {
            this.value = v;
            this.next = next;
        }
    }


    //1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点

    public static Node getMidOrUpMidNode(Node head) {

        // 没有节点，一个节点，两个节点
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }


        Node fast = head.next.next;
        Node slow = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node getMidOrDownMidNode(Node head) {
        // 无节点，或只有一个节点
        if (head == null) {
            return null;
        }
        Node f = head.next;
        Node s = head.next;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    //3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node getPreMidOrUpMidNode(Node head) {

        //  无节点，一个节点，两个节点
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node f = head.next.next;
        Node s = head;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    //4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node getPreMidOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node f = head.next;
        Node s = head;

        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

}
