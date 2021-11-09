package qinfeng.zheng.date_20210826;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/27 21:47
 * @dec
 */
public class A_03_删除链表中的某个值 {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

    }


    public static Node removeValue(Node head, int value) {

        while (head != null) {
            if (head.data != value) {
                break;
            }
            head = head.next;
        }

        Node pre = head;  // 前一个节点
        Node cur = head;  // 当前节点

        while (cur != null) {
            if (cur.data == value) {
                // 如果当前的值等于value , 就将前一个节点的next指针指向当前节点的下一个节点,相当于删除了当前节点
                pre.next = cur.next;
            } else {
                // 前一个节点移到当前节点
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static Node genNode(int length, int maxValue) {
        Node head = null;
        for (int i = 0; i < length; i++) {
            int data = (int) (Math.random() * maxValue) + 1;
            Node cur = new Node(data);
            cur.next = head;  // 相当于HashMap resize方法中的头插法
            head = cur;
        }
        return head;
    }

    // 拷贝一个链表,并没有使有额外空间
    public static Node copyNode(Node head) {

        Node newHead = null;
        while (head != null) {
            Node node = new Node(head.data);
            node.next = newHead;
            newHead = node;
            head = head.next;
        }

        Node prev = null;
        Node next = null;
        while (newHead != null) {
            next = newHead.next;
            newHead.next = prev;
            prev = newHead;
            newHead = next;
        }


        return prev;


    }


    public static void main(String[] args) {
        Node node = genNode(3, 5);
        Node node1 = copyNode(node);
        System.out.println(node1);
    }

}
