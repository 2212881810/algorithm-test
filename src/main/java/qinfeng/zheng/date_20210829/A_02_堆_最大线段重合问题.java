package qinfeng.zheng.date_20210829;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/29 22:39
 * @dec 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class A_02_堆_最大线段重合问题 {


    /**
     * 暴力方式：
     *
     * @param lines
     */
    public static int maxCoverLines(int[][] lines) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        // 算出给出的这些线段中，最小start 和 最大 end值
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]); // start
            max = Math.max(max, lines[i][1]); // end
        }
        // 因为线段开始位置和结束位置都是整数值,所以我们可以0.5来表示线段中的一个位置，
        // 不能用整数，例如3这个数，线段[1,3]和[3,5]都含有3个这数，但是不是两个线段的重合区
        // 全局最大重合线段数
        int cover = 0;

        for (double p = min + 0.5; p < max; p += 1) {
            // 通过cur来标记 min+1.5, min+2.5, min+3.5这些点重合的线段数
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) { // 说明line[i]这个线段经过了p点
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    /**
     * 辅助类
     */
    private static class Line {
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     *  使用了小根堆来实现的
     * @param arr ： 用来表示一些线程信息的二维数组
     * @return  ： 最大重合线段数
     */
    public static int maxCoverLines2(int[][] arr) {
        Line[] lines = new Line[arr.length];

        // 将二维数组转换成Line数组
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }

        // 按start值进行升序排列
        Arrays.sort(lines, Comparator.comparingInt(l -> l.start));

        // 小根堆，存放每一条线段的end值
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 重合线程最大值
        int cover = 0;
        for (int i = 0; i < lines.length; i++) {

            // 如果一条线段的start位置 都大于等于 另一条线段(堆中)的end位置,那么这两条线段肯定没重合点
            while (!heap.isEmpty() && lines[i].start >= heap.peek()) {
                heap.poll();// 弹出
            }
            // 每一条线段的end都会放到堆中，而且由于是小根堆,所以heap.peek()拿到的一定是end最小
            heap.add(lines[i].end);
            cover = Math.max(cover, heap.size());
        }
        return cover;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 50;
        int L = 0;
        int R = 50;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            long l = System.currentTimeMillis();
            int ans1 = maxCoverLines(lines);
            long l2 = System.currentTimeMillis();
            System.out.println(l2-l); //166500
            int ans2 = maxCoverLines2(lines);
            long l3 = System.currentTimeMillis();
            System.out.println(l3-l2); //53
            System.out.println("------------------------------------");
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}
