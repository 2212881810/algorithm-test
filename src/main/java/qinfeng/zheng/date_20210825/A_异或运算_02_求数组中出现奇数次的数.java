package qinfeng.zheng.date_20210825;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/25 22:25
 * @dec 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class A_异或运算_02_求数组中出现奇数次的数 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 1, 4, 4, -1, -1, 3, -1};
        int odd = oddTimesNum(arr);
        System.out.println(odd);

    }

    // 解答本题用到异或的一个性质： 偶数次的数异或之后等于0
    public static int oddTimesNum(int[] arr) {
        int eor = 0;
        for (int item : arr) {
            eor ^= item;
        }
        return eor;
    }
}
