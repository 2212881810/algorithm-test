package qinfeng.zheng.date_20210914;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/29 21:37
 * @dec 本题为leetcode原题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 * 所有方法都可以直接通过
 * <p>
 * 给定一个二维数组matrix，里面的值不是1就是0，
 * 上、下、左、右相邻的1认为是一片岛，
 * 返回matrix中岛的数量
 */
public class A_04_岛问题 {

    /**
     * 如果board[i][j] == '1', 将其改为'0', 并通过递归对其上下左右的点进行感染！！！
     *
     * @param board
     * @param i     : 横轴
     * @param j     ： 纵轴
     */
    public static void infect(char[][] board, int i, int j) {
        // base case
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != '1') {
            return;
        }
        // 已经感染过的点设置成'0',设置成非'1'的字符都是可以的
        board[i][j] = '0';
        //感染board[i][j] 上下左右的点
        infect(board, i - 1, j); // 上一行
        infect(board, i + 1, j); // 下一行
        infect(board, i, j - 1); // 左一列
        infect(board, i, j + 1); // 右一列
    }

    // 求小岛个数的第一种方式
    public static int islandNums(char[][] board) {
        int islandNums = 0;
        for (int i = 0; i < board.length; i++) {  // 行
            for (int j = 0; j < board[0].length; j++) {// 列
                if (board[i][j] == '1') {
                    islandNums++;
                    infect(board, i, j);
                }
            }
        }
        return islandNums;
    }


    public static class Node<V> {
        V v;

        public Node(V v) {
            this.v = v;
        }
    }


    // 再写一次hashMap实现的并查集
    public static class UnionFind<V> {
        // 保存V与其包装类的映射关系
        public Map<V, Node<V>> nodes;
        // Node<v> 的代表节点就是Node<V>,它的包装类
        public Map<Node<V>, Node<V>> parentMap;

        public Map<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>(values.size());
            parentMap = new HashMap<>(values.size());
            sizeMap = new HashMap<>(values.size());

            for (V value : values) {
                Node<V> curNode = new Node<>(value);
                this.nodes.put(value, curNode);
                this.parentMap.put(curNode, curNode);
                this.sizeMap.put(curNode, 1);
            }

        }

        public Node findFather(Node<V> v) {
            Stack<Node> stack = new Stack<>();
            while (v != parentMap.get(v)) {
                stack.push(v);
                v = parentMap.get(v);
            }

            // 路径压缩
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), v);
            }
            return v;
        }

        public void union(V a, V b) {
            Node<V> f1 = parentMap.get(nodes.get(a));
            Node<V> f2 = parentMap.get(nodes.get(b));

            if (f1 != f2) {  // 代表节点不同，需要合并

                Integer f1Size = sizeMap.get(f1);
                Integer f2Size = sizeMap.get(f2);

                if (f1Size > f2Size) { // 将f2的代表节点设置成f1
                    sizeMap.put(f1, f1Size + f2Size);
                    parentMap.put(f2, f1);
                    sizeMap.remove(f2);
                } else {
                    sizeMap.put(f2, f1Size + f2Size);
                    parentMap.put(f1, f2);
                    sizeMap.remove(f1);
                }
            }
        }

        public int getSize() {
            return this.sizeMap.size();
        }


    }
}

