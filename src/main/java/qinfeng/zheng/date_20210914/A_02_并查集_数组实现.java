package qinfeng.zheng.date_20210914;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/28 23:06
 * @dec
 */
public class A_02_并查集_数组实现 {


    public static class UnionFind {
        // 如果parent[i] == k , 那么k就是i的代表节点
        public int[] parent;
        // 如果size[i] == k , 那么i为代表的集合中k个节点
        public int[] size;
        // 辅助数组，用于路径压缩
        public int[] help;
        // 整个有多少个集合
        public int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                // 初始化时，自己是自己的代表节点
                parent[i] = i;
                // 以i为代表节点的集合的有1个节点
                size[i] = 1;
            }
        }

        public int find(int v) {
            int index = 0;
            while (v != parent[v]) {
                help[index++] = v;
                v = parent[v];
            }

            //路径压缩
            for (index--; index >= 0; index--) {
                // 将help中收集起来的节点的代表节点设置成v
                parent[help[index]] = v;
            }
            return v; // 返回代表节点
        }

        public void union(int i, int j) {
            // 获取i,j的代表节点
            int fi = find(i);
            int fj = find(j);

            if (fi != fj) {  // 进行合并
                if (size[fi] > size[fj]) { //将fj为代表的集合合并到fi中
                    size[fi] = size[fi] + size[fj];
                    parent[fj] = fi;
                } else {
                    size[fj] += size[fi];
                    parent[fi] = fj;  // fj ==  parent[fj]
                }
                sets--;  // 合并了一个集合
            }
        }

        public int getSets() {
            return sets;
        }
    }
}

