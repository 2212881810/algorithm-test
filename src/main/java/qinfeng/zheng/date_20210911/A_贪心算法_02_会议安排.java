package qinfeng.zheng.date_20210911;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/11 23:34
 * @dec 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 */
public class A_贪心算法_02_会议安排 {

    public static class Meeting {
        //会议的开始时间
        private int start;
        //会议的结束时间
        private int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 贪心算法！！！！
    public static int bestArrange(Meeting[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                // 按会议的结束时间进行排序
                return o1.end - o2.end;
            }
        });

        int ans = 0;
        int endTime = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start >= endTime) {
                ans++;
                endTime = arr[i].end;
            }

        }

        return ans;
    }

    // 暴力递归
    // 实现思路：先选定一场会议，然后枚举其它所有的会议，求出最多的次数，依次这么递归下去，，，，
    public static int bestArrange2(Meeting[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, 0, 0);
    }

    /**
     * @param arr      会议集合
     * @param done     ： 已经可以安排的最大次数
     * @param deadLine ： 结束时间
     * @return
     */
    public static int process(Meeting[] arr, int done, int deadLine) {
        if (arr == null || arr.length == 0) {
            return done;//
        }
        int max = done;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start >= deadLine) { // 可以安排
                // 从arr数组中移除i号元素，然后调用process求最大安排场数
                Meeting[] next = removeCurrentEle(arr, i);
                max = Math.max(max, process(next, done + 1, arr[i].end));
            }

        }

        return max;
    }

    private static Meeting[] removeCurrentEle(Meeting[] arr, int i) {
        Meeting[] ans = new Meeting[arr.length - 1];
        int index = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != i) {
                ans[index++] = arr[j];
            }

        }

        return ans;
    }

    // for test
    public static Meeting[] generatePrograms(int programSize, int timeMax) {
        Meeting[] ans = new Meeting[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting(r1, r1 + 1);
            } else {
                ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Meeting[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
