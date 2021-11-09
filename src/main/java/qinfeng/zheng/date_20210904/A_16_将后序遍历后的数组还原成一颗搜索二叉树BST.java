package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/16 23:01
 * @dec
 */
public class A_16_将后序遍历后的数组还原成一颗搜索二叉树BST {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node process(int[] arr, int left, int right) {
        // 因为是后序遍历得到的数组，所以数组的最后一个元素一定是树的根
        if (left > right) {
            return null;
        }

        if (left == right) {
            return new Node(arr[left]);
        }

        Node root = new Node(arr[right]);
        // 因为搜索二叉树，所以左子树的节点值都会小于根节点的值 ，右子树 > 根节点的值
        int mid = right - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) { // 小于根节点arr[right]的值都是左子树上的节点
                mid = i;
            }
        }
        root.left = process(arr, left, mid);
        root.right = process(arr, mid + 1, right - 1); // right是根节点

        return root;
    }

    // 后序遍历二叉树
    public static void post(Node root) {
        if (root == null) {
            return;
        }
        post(root.left);
        post(root.right);
        System.out.print(root.value+" ");
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 6, 9, 8, 5};
        Node root = process(arr, 0, arr.length - 1);

        post(root);
    }
}
