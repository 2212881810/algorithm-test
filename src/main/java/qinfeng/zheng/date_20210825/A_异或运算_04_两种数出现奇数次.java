package qinfeng.zheng.date_20210825;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/25 22:38
 * @dec
 */
public class A_异或运算_04_两种数出现奇数次 {
    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 14, 2, 2, 4, 4, 5, 5, 5, 5, 3, 3, 14, 14};
        print2KindsOddTimes(arr);

    }

    //一个数组中有两种数(假设是a,b 且a，b不相等)出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
    public static void print2KindsOddTimes(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;  // 最终的eor一定是出现了奇数次的那两种数的异或结果
        }
        // 因为出现奇数次的两种数不相等，所以eor一定不等于0 ==> 那么eor一定有一个位置上的数不等于0,即等于1

        // 取eor最右侧的1 , 说明a,b两个数在第rightOne一定不相同
        int rightOne = eor & (-eor);

        // 再以rightOne为标准将原数组arr分为两部分,
        // 一部分是rightOne这一位置为1的数,一部分是rightOne这一位置为0的数
        // 且a,b一定分别处在这两部分中！
        int eor2 = 0;
        for (int i : arr) {
            // &运算： 同时为1,结果为1
            if ((i & rightOne) != 0) { // i的rightOne这一位置的数为1
                eor2 ^= i;
            }
        }

        System.out.println(eor2 + " <-----> " + (eor ^ eor2));

    }
}
