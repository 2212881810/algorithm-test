package qinfeng.zheng.date_20210904;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/7 20:41
 * @dec
 */
public class A_06_二叉树的序列化_按层 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    // 按层序列化二叉树
    public static Queue<Integer> levelSerial(Node head) {
        Queue<Integer> ans = new LinkedList<>();

        if (head == null) {
            ans.add(null);
        } else {

            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            // 注意放入queue中的时机
            ans.add(head.value);

            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                //System.out.print(cur.value);
                if (cur.left != null) {
                    queue.add(cur.left);
                    ans.add(cur.left.value);
                } else {
                    ans.add(null);
                }

                if (cur.right != null) {
                    queue.add(cur.right);
                    ans.add(cur.right.value);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }


    public static Node buildByLevelSerial(Queue<Integer> linkedLists) {

        if (linkedLists == null || linkedLists.size() == 0) {
            return null;
        }

        Node head = genNode(linkedLists.poll());
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.left = genNode(linkedLists.poll());
            cur.right = genNode(linkedLists.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return head;
    }

    private static Node genNode(Integer val) {
        if (val == null) {
            return null;
        }

        return new Node(val);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        Queue<Integer> queue = levelSerial(node1);
        //Queue<String> queue = levelSerial2(node1);

        System.out.println(queue);
        Node node = buildByLevelSerial(queue);

        System.out.println(node);
    }

}
