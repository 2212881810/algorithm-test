package qinfeng.zheng.date_20211020_动态规划;

/**
 * @Author ZhengQinfeng
 * @Date 2021/11/14 22:49
 * @dec leetcode: https://leetcode.com/problems/longest-common-subsequence/
 * <p>
 * <p>
 * 套路：样本对应模型，一般比较最后一个元素
 */
public class A_002_最长公共子序列 {


    // 使用暴力递归的方法
    public int longestCommonSubsequence1(String text1, String text2) {

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        return process1(str1, str2, str1.length - 1, str2.length - 1);

    }

    /**
     * 样本对应模型，一般比较最后一个元素啦！！！！
     * 求str1[0....i] 和 str2[0....j]的最长公共子序列长度
     *
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    public int process1(char[] str1, char[] str2, int i, int j) {

        if (i == 0 && j == 0) {
            // 都只有一个字符了，直接进行比较，如果相等，返回1，否则返回0
            return str1[i] == str2[j] ? 1 : 0;

        } else if (i == 0) {
            // str1只有一个字符，str2不是一个字符,有j个字符
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                // str1[i] 与 str2[0...j-1]求最长公共子序列
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {

            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            //i != 0 && j != 0;
            // 第1种情况：最长公共子序列可能在str1的i上取，不可能在str2的j上取
            int p1 = process1(str1, str2, i, j - 1);

            // 第2种情况：最长公共子序列不可能在str1的i上取，可能在str2的j上取
            int p2 = process1(str1, str2, i - 1, j);

            // 第3种情况：最长公共子序列可能在str1的i上取，可能在str2的j上取
            // process1(str1, str2, i, j) 这样写会弄成死循环，所以将i和j的比较提出来
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }


    // 动态规划！！！！！！！！！！！！
    public int longestCommonSubsequence(String text1, String text2) {

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];

        // 1. 填充(0,0)位置元素
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;

        // 2. 填充第1行的元素
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }

        // 3. 填充第1列的元素
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }

        // 4.填充其它位置的元素
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {

                int p1 = dp[i][j - 1];
                int p2 = dp[i-1][j];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;

                int p = Math.max(p1, Math.max(p2, p3));
                dp[i][j] = p;
            }
        }

        return dp[N - 1][M - 1];

    }


}
