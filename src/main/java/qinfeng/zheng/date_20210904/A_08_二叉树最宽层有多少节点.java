package qinfeng.zheng.date_20210904;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/7 22:12
 * @dec
 */
public class A_08_二叉树最宽层有多少节点 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    // 使用HashMap来存储当前节点所在的层数
    public static int maxWideWithMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int max = Integer.MIN_VALUE;
        int curLevel = 1;
        // 当前层有多少个节点
        int curLevelNodes = 0;

        Map<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);

            if (cur.left != null) {
                queue.add(cur.left);
                levelMap.put(cur.left, curNodeLevel + 1);  // 当前节点的下一层
            }

            if (cur.right != null) {
                queue.add(cur.right);
                levelMap.put(cur.right, curNodeLevel + 1);  // 当前节点的下一层
            }

            //累加计数
            if (curNodeLevel == curLevel) {
                curLevelNodes++;  // 当前层节点进行累加
            } else {//说明已经进入到下一层节点的遍历
                max = Math.max(max, curLevelNodes);
                curLevel++;  // 全局变量标记下一层的数字
                curLevelNodes = 1;  // 计数重新开始
            }
        }
        // 最后一次遍历不会进入上面的else分支，所以要在最外层取max
        return Math.max(max, curLevelNodes);

    }


    // 不使用容器的方式实现： 使用两个变量,来记录当前行的end节点和下一层的end的节点
    public static int maxWideNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node endNode = head;
        Node nextEndNode = null;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            curLevelNodes++;
            if (cur.left != null) {
                queue.add(cur.left);
                nextEndNode = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEndNode = cur.right;
            }
            if (cur == endNode) {  // 遍历到这一层的最后一个节点
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                endNode = nextEndNode;
            }
        }

        return max;
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

        System.out.println(maxWideWithMap(node1));
        System.out.println(maxWideNoMap(node1));
    }
}
