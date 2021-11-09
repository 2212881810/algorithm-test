package qinfeng.zheng.date_20210926_暴力递归;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/27 22:59
 * @dec 打印一个字符串的全部排列
 */
public class A_03_字符串全排列 {


    /**
     * 第一种递归实现方式
     *
     * @param rest   :剩余的还没有排列的字符
     * @param ans    ： 存储答案的集合
     * @param before ：已经排好的字符串
     */
    public static void process1(List<Character> rest, List<String> ans, String before) {
        if (rest.isEmpty()) {
            ans.add(before);
            return;
        } else {
            for (int i = 0; i < rest.size(); i++) {
                Character cur = rest.get(i);
                rest.remove(i);
                process1(rest, ans, before + cur);
                // 恢复现场
                rest.add(i, cur);
            }
        }
    }

    public static List<String> permutation1(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        char[] chars = str.toCharArray();
        List<Character> rest = new ArrayList<>(chars.length);
        for (char ch : chars) {
            rest.add(ch);
        }
        process1(rest, ans, "");
        return ans;
    }


    /**
     * 第二种实现方式
     *
     * @param str   ： 字符数组
     * @param index ： 本次递归要处理的索引值
     * @param ans   ： 结果集合
     */
    public static void process2(char[] str, int index, List<String> ans) {

        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }

        // 因为index之前的字符已经移动排序过了
        for (int i = index; i < str.length; i++) {
            swap(str, i, index); // 交换数组中元素的位置，最终都是在上面的if条件中进行add

            // 处理index后面一个元素
            process2(str, index + 1, ans);
            // 恢复现场
            swap(str, i, index);
        }

    }

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }


    /**
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     *
     * @param str   ： 字符数组
     * @param index ： 本次递归要处理的索引值
     * @param ans   ： 结果集合
     */
    public static void process3(char[] str, int index, List<String> ans) {

        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        // 每次递归调用的都会创建这个数组，
        boolean[] visited = new boolean[256];
        // 因为index之前的字符已经移动排序过了
        for (int i = index; i < str.length; i++) {
            // str[i]元素没有出现过，

            // 如index ==  1, i == 1 ,  此时 str[1] == c  ==> 转成ascii码后为99. ==> visited[99] == true,
            // 然后index == 1, i == 2 , str[2] == c ==>  转成ascii码后为99. ==>visited[99] == true, 不再进行后面的交换逻辑了
            // 因为str[1]已经与str[index] 进行了交换，此时str[2] ==  str[1] , 就不再需要与str[index]交换了
            if (!visited[str[i]]) {
                // 表示str[i]元素已经出现过了
                visited[str[i]] = true;
                swap(str, i, index); // 交换数组中元素的位置，最终都是在上面的if条件中进行add
                // 处理index后面一个元素
                process3(str, index + 1, ans);
                // 恢复现场
                swap(str, i, index);
            }

        }

    }
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process3(str, 0, ans);
        return ans;
    }


    public static void main(String[] args) {
        //String str = "abc";
        String str = "acc";
        List<String> ans1 = permutation1(str);
        for (String s : ans1) {
            System.out.println(s);
        }

        System.out.println("=================");
        List<String> ans2 = permutation2(str);
        for (String s : ans2) {
            System.out.println(s);
        }

        System.out.println("=================");
        List<String> ans3 = permutation3(str);
        for (String s : ans3) {
            System.out.println(s);
        }

    }
}
