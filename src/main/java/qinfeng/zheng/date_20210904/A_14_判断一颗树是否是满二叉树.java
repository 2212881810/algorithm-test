package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/14 21:18
 * @dec 满二叉树的判断依据： 如果二叉树的高度是h, 那么节点是2^h -1
 */
public class A_14_判断一颗树是否是满二叉树 {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isFull(Node head) {

        if (head == null) {
            return true;
        }

        int height = h(head);
        int nodes = nodes(head);
        return 1 << height == nodes + 1;
    }

    /**
     * 求x树的高度
     *
     * @param x
     * @return
     */
    public static int h(Node x) {
        if (x == null) {
            return 0;
        }
        return Math.max(h(x.left), h(x.right)) + 1;
    }

    /**
     * 求x树的节点总数
     *
     * @param x
     * @return
     */
    public static int nodes(Node x) {
        if (x == null) {
            return 0;
        }
        return nodes(x.left) + nodes(x.right) + 1;
    }


    public static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }


    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }


        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }

        Info info = process(head);
        return 1 << info.height == info.nodes+1;
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
            if (isFull(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

