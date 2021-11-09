package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/8 21:56
 * @dec 前驱节点、 后继节点都是针对中序遍历而言的！！！！
 * 主要分两种情况：
 * 1.有右子树
 * 2.无右子树
 *
 *
 */
public class A_09_求后继节点 {
    public static class Node<V> {
        V value;
        Node left;
        Node right;
        Node parent;

        public Node(V  v) {
            value = v;

        }
    }

    public static Node successorNode(Node cur) {
        if (cur == null) {
            return null;
        }

        if (cur.right != null) {
            // 最左的节点
            return getMostLeft(cur.right);
        } else {  // 无右子树
            Node parent = cur.parent;

            //如果当前节点cur是其父节点的右子树，那就一直往上找，直到找到父节点是null 或者当前节点是其父节点的左子树为止
            while (parent != null && parent.right == cur) {
                cur = parent;
                parent = cur.parent;

            }

            return parent;

        }

    }

    private static Node getMostLeft(Node cur) {
        // 如果当前节点有左子树，就找到最左的那颗子树，然后返回
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public static void main(String[] args) {
        Node nod1 = new Node(1);
        Node nod2 = new Node(2);
        Node nod3 = new Node(3);
        Node nod4 = new Node(4);
        Node nod5 = new Node(5);
        Node nod6 = new Node(6);
        Node nod7 = new Node(7);
        Node nod8 = new Node(8);
        nod1.left = nod2;
        nod1.right = nod3;
        nod2.parent = nod1;
        nod3.parent = nod1;
        nod2.left = nod4;
        nod2.right = nod5;
        nod4.parent = nod2;
        nod5.parent = nod2;
        nod5.right = nod8;
        nod8.parent = nod5;
        nod3.left = nod6;
        nod3.right = nod7;
        nod6.parent = nod3;
        nod7.parent = nod3;

        //System.out.println(successorNode(nod8).value);  // nod8的后继节点
        //System.out.println(successorNode(nod1).value);  // nod1的后继节点
        System.out.println(successorNode(nod7));  // nod7的后继节点是null
    }
}
