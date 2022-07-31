package qinfeng.zheng.date_20210825;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/25 22:30
 * @dec 怎么把一个int类型的数，提取出最右侧的1来
 *
 * 按位与& ,双1得1
 */
public class A_异或运算_03_求最右侧的1 {
    public static void main(String[] args) {
        int num = 600;
        System.out.println(Integer.toBinaryString(num));
        int ans = rightOne(num);
        System.out.println(Integer.toBinaryString(ans));
    }

    public static int rightOne(int num) {
        int ans = num & (-num);
        //  ~ 取反码,  反码+1 就是补码, 一个数的补码就是这个数的相反数
//        int ans = num & ((~num) + 1);  // 与上一行代码等价
        return ans;
    }

}
