package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/23 22:03
 * @dec
 */
public class A_19_找ab两个节点的最低公共祖先 {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node answer;

        public Info(boolean findA, boolean findB, Node answer) {
            this.findA = findA;
            this.findB = findB;
            this.answer = answer;
        }
    }


    public static Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }

        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        // 如果x本身就是a节点，或者x的左树找到了a节点，或者x的右树找到了a节点，那么就是找到了a节点，findA ==  true
        boolean findA = x == a || leftInfo.findA || rightInfo.findA;
        boolean findB = x == b || leftInfo.findB || rightInfo.findB;

        // 最低公共祖先
        Node ans = null;
        if (leftInfo.answer != null) { // 如果x的左树上找到最低公共祖先,那就是了
            ans = leftInfo.answer;
        } else if (rightInfo.answer != null) {
            ans = rightInfo.answer;
        } else {
            if (findA && findB) {  //x是最低公共祖先
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

    // 使用二叉树的递归套路求a,b两个节点的最低公共祖先
    public static Node getLowerAncestor(Node head,Node a, Node b) {
        if (head == null) {
            return null;
        }
        return process(head, a, b).answer;
    }

}
