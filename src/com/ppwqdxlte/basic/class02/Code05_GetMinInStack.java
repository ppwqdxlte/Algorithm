package com.ppwqdxlte.basic.class02;

import static com.ppwqdxlte.basic.class02.Code03_DoubleEndsQueueToStackAndQueue.*;
/**
 * @author:李罡毛
 * @date:2021/7/9 18:19
 * 获得栈里面的最小值，实现思路，栈维护两个双向链表，一个存数值，一个存每个范围的最小值
 * 双向链表模型，直接取自本课Code03_DoubleEndsQueueToStackAndQueue
 */
public class Code05_GetMinInStack {

    private MyStack<Integer> myStack01 = new MyStack<>();
    private MyStack<Integer> myStack02 = new MyStack<>();

    public int getMinInStack(){
        int min = myStack02.pop();
        myStack02.push(min);
        return min;
    }
    public void push(int value){
        myStack01.push(value);
        if (myStack02.isEmpty()){
            myStack02.push(value);
        }
        int top = myStack02.pop();
        myStack02.push(top);
        if (value<=top){
            myStack02.push(value);
        }
    }
    public int pop(){
        if (myStack01.isEmpty()) return -1;
        int poped = myStack01.pop();
        int top = myStack02.pop();
        if (poped > top){
            myStack02.push(top);
        }
        return poped;
    }

    public static void main(String[] args) {
        Code05_GetMinInStack test = new Code05_GetMinInStack();
        int[] arr= {5,3,6,2,4,1,7,12,43,3,3,1,1,1};
        for (int i = 0; i < arr.length; i++) {
            test.push(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(test.getMinInStack());
            test.pop();
        }
    }
}
