package qinfeng.zheng.date_20210914;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/29 21:24
 * @dec 本题为leetcode原题
 * 测试链接：https://leetcode.com/problems/friend-circles/
 * 可以直接通过
 */
public class A_03_朋友圈个数 {

    public static void main(String[] args) {

    }

    // 如果M[i][j] == 1 , 那么i和j一定是认识的
    // i和i自己是认识的
    // 如果i认识j，那么j一定也认识i
    // 所以我们在求朋友圈时，只需要求矩形的上半部分即可
    public static int findFriendCycle(int[][] M) {
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) { // i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.getSets();

    }

    public static class UnionFind {
        public int[] parent;
        public int[] size;
        public int[] help;
        public int sets;

        public UnionFind(int N) {
            this.parent = new int[N];
            this.size = new int[N];
            this.help = new int[N];
            this.sets = N;

            for (int i = 0; i < N; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        public int find(int v) {
            int index = 0;
            while (v != parent[v]) {
                help[index++] = v;
                v = parent[v];
            }

            // 路径压缩
            for (index--; index >= 0; index--) {
                parent[help[index]] = v;
            }
            return v;
        }


        public void union(int i, int j) {
            int f1 = find(i);

            int f2 = find(j);


            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }

        }

        public int getSets() {
            return sets;
        }
    }


}
