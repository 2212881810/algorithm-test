package qinfeng.zheng.date_20210904;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/17 23:47
 * @dec 解法与A_15一样
 */
public class A_18_二叉树中最大的二叉搜索子树的头节点 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static class Info {
        public Node maxSubBSTHead;
        public int maxSubBSTSize;
        public int max;
        public int min;

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int max, int min) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        int max = x.value;
        int min = x.value;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxSubBSTSize = leftInfo.maxSubBSTSize;
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            // 注意L126是大于等于,要与此处的大于判断相匹配，否则就鸡鸡了
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) { // 右树的BST节点数大于左树，那么结果只能在右树中产生
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }

        // 判断x整颗树是不是BST,如果x不是BST,那么结果只能是在左、右两颗子树其一中产生
        if ((leftInfo == null ? true : leftInfo.maxSubBSTHead == x.left && leftInfo.max < x.value) && (rightInfo == null ? true : rightInfo.maxSubBSTHead == x.right && rightInfo.min > x.value)) {
            maxSubBSTHead = x;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTHead, maxSubBSTSize, max, min);
    }

    // 使用二叉树递归套路实现的解法
    public static Node getMaxBSTSubtreeHead(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    // 二叉树的中序遍历
    public static void mid(Node head, List<Node> arr) {
        if (head == null) {
            return;
        }

        mid(head.left, arr);
        arr.add(head);
        mid(head.right, arr);
    }

    public static int getMaxBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> arr = new ArrayList<>();
        mid(head, arr);  // 将二叉树进行中序遍历之后，就可以判断它是不是BST树了
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static Node getMaxBSTSubtreeHead2(Node head) {

        if (head == null) {
            return null;
        }
        int maxBSTSize = getMaxBSTSize(head);
        if (maxBSTSize != 0) { // 整颗head树就是BST树
            return head;
        }

        // 代码走到这里说明head不是BST树，那么只能从它的左子树和右子树中去寻找了
        // 在head的左子树中找到BST的头节点
        Node leftHead = getMaxBSTSubtreeHead2(head.left);
        // 在head的右子树中找到BST的头节点
        Node rightHead = getMaxBSTSubtreeHead2(head.right);

        //在比较找到的头节点的BST树的大小，大的即是结果！！！
        return getMaxBSTSize(leftHead) >= getMaxBSTSize(rightHead) ? leftHead: rightHead;
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
            if (getMaxBSTSubtreeHead2(head) != getMaxBSTSubtreeHead(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}

