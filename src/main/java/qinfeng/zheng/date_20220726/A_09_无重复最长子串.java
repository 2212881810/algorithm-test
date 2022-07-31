package qinfeng.zheng.date_20220726;

/**
 * 实现原理: 从头开始遍历
 */
public class A_09_无重复最长子串 {

    public static int longestSubstr(String s) {

        int start = 0;
        int end = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 判断i字符在0~i中出现过没有
            int index = s.substring(start, i).indexOf(chars[i]);
            // 如果没有出现过,start位置不变,end位置+1
            if (index == -1 && i + 1 > end) {
                end = i + 1;
            } else {
                // 否则:start,end位置都同时往后移index+1, index就是i字符在0~i中出现的位置
                start += index + 1;
                end += index + 1;
            }
        }
        return end - start;
    }

    public static void main(String[] args) {
        String s = "abcabcefee";
        int span = longestSubstr(s);
        System.out.println(span);
    }
}
