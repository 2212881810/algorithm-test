package qinfeng.zheng.date_20210904;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/8 22:37
 * @dec 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * <p>
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down  ;  N=2时，打印: down down up
 */
public class A_10_打印折痕 {
    // 本题其实就是二叉树的中序遍历


    public static void f(int N) {
        process(1, N, true);
    }

    /**
     * 其实就是个中序遍历，将原本head.left 改成了i+1, true ; head.right 改成了i+1,false
     *
     * @param i    : 当前折的次数
     * @param N    ： 总次数
     * @param down ：
     */
    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);// 左子树，是down
        System.out.print(down ? true : false);
        System.out.print(" ");
        process(i + 1, N, false);

    }

    public static void main(String[] args) {
        f(3);
    }

}
