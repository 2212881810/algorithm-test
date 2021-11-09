package qinfeng.zheng.date_20210904;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/15 23:43
 * @dec 描述一下： 在一颗二叉树中，可以某些颗子树是搜索二叉树BST, 求节点数最多的那个BST子树有多少个节点
 */
public class A_15_求二叉树中最大的搜索二叉子树 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    // 递归收集的条件
    public static class Info {
        // 最大的搜索二叉子树的节点个数
        public int maxBSTSubtreeSize;
        // 整个数的节点个数， 如果 allSize == maxBSTSubtreeSize,说明这颗树就是BST
        public int allSize;
        // 最大值
        public int max;
        // 树中的最小值
        public int min;

        public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
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
        // x树的最小值
        int min = x.value;
        // x树的最大值
        int max = x.value;
        int maxBSTSubtreeSize = 0;

        int allSize = 1; // x节点本身占一个size
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            allSize += leftInfo.allSize;
            maxBSTSubtreeSize = Math.max(maxBSTSubtreeSize, leftInfo.maxBSTSubtreeSize);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allSize += rightInfo.allSize;
            maxBSTSubtreeSize = Math.max(maxBSTSubtreeSize, rightInfo.maxBSTSubtreeSize);
        }

        //如果左子树是BST,右子树也是BST,那么再看看整颗树X是否是搜索二叉树BST
        // 如果左子树的最大搜索二叉子树的节点数等于整颗树的节点数，那么左子树肯定是搜索二叉树
        boolean leftIsBST = leftInfo == null ? true : leftInfo.maxBSTSubtreeSize == leftInfo.allSize;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.maxBSTSubtreeSize == rightInfo.allSize;

        // 如果x的左右两颗子树都是搜索二叉树了，那么再判断x树是否是搜索二叉树
        if (leftIsBST && rightIsBST) {
            // 左树最大值小于x节点的值
            boolean leftLessThanX = leftInfo == null ? true : leftInfo.max < x.value;
            boolean rightMoreThanX = rightInfo == null ? true : rightInfo.min > x.value;

            if (leftLessThanX && rightMoreThanX) {  // 说明x是搜索二叉树

                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                // 其实肯定是leftSize + rightSize + 1大
                //maxBSTSubtreeSize = Math.max(maxBSTSubtreeSize, leftSize + rightSize + 1);
                maxBSTSubtreeSize =  leftSize + rightSize + 1;
            }

        }


        return new Info(maxBSTSubtreeSize, allSize, max, min);
    }


    // 使用二叉树的递归套路实现的
    public static int getBSTSubtreeSize1(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head).maxBSTSubtreeSize;
    }


    // 中序遍历，BST的中序遍历就是递增的值
    public static void in(Node head, List<Node>  arr) {
        if (head == null) {
            return;
        }

        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 获取搜索二叉树的size
    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            // 说是不是BST
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }

        }
        return arr.size();
    }

    //递归总是有点找不到感觉呀！！！！
    public static int getBSTSubtreeSize2(Node head) {
        if (head == null) {
            return 0;
        }
        int size = getBSTSize(head);

        if (size != 0) {  // 说明是BST
            return size;
        }
        return Math.max(getBSTSubtreeSize2(head.left), getBSTSubtreeSize2(head.right));

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
            if (getBSTSubtreeSize1(head) != getBSTSubtreeSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
