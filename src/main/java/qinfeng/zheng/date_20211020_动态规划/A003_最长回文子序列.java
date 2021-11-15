package qinfeng.zheng.date_20211020_动态规划;

/**
 * @Author ZhengQinfeng
 * @Date 2021/11/15 21:24
 * @dec 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * <p>
 * <p>
 * 子序列可以不连续；子串必须连续
 *
 * leetcode测试地址： https://leetcode.com/problems/longest-palindromic-subsequence/submissions/
 */
public class A003_最长回文子序列 {

    //这个题可以转换为最长公共子序列来解！
    //原字符串， 将原字符串反转后的字符串  ---》 求这两个字符串的最长公共子序列，即为原字符串的最长回文子序列
    //使用暴力递归的方式来实现的
    //样本对应模型
    public int lps(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }

        char[] chars = text.toCharArray();
        char[] reverseChars = new StringBuffer(text).reverse().toString().toCharArray();

        return process(chars, reverseChars, chars.length - 1, reverseChars.length - 1);
    }

    /**
     * 求str1[0....i] 和str2[0...j]之间最长公共子序列
     *
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    public int process(char[] str1, char[] str2, int i, int j) {

        if (i == 0 && j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return 0;
            }
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        } else {
            // i != 0  && j != 0
            //  可能是取i, 不可能取j, 所以最长公共子序列就等于在（i,j-1）上面的取值
            int p1 = process(str1, str2, i, j - 1);
            int p2 = process(str1, str2, i - 1, j);
            int p3 = str1[i] == str2[j] ? (1 + process(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    // 将上面的暴力递归改成动态规则
    public int longestPalindromeSubseq(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }

        char[] char1 = text.toCharArray();
        char[] char2 = new StringBuffer(text).reverse().toString().toCharArray();

        int N = char1.length;
        int[][] dp = new int[N][N];

        dp[0][0] = char1[0] == char2[0] ? 1 : 0;

        // 填充第1行的元素,列变
        for (int j = 1; j < N; j++) {
            dp[0][j] = char1[0] == char2[j] ? 1 : dp[0][j - 1];
        }


        // 填充第1列的元素,行变
        for (int i = 1; i < N; i++) {
            dp[i][0] = char1[i] == char2[0] ? 1 : dp[i - 1][0];
        }

        // 填充其它的位置的元素
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = char1[i] == char2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[N - 1][N - 1];
    }


    /**
     * 第二种套路： 范围尝试模型， 一般都是尝试边界值
     * 暴力递归解阖
     * @param text
     * @return
     */
    public int longestPalindromeSubseq2(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        char[] str = text.toCharArray();

        return f(str, 0, str.length - 1);
    }

    /**
     * 求str从L 到 R范围上最长回文子序列
     *
     * @param str
     * @param L
     * @param R
     * @return
     */
    public int f(char[] str, int L, int R) {

        // base case
        if (L == R) {
            return 1; // 只有一个元素，肯定是一个回文序列
        }

        // base case
        if (L == R - 1) {
           // 两个元素时， 如果两个元素相等，则回文序列是2，如果两个元素不等，回文序列是1（看成一个整体！）
            return str[L] == str[R] ? 2 : 1;
        }

        // 不止两个元素

        // 第1种情况，可能取L, 不可能取R
        int p1 = f(str, L, R - 1);

        // 第二种情况，不可能L,可能取R
        int p2 = f(str, L + 1, R);

        // 第三种情况，不可能取L, 也不可能是R
        int p3 = f(str, L + 1, R - 1);

        // 第四种情况，可能取L, 也可能取R
        int p4 = str[L] == str[R] ? (2 + f(str, L + 1, R - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }
}
