package qinfeng.zheng.date_20211020_动态规划;

/**
 * @Author ZhengQinfeng
 * @Date 2021/10/20 23:04
 * @dec 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class A001_背包问题 {

    // 使用暴力递归的算法求解
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);  // 这里决定了dp返回 return dp[0][bag];
    }

    /**
     * 尝试: 从左往右的模型进行尝试
     *
     * @param w     ： 重量数组
     * @param v     ： 价值数组
     * @param index ： 当前处理的数组元素,取值范围从 0  到N ,共有 N+1种可能
     * @param rest  ： 背包中还可以装下的重量,取值范围看成是 0 到bag , 共有 bag + 1 种可能！
     * @return 价值
     * <p>
     *     
     *     
     *     
     *     
     * <p>
     * index的取值范围： 0 ~ N, 所以最多N+1个元素
     * rest的取值范围： 0 ~ bag, 所以最多bag+1个元素
     */
    public static int process(int[] w, int[] v, int index, int rest) {
        // base case ,  装不下，用-1进行标识
        if (rest < 0) {
            return -1;  //价值
        }

        //base case 越界了
        if (index == w.length) {
            return 0;  // 价值
        }
        // 1. 不要index物品
        int p1 = process(w, v, index + 1, rest);

        // 2. 要index物品，就需要判断index物品加到背包中是否超重了的问题
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            // 加上index物品的价值！！！
            p2 = next + v[index];
        }
        return Math.max(p1, p2);

    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        int N = w.length;

        // 为什么要N+1??
        // 因为在前面的递归中，index == w.length == N,即是从 0 到 N , 共有N+1个元素，所以下面定义数组的长度为N+1
        int[][] dp = new int[N + 1][bag + 1];  // dp表中默认值都是0
        // 因为int[N][x] == 0,即dp表最后一行全是0,
        // 为什么是0？ 由暴力递归中的  【if (index == w.length) {  return 0; }】 得到！！！！！
        for (int index = N - 1; index >= 0; index--) {  // 【行： 从下往上填 】
            for (int rest = 0; rest <= bag; rest++) {  // 【列：从左往右填】从上面的暴力递归可知，它只与行有关，所以从左往右填行，从右往左填也可以，无所谡
                // index == N-1时，index+1 == N,就是数组的最后一行元素
                int p1 = dp[index + 1][rest];

                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        //返回dp[0][bag] 由尝试的入参数的决定的
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 4, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
