package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/4 15:56
 * @dec
 */
public class A_01_遍历二叉树 {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v) {
            this.value = v;

        }


    }

    // 前序遍历 头--> 左--> 右
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 后序遍历 左--> 右---> 头
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value);
    }

    // 中序遍历  左---> 头 ----> 右
    public static void midOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        midOrder(root.left);
        System.out.print(root.value);
        midOrder(root.right);

    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode5.right = treeNode8;

//        preOrder(treeNode1);
//        postOrder(treeNode1);
        midOrder(treeNode1);
    }
}
