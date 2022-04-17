package com.ppwqdxlte.basic.class12;

import java.util.Stack;

/**
 * @author:李罡毛
 * @date:2021/8/17 11:48
 * 【提问】怎么用暴力递归翻转栈，且不借助其它数据结构？也就是说额外空间复杂度O(1)??
 */
public class Code02_ReverseStackWithRecursive {
    public void reverse(Stack<Integer> stack){
        if (stack.isEmpty()) return;
        int bottom = removeBottom(stack);
        reverse(stack);
        stack.push(bottom);
    }
    /** 栈底元素移除掉
        上面的元素盖下来
        返回移除掉的栈底元素
     * @param stack 栈
     * @return bottom
     */
    private Integer removeBottom(Stack<Integer> stack){
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            Integer next = removeBottom(stack);
            stack.push(result);
            return next;
        }
    }

    // for test
    private void printStack(Stack<Integer> stack){
        System.out.println("===================================");
        Stack<Integer> stack1 = new Stack<>();
        while (!stack.isEmpty()){
            Integer i = stack.pop();
            stack1.push(i);
            System.out.println(i);
        }
        while (!stack1.isEmpty()){
            stack.push(stack1.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);stack.push(9);stack.push(8);stack.push(7);
        Code02_ReverseStackWithRecursive test = new Code02_ReverseStackWithRecursive();
        test.printStack(stack);
        test.reverse(stack);
        test.printStack(stack);

        test.removeBottom(stack);
        test.printStack(stack);
    }
}
