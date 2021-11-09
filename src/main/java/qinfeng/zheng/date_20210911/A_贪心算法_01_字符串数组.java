package qinfeng.zheng.date_20210911;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/11 23:06
 * @dec 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 */
public class A_贪心算法_01_字符串数组 {
    // 假设有数组strs = {"abc","d","efd"},把数组中的所有字符串拼接起来，求拼接之后的字符串字典序最小

    // 使用贪心算法求解
    public static String lowestString(String[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // 两个字符串a,b，按a+b(拼接)字典序小于b+a(拼接)字典序进行排序
                return (a + b).compareTo(b + a);
            }
        });

        String ans = "";
        for (String s : arr) {
            ans += s;
        }
        return ans;
    }

    // 使用暴力方法求解
    public static String lowestString2(String[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeSet<String> treeSet = process(arr);
        return treeSet == null ? "" : treeSet.first();
    }

    // 返回arr数组中所以字符串的全排序，
    // TreeSet 默认是小根堆，按字典序升序，所以它的第一个元素就是排序后字典序最小的字符串
    public static TreeSet<String> process(String[] arr) {
        TreeSet<String> treeSet = new TreeSet<>();  // 它的底层使用treeMap来实现的
        if (arr == null || arr.length == 0) { // base case
            treeSet.add("");
            return treeSet;
        }

        // 遍历arr数组中的每个元素
        for (int i = 0; i < arr.length; i++) {

            String cur = arr[i];
            // 递归构造cur与arr中除去cur元素的字符串
            String[] nexts = removeCurElement(arr, i);  // 移除数组arr中的i元素
            // 递归调用process方法，用于处理nexts数组
            TreeSet<String> nextSet = process(nexts);

            for (String next : nextSet) {
                // 把cur+next放到堆中，进行排序
                treeSet.add(cur + next);
            }

        }
        return treeSet;
    }

    // 移除arr数组中i号元素，返回一个新的数组
    private static String[] removeCurElement(String[] arr, int i) {

        if (arr == null || arr.length == 0) {
            return new String[0];
        }

        String[] ans = new String[arr.length - 1];

        int index = 0;
        for (int j = 0; j < arr.length; j++) {

            if (j != i) {
                ans[index++] = arr[j];
            }

        }

        return ans;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
