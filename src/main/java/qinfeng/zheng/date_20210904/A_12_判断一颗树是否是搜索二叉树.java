package qinfeng.zheng.date_20210904;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/11 12:00
 * @dec 搜索二叉树的性质： 左子树的最大值  < 头节点的值  < 右子树的最小值
 */
public class A_12_判断一颗树是否是搜索二叉树 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int v) {
            this.value = v;
        }
    }


    // 通过中序遍历 + 数组来判断
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        List<Node> arr = new ArrayList<>();
        in(head, arr);

        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }

        return true;
    }

    // 中序遍历
    public static void in(Node head, List<Node> arr) {
        if (head == null) {
            return;
        }
        Node left = head.left;
        Node right = head.right;
        in(left, arr);
        arr.add(head);
        in(right, arr);

    }


    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    // 使用二叉树的递归套路求解
    public static boolean isBST2(Node head) {

        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(Node x) {

        if (x == null) {  // base case
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 1. 求x整颗树的最大值,要么是X节点的值,要么是左子树最大值,要么是右子树最大值
        int max = x.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }

        // 2. 求x整颗树的最小值
        int min = x.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }


        // 3. 判断x整颗树是否是BST
        boolean isBST = true;
        // 3.1 左子树的最大值大于x的值
        if (leftInfo != null && leftInfo.max >= x.value) {
            isBST = false;
        }
        // 3.2 右子树的最小值小于x的值
        if (rightInfo != null && rightInfo.min <= x.value) {
            isBST = false;
        }
        // 3.3 左子树不是BST
        if (leftInfo != null && !leftInfo.isBST){
            isBST = false;
        }

        // 3.4 右子树不是BST
        if (rightInfo != null && !rightInfo.isBST){
            isBST = false;
        }
        return new Info(isBST, max, min);
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
