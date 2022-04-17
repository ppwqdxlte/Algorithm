package com.ppwqdxlte.basic.class02;

import static com.ppwqdxlte.basic.class02.Code03_DoubleEndsQueueToStackAndQueue.*;

/**
 * @author:李罡毛
 * @date:2021/7/9 20:51
 * 两个栈实现一个队列，左手倒右手，右手倒左手，要倒全都倒，不要剩了不倒
 */
public class Code06_TwoStacksImplementQueue {
    private MyStack<Integer> myStack1 = new MyStack<>();
    private MyStack<Integer> myStack2 = new MyStack<>();

    public void add(int value){
        myStack1.push(value);
    }
    public int poll(){
        while (!myStack1.isEmpty()){
            myStack2.push(myStack1.pop());
        }
        return myStack2.pop();
    }

    public static void main(String[] args) {
        Code06_TwoStacksImplementQueue test = new Code06_TwoStacksImplementQueue();
        int[] arr= {5,3,6,2,4,1,7,12,43,3,3,1,1,1};
        for (int i = 0; i < arr.length; i++) {
            test.add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(test.poll());
        }
    }

}
