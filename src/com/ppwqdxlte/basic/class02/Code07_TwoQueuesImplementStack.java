package com.ppwqdxlte.basic.class02;

import java.util.Stack;

import static com.ppwqdxlte.basic.class02.Code03_DoubleEndsQueueToStackAndQueue.*;

/**
 * @author:李罡毛
 * @date:2021/7/9 21:06
 */
public class Code07_TwoQueuesImplementStack {
    private MyQueue<Integer> queue = new MyQueue<>();
    private MyQueue<Integer> help = new MyQueue<>();

    public void push(int value){
        queue.add(value);
    }
    public int pop(){
        int ans = -1;
        while (!queue.isEmpty()){
            ans = queue.poll();
            help.add(ans);
        }
        help.poll();
        MyQueue<Integer> tempQ = queue;
        queue = help;
        help = tempQ;
        return ans;
    }
    public int peek(){
        int ans = -1;
        while (!queue.isEmpty()){
            ans = queue.poll();
            help.add(ans);
        }
        MyQueue<Integer> tempQ = queue;
        queue = help;
        help = tempQ;
        return ans;
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public static void main(String[] args) {
        System.out.println("test begin");
        Code07_TwoQueuesImplementStack myStack = new Code07_TwoQueuesImplementStack();
        Stack<Integer> jdkStack = new Stack<>();
        int testTime = 10000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!jdkStack.isEmpty()) {
                    System.out.println("Oops?why myStack is Empty?!");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                jdkStack.push(num);
            } else {
                int r = (int)Math.random();
                if (r < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    jdkStack.push(num);
                } else if (r < 0.5) {
                    if (myStack.peek() !=(Integer.valueOf(jdkStack.peek()))) {
                        System.out.println("Oops,peek() is failed!");
                    }
                } else if (r < 0.75) {
                    if (myStack.pop()!=(Integer.valueOf(jdkStack.pop()))) {
                        System.out.println("Oops,pop() is failed!");
                    }
                } else {
                    if (myStack.isEmpty() != jdkStack.isEmpty()) {
                        System.out.println("Oops,size is defferent!");
                    }
                }
            }
        }

        System.out.println("test finish!");
    }
}
