package qinfeng.zheng.date_20210826;

import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 10:55
 * @dec
 */
public class A_06_取栈中的最小值 {

    private static class MyStack {
        // 使用了两个栈
        Stack<Integer> stack;
        // 辅助栈
        Stack<Integer> minStack;

        public MyStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }


        public void push(int value) {

            // 如果栈中为空，则value添加到两个栈中
            if (stack.isEmpty()) {
                stack.push(value);
                minStack.push(value);
            } else {
                // 如果栈中不为空，stack栈正常添加数据value
                stack.push(value);
                //对于minStack栈而言，先比较一下栈顶元素与当前value的大小，
                //如果栈顶元素小于value，那就再push一个栈顶元素进去，否则push value
                Integer minValue = minStack.peek();
                if (value < minValue) {
                    minStack.push(value);
                } else {
                    minStack.push(minValue);
                }
            }
        }
        public int pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("栈已空！");
            }

            // 弹栈时，先把辅助栈栈顶的元素先弹掉
            minStack.pop();
            return stack.pop();
        }

        // 获取栈中最小值时，直接从辅助栈中取值
        public int getMinValue() {
            if (stack.isEmpty()) {
                throw new RuntimeException("栈已空！");
            }
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(2);
        stack.push(6);
        stack.push(19);
        stack.push(3);
        stack.push(1);

        System.out.println(stack.getMinValue());
        System.out.println(stack.pop());
        System.out.println(stack.getMinValue());
    }


}
