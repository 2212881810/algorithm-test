package qinfeng.zheng.date_20210829;


/**
 * @Author ZhengQinfeng
 * @Date 2022/2/27 22:47
 * @dec
 */
public class A_10_合并两个有序链表 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(7);
        n4.next = n5;
        n5.next = n6;

        ListNode listNode = mergerSortListNode(n1, n4);

        while (listNode != null) {
            System.out.println(listNode.value);
            listNode = listNode.next;
        }


    }


    /**
     * l1 : 1-> 3 -6; l2: 1 -> 2 -> 7
     * 输出： 1 -> 1 -> 2 -> 3 -> 6 -> 7
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergerSortListNode(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }

            // 节点往链表尾巴上插的套路
            prev = prev.next;
        }

        if (l1 != null) {
            prev.next = l1;
        }

        if (l2 != null) {

            prev.next = l2;
        }

        return dummy.next;
    }


    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int val) {
            value = val;
        }
    }
}
