package qinfeng.zheng.date_20210904;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/7 21:26
 * @dec 这个题需要好好体会这种递归的用法！！！
 */
public class A_07_将多叉树转换成二叉树 {
    // 多叉树节点
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 二叉树节点
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 将多叉树转换成二叉树
     *
     * @param root ： 多叉树的根节点
     * @return ： 二叉树的根节点
     */
    public static TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }

        //转换之后，二叉树的根节点
        TreeNode head = new TreeNode(root.val);
        head.left = en(root.children);
        return head;
    }

    // 深度优先遍历
    private static TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;


        if (children == null) {
            return null;
        }
        //我觉得代码是真的写得好！！！
        //children ==null 这样遍历会报NPE
        for (Node child : children) {
            TreeNode treeNode = new TreeNode(child.val);
            if (head == null) {
                //第一次遍历children肯定会构造这head
                head = treeNode;
            } else {
                // 将其它的child节点都挂在前一个child节点的right节点上
                cur.right = treeNode;
            }
            cur = treeNode;
            // 所有的子节点都成为当前父节点的左子节点，但是具体的实现由上面的if...else控制
            // 只有第1个子节点成为原父节点的left节点，其它子节点都成了left的right节点
            cur.left = en(child.children);

        }
        return head;
    }


    //  将二叉树转换成多叉树
    public static Node decode(TreeNode root) {

        if (root == null) {
            return null;
        }

        Node head = new Node(root.val, de(root.left));
        return head;
    }


    private static List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();

        while (root != null) {
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }

        return children;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        List<Node> children1 = new ArrayList<>();
        children1.add(node2);
        children1.add(node3);
        children1.add(node4);

        List<Node> children2 = new ArrayList<>();
        children2.add(node5);
        children2.add(node6);
        node1.children = children1;
        node2.children = children2;

        TreeNode treeNode = encode(node1);
        System.out.println(treeNode);


    }

}
