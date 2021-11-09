package qinfeng.zheng.date_20210926_暴力递归;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/27 22:14
 * @dec
 */
public class A_02_汉诺塔问题 {

    /**
     * 解决思路： 假设有N圆盘和3个柱子，且目标是将圆盘从A移到C,分3步走：
     * 1、将1...N-1,从A移到B
     * 2、将N从A移到C
     * 3、将1...N-1从B移到C
     *
     * @param N     : 圆盘数据
     * @param from
     * @param to
     * @param other
     */
    public static void process(int N, String from, String to, String other) {
        if (N == 1) {// base case
            System.out.println("圆盘1从" + from + "移到" + to);
        } else {
            // 第1步
            process(N - 1, from, other, to);
            // 第2步
            System.out.println("圆盘" + N + "从" + from + "移到" + to);
            // 第3步
            process(N - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        int N = 3;
        String from = "A";
        String to = "C";
        String other = "B";
        process(N, from, to, other);

    }
}
