package qinfeng.zheng.date_20220726;

import java.util.Stack;

/**
 * 递归压栈的套路,真的需要好好理解~
 */
public class A_10_逆序栈 {


    /**
     * 获取并移除stack中的最下面一个元素, 其它元素的顺序不变!!!!!!!!!!!
     *
     * @param stack
     * @return 如何理解此方法的套路呢????
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        //  弹出栈顶的元素!
        Integer pop = stack.pop();

        // 说明stack中只有一个元素
        if (stack.isEmpty()) {
            return pop;
        } else {
            // 说明stack中还其它的元素
            // 再从栈中获取一个元素
            int lastElement = getAndRemoveLastElement(stack);
            // 把之前弹出来的元素再一次压到栈中
            stack.push(pop);
            // 返回最后一个元素
            return lastElement;
        }
    }


    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 获取原来stack中最后一个元素
        int lastElement = getAndRemoveLastElement(stack);
        reverse(stack);
        // 将原栈中最后一个元素进行压栈
        stack.push(lastElement);

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
//        System.out.println(getAndRemoveLastElement(stack));
        reverse(stack);
        System.out.println(stack);
    }
}
