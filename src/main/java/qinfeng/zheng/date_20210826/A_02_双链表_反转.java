package qinfeng.zheng.date_20210826;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/26 22:42
 * @dec 无论是单链表的反转还是双链表的反转, coding的套路都是一样, 记住套路写起来就很简单！
 * <p>
 * 下面总结一下coding的套路：
 * 1、声明两个变量： prev(当前节点的上一个节点) 和 next(当前节点的下一个节点)
 * 2、while循环中,通过next节点使用循环一直往下进行下去
 * 3、当前节点的next指针指向它的prev节点；当前节点的prev指针指向它的next节点(双链表才有prev指针)
 */
public class A_02_双链表_反转 {

    public static class DoubleNode {
        Integer data;
        DoubleNode prev;
        DoubleNode next;
    }

    public static DoubleNode reverseDoubleNode(DoubleNode head) {

        // 与单链表一样,先声明两个变量！
        DoubleNode prev = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next; // 与单链表一样

            head.next = prev;  // 与单链表一样,可以看成是当前节点的后一个节点指向了当前节点的前一个节点
            head.prev = next;  // 单链表没有这一行, 可以看成是当前节点的前一个节点指向了当前节点的后一个节点

            prev = head;  // 与单链表一样

            head = next;  // 与单链表一样,通过next节点使用循环往下进行！！！
        }


        return prev;

    }

}
