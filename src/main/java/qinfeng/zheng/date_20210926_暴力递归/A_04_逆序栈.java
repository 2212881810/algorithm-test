package qinfeng.zheng.date_20210926_暴力递归;

import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/28 22:18
 * @dec
 */
public class A_04_逆序栈 {

    /**
     * 这个递归也是需要好好理解的，太他妈苟了！！！
     * 删除栈底的元素，然后将其它元素直接盖下去！！
     *
     * @param stack
     * @return 栈底的元素
     */
    public static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            // last是栈底的元素，它会一直往上层调用返回，而上层的result值会push到栈中！！！
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 调用f方法取stack栈中最后一个元素，却是最后再push到stack栈中，从而实现逆序！！！
        // 最后一个元素，倒数第二个元素...倒数第N个元素
        int last = f(stack);
        reverse(stack);
        // 倒数第N个元素...倒数第二个元素, 最后一个元素！！！！
        stack.push(last);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
}
