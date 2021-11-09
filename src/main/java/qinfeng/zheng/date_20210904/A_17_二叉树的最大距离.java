package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/17 22:45
 * @dec 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
 * 返回整棵二叉树的最大距离
 */
public class A_17_二叉树的最大距离 {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int maxDistance;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int d1 = leftInfo.maxDistance;
        int d2 = rightInfo.maxDistance;
        // 因为算大距离d3这种情况时用到高度，所以Info对象里需要height
        int d3 = leftInfo.height + rightInfo.height + 1;
        maxDistance = Math.max(d1, Math.max(d2, d3));
        return new Info(maxDistance, height);
    }

    // 给定一棵二叉树的头节点head , 返回整颗二叉树的最大距离
    public static int getMaxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxDistance;
    }
}
