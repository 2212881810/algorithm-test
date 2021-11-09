package qinfeng.zheng.date_20210829;

import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/5 14:20
 * @dec 回文链表如： 1 -> 2 -> 3 ->2 -> 1 , 又如  1 -> 2 -> 3 -> 3 ->2 -> 1
 *
 *
 * 这个题技巧真的蛮多的，要好好理解！！！
 * 用了两个多小时，才理解透彻！！！！！！！！！！！！！！！！！
 */
public class A_06_单链表回文判断 {
    public static class Node {
        int value;
        Node next;

        public Node(int v) {
            this.value = v;
        }
    }

    // 使用栈来实现回文链表的判断,空间复杂度O(N)
    public static boolean isPalindrome(Node head) {
        Stack<Integer> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur.value);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 第二种方式，空间复杂度O(1)
    public static boolean isPalindrome2(Node head) {


        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //1. 找到中间节点（奇数长度就是中间节点，偶数长度就是上中点）
        Node mid = slow;
        // 如果链表是： 50 -> 74 -> 11 -> 11 -> 74 -> 50 -> null ,那么rightHead :  11 -> 74 -> 50 -> null
        // 如果链表是： 50 -> 74 -> 11 -> 74 -> 50 -> null ，那么rightHead :  74 -> 50 -> null
        Node rightHead = mid.next;

        //2. 原链表从中间节点断开，分成两个链表
        // 左边的链表以head为头，是 :  50 -> 74 -> 11 -> null
        // 右边的链表以rightHead为头，是： 11 -> 74 -> 50 -> null
        // 同时mid 节点还有值是 11
        mid.next = null;

        //3. 将右边的链表反转，
        // 正常的链表反转，会定义一个null的pre节点，
        // 但是这里不行，因为如果原先的head链表是奇数长度，左右两个链表都要共用mid节点
        // 所以返回rightHead代码如下：
        Node next = null;
        while (rightHead != null) {
            next = rightHead.next;
            rightHead.next = mid;  //mid正常应用是pre
            mid = rightHead;
            rightHead = next;
        }

        // 反转右链表之后(偶数长度时)，mid :  50 -> 74 -> 11 -> 11 -> null, 多个11是因为原先 mid == 11

        //4. 比较左右两个链表是否相等
        boolean equal = true;
        Node left = head;
        Node right = mid;
        // 为什么left ,right都要判空？ 因为在原链表是偶数的情况下，right会持有上下中间，而left只持有上中点
        while (left != null && right != null) {
            if (left.value != right.value) {
                equal = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        //5. 还原原链表
        // 还原链表时，很狗呀，因为断开链表后，mid表示右边的链表(已经反转过来了)
        // 此时只需要将mid再反转即可，因为左边的链表还挂着中点节点
        Node pre = null;
        next = null;
        // 为什么只反转mid链表(右链表)，然后整个链表就还原了呢？
        // 因为，左边的链表持有mid节点对象，而反转右链表之后，mid节点在右链表第1个元素，
        // 左右链表在内存中是同一个对象，所以原链表就有序了！！
        // 记住链表中的元素是对象，而不是简单类型
        while (mid != null) {
            next = mid.next;
            mid.next = pre;
            pre = mid;
            mid = next;
        }

        return equal;
    }



    // 生成一个回文链表，一个数组也行，第一次正向组织，第二次反向组织
    public static Node genLinkedList(int len, int maxValue) {
        int randomLength = (int) (Math.random() * len) + 1;
        //int randomLength = 3;
        int[] arr1 = new int[randomLength];
        int[] arr2 = new int[randomLength];
        int index1 = 0;
        int index2 = arr2.length - 1;
        for (int i = 0; i < randomLength; i++) {
            int value = (int) (Math.random() * maxValue) + 1;
            arr1[index1++] = value;
            arr2[index2--] = value;
        }

        //System.out.println(Arrays.toString(arr1));
        //System.out.println(Arrays.toString(arr2));
        Node head = null, tail = null;
        for (int i = 0; i < arr1.length; i++) {
            Node cur = new Node(arr1[i]);

            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
        }
        for (int i = Math.random() < 0.5 ? 0 : 1; i < arr2.length; i++) {
            Node cur = new Node(arr2[i]);

            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10_0000; i++) {

            Node head = genLinkedList(100, 100);
            boolean palindrome = isPalindrome2(head);
            if (!palindrome) {
                System.out.println("Oops!");
                break;
            }

        }
        System.out.println("ok");


        //Node node1 = new Node(1);
        //Node node2 = new Node(2);
        //Node node3 = new Node(3);
        //Node node33 = new Node(3);
        //Node node4 = new Node(2);
        //Node node5 = new Node(1);
        //node1.next = node2;
        //node2.next = node3;
        //node3.next = node33;
        //node33.next = node4;
        //node4.next = node5;
        ////System.out.println(isPalindrome3(node1));
        //System.out.println(isPalindrome2(node1));
    }
}
