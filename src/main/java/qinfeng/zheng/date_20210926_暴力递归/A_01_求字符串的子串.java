package qinfeng.zheng.date_20210926_暴力递归;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/27 22:08
 * @dec
 */
public class A_01_求字符串的子串 {


    /**
     * 打印一个字符串的全部子序列
     * @param str    : 字符数组
     * @param index  ：本次递归要处理的字符在数组str中下标值
     * @param ans    ： 存储子串的结果集合
     * @param before ：表示index下标之前已经确定好的子串
     */
    public static void process(char[] str, int index, List<String> ans, String before) {
        if (index == str.length) {  // 表示str中所有的字符都已经处理了
            ans.add(before);
            return;
        }
        // 对于index字符来讲，一共有两种可能，一种是before+"", 一种是before+str[index]
        process(str, index + 1, ans, before);
        process(str, index + 1, ans, before + String.valueOf(str[index]));
    }

    /**
     * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     * @param str    : 字符数组
     * @param index  ：本次递归要处理的字符在数组str中下标值
     * @param ans    ： 存储子串的结果集合
     * @param before ：表示index下标之前已经确定好的子串
     */
    public static void process2(char[] str, int index, Set<String> ans, String before) {
        if (index == str.length) {  // 表示str中所有的字符都已经处理了
            ans.add(before);
            return;
        }
        // 对于index字符来讲，一共有两种可能，一种是before+"", 一种是before+str[index]
        process2(str, index + 1, ans, before);
        process2(str, index + 1, ans, before + String.valueOf(str[index]));
    }


    public static void main(String[] args) {
        String s = "abc";
        List<String> ans = new ArrayList<>();
        // 求s的所有子串
        process(s.toCharArray(), 0, ans, "");
        for (String str : ans) {
            System.out.println(str);
        }
    }

}
