package qinfeng.zheng.date_20210904;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/11 10:50
 * @dec 给出头节点，判断一颗树是否是完全二叉树
 * 完全二叉树的性质： 若这颗树有k层，那么1~k-1层都是满的，第k层从左到右看也是逐渐满的
 *
 * <p>
 * 首先得明白什么是完全二叉树，在求解的过程中少不了用到二叉树的遍历知识
 */
public class A_11_判断一颗树是否是完全二叉树 {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 代码实现其实就是二叉树的按层遍历
    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        boolean isLeaf = false;

        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            Node left = head.left;
            Node right = head.right;

            //无左子树，有右子树，不符合完全二叉树的定义
            if (left == null && right != null) {
                return false;
            }

            // 先遇一次子树不全的情况，然后再次遇到了非叶子节点，肯定也不是完全二叉树
            if (isLeaf && (left != null || right != null)) {
                return false;
            }

            if (left != null) {
                queue.add(left);
            }

            if (right != null) {
                queue.add(right);
            }

            // 遇到子树不全时，标识isLeaf修改
            if (left == null || right == null) {
                isLeaf = true;
            }

        }

        return true;
    }


    public static class Info {
        // 满二叉树
        public boolean isFull;
        // 完全二叉树
        public boolean isCBT;
        // 树的高度
        public int height;

        public Info(boolean f, boolean cbt, int h) {
            this.isFull = f;
            this.isCBT = cbt;
            this.height = h;
        }
    }


    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }

        return process(head).isCBT;
    }

    //求任意一颗树X是否是完全二叉树
    public static Info process(Node x) {

        if (x == null) {
            return new Info(true, true, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 根据x的左右子树，计数出X整颗树的Info信息

        // 满二叉树的条件判断
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isCBT = false;
        if (isFull) { // 如果X是满二叉树，那么它一定是完全二叉树
            isCBT = true;
        } else {  // 如果X不是满二叉树，那么再计数出它是完全二叉树的情况

            // 1. 左是完全，右子满的情况
            if (leftInfo.isCBT && rightInfo.isFull && (leftInfo.height == (rightInfo.height + 1))) {
                isCBT = true;
            }

            // 2. 左是满，右是满
            if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            }

            // 3. 左是满，右是完全
            if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                isCBT = true;
            }
        }
        return new Info(isFull, isCBT, height);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
