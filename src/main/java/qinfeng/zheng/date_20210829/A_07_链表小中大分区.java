package qinfeng.zheng.date_20210829;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/5 20:15
 * @dec 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1）把链表放入数组里，在数组上做partition（笔试用）
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 */
public class A_07_链表小中大分区 {

    public static class Node {
        int value;
        Node next;

        public Node(int v) {
            this.value = v;
        }
    }

    // 使用这种方式，空间复杂度O(N)
    public static Node partitionLinkedList(Node head, int pivot) {

        if (head == null || head.next == null) {
            return head;
        }

        // 1. 计算出链表的有多少个节点
        int count = 0;
        Node cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        // 2. 将链表的节点保存到数组中
        Node[] arr = new Node[count];
        int index = 0;
        cur = head;
        while (cur != null) {
            Node next = cur.next;
            // 干掉next指针之后，后面就可以不要arr[arr.length - 1].next = null这行代码
            cur.next = null;
            arr[index++] = cur;
            cur = next;
        }
        // 3. 使用荷兰国旗算法，对数组中的节点分区，小于部分，等于部分，大于部分
        // 将数组arr中的值划分为小中大3部分， 按原链表最后一个值进行分组
        //partition(arr, 0, count - 1);
        partition(arr, pivot);


        // 4. 重新整理链表
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }

        // 最一个元素的next指向null
        // 这一句代码非常重要！！！！
        // 因为原先arr[arr.length-1]这个节点next可能挂着其它节点，如果next不置空，那么这个链表可能循环引用导致无限个节点组成了
        // 但是如果在将节点加入到数组中时，干掉next指针，那么就可以省略下面这行代码
        //arr[arr.length - 1].next = null;
        return arr[0];
    }

    private static void partition(Node[] arr, int pivot) {
        int less = -1;
        int more = arr.length;
        int cur = 0;
        while (cur < more) {
            if (arr[cur].value == pivot) {
                cur++;
            } else if (arr[cur].value < pivot) {
                swap(arr, ++less, cur++); //
            } else {
                swap(arr, --more, cur);
            }
        }


    }

    //private static void partition(Node[] arr , int left , int right) {
    //    int less = left - 1;
    //    int more = right;
    //    int cur = left;
    //    while (cur < more) {
    //        if (arr[cur].value == arr[right].value) {
    //            cur++;
    //        } else if (arr[cur].value < arr[right].value) {
    //            swap(arr, ++less, cur++);  // 小于和等于的无素换下位置
    //        } else if (arr[cur].value > arr[right].value){
    //            swap(arr, --more, cur);// 调用当前位置元素与大于位置元素的位置
    //        }
    //    }
    //
    //    swap(arr, more, right);  // right一直是pivot元素，需要换下位置
    //}

    private static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    // 使用这种方式，空间复杂度O(1)
    public static Node partitionLinkedList2(Node head, int pivot) {
        Node sH = null; // 小于部分的头节点
        Node sT = null; // 小于部分的尾节点
        Node eH = null; // 等于部分的头节点
        Node eT = null; // 等于部分的尾节点
        Node mH = null; // 大于部分的头节点
        Node mT = null; // 大于部分的尾节点
        Node next = null;
        while (head != null) {
            next = head.next;

            head.next = null; // 干掉head节点的后续链节

            if (head.value < pivot) { // 肯定是小于部分
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        // 原则上小于区的尾连等于区的头，等于区尾再连大于区头，但是考虑到有些区可能为null,所以需要判断

        // 下面这段代码也需要好好体会，尤其是   eT = eT != null ? eT : sT; 这行代码造成第二个if判断的

        if (sT != null) { // 有小于区
            sT.next = eH;  // 小于区的尾 -> 等于区的头  （但是有等于区吗？）
            eT = eT != null ? eT : sT;  // 如果有等于区，那eT就是eT，否则就sT
        }

        if (eT != null) {// et不等于null 有两种情况，一是有等于区时，eT就还是那个eT,如果没有等于区，eT = sT  (155行代码)
            // 所以，可能是等于区尾挂上了大于区的头，也有可能是小于区的尾挂上了大于区的头
            eT.next = mH;
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }


    public static void printLinkedList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ->");
            cur = cur.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(6);
        Node node2 = new Node(3);
        Node node3 = new Node(5);
        Node node4 = new Node(2);
        Node node5 = new Node(-1);
        Node node6 = new Node(4);
        Node node7 = new Node(3);
        Node node8 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        printLinkedList(node1);
        //Node node = partitionLinkedList(node1,5);
        Node node = partitionLinkedList2(node1, 0);

        printLinkedList(node);

    }


}
