package qinfeng.zheng.date_20210904;

import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/4 16:13
 * @dec 总结：
 * 前序：一个stack就行
 * 后序： 两个stack就行
 * 中序： 一个stack就行，但是理解起来要难一点
 */
public class A_02_遍历二叉树_非递归 {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int v) {
            this.value = v;
        }
    }


    // 先序
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                System.out.print(cur.value);
                // 因前序是头左右,所以要先把右压入栈，再把左压入栈，这样左会先出栈，先打印
                stack.push(cur.right);
                stack.push(cur.left);
            }
        }
    }

    // 后序
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            if (cur != null) {
                stack2.push(cur);
                    stack.push(cur.left);
                stack.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value);
        }
    }

    public static void midOrder(TreeNode root) {

        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {  // 先将左子树的节点全部压入栈中
                stack.push(root);
                root = root.left;  // 最后一个左节点的left节点肯定为null,然后进入else分支
            } else {
                // 最先弹出的肯定是左节点
                root = stack.pop();
                // 弹出来打印
                System.out.print(root.value);
                // 如果有right节点走上面的if分支压入栈，如果没root == null,继续走else分支，弹栈
                root = root.right;
            }
        }

    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        preOrder(treeNode1);
        System.out.println();
        postOrder(treeNode1);
        System.out.println();
        midOrder(treeNode1);

        System.out.println();
        post(treeNode1);
    }


    // 中序遍历，写熟了理解了其实也很简单呀
    public static void post(TreeNode cur) {

        if (cur == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value);
                cur = cur.right;
            }
        }
    }
}
