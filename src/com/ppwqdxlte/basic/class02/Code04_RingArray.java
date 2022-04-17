package com.ppwqdxlte.basic.class02;

import java.util.*;

/**
 * @author:李罡毛
 * @date:2021/7/9 13:31
 * 有序集合实现 栈 和
 * 数组实现  队列
 * 为了提高队列空间利用率，有一个【环状数组】的算法
 */
public class Code04_RingArray {
    public static class MyStack{
        private int[] arr;
        private int pushI;//压入的位置
        private int popI;//弹出的位置
        private int size;//元素个数
        private int limit;//栈大小
        public MyStack(){ }
        public MyStack(int limit){
            this.limit = limit;
            arr = new int[limit];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = -1;
            }
        }
        public void push(int value){
            if (size == limit) return;
            size++;
            arr[pushI] = value;
            popI = pushI;
            if (size < limit){
                pushI ++;
            }
        }
        public int pop(){
            if (size == 0) return -1;
            size--;
            int poped = arr[popI];
            arr[popI] = -1;
            pushI = popI;
            if (size > 0){
                popI--;
            }
            return poped;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void printMyQueue(){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]+"\t");
            }
            System.out.println("\n栈size：\t"+size
                    +"\n栈pushIndex：\t"+pushI
                    +"\n栈pollIndex：\t"+popI);
            System.out.println("--------------------");
        }
    }
    public static class MyQueue{
        private int[] arr;
        private int size;//实际的元素的数量
        private int limit;//数组限制
        private int addI;//添加位置
        private int pollI;//取出位置
        public MyQueue(){}
        public MyQueue(int limit){
            this.limit = limit;
            arr = new int[limit];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = -1;
            }
        }
        public void add(int value){
            if (size == limit) return;//已满，不能添加，只可取出
            size++;
            arr[addI] = value;
            if (addI == limit-1){
                addI = 0;
            } else{
                addI++;
            }
        }
        public int poll(){
            if (size == 0) return -1;//已空，不能取出，只可添加
            size--;
            int polledOne = arr[pollI];
            arr[pollI] = -1;//-1表示此处没有
            if (pollI == limit-1){
                pollI = addI - size;//找到最右边不为空的index
            }else {
                pollI++;
            }
            return polledOne;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void printMyQueue(){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]+"\t");
            }
            System.out.println("\n队列size：\t"+size
                    +"\n队列addIndex：\t"+addI
                    +"\n队列pollIndex：\t"+pollI);
            System.out.println("--------------------");
        }
    }
    //For test
    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null & o2 == null) return true;
        if (o1 == null & o2 != null) return false;
        if (o2 == null) return false;
        return o1.equals(o2);
    }
    public static void main(String[] args) {
        int oneTestDataNum = 50;
        int maxValue = 10000;
        int testTimes = 10000;
        boolean isFaild = false;
        for (int i = 0; i < testTimes && !isFaild; i++) {
            MyStack myStack = new MyStack(oneTestDataNum);
            MyQueue myQueue = new MyQueue(oneTestDataNum);//定长
            Stack<Integer> jdkStack = new Stack<>();
            Queue<Integer> jdkQueue = new LinkedList<>();//测试队列不限长，导致报错！！！
            for (int j = 0; j < oneTestDataNum; j++) {//不超过queue数组的定长就没事儿
                if (Math.random()<0.5){//增
                    int newValue = (int)((maxValue+1)*Math.random());
                    myStack.push(newValue);
                    myQueue.add(newValue);
                    jdkStack.push(newValue);
                    jdkQueue.offer(newValue);
//                    myStack.printMyQueue();
                }else {//删
                    if (jdkStack.isEmpty()) continue;
                    int myPoped = myStack.pop();
                    int jdkPoped = jdkStack.pop();
                    if (!isEqual(myPoped,jdkPoped)) {
                        System.out.println("MyStack ooops!");
                        isFaild = true;
//                        System.out.println(myPoped);
//                        System.out.println(jdkPoped);
//                        System.out.println(myStack.size==jdkStack.size());
                        break;
                    }
                    int myPolled = myQueue.poll();
                    int jdkPolled = jdkQueue.poll();
                    if (!isEqual(myPolled,jdkPolled)){
                        System.out.println("MyQueue ooops!");
//                        System.out.println(myPolled);
//                        System.out.println(jdkPolled);
//                        myStack.printMyQueue();
                        isFaild = true;
                        break;
                    }
                }
            }
        }
        System.out.println("finished!");

        //排查错误测试：最终发现是jdk自带的queue不限长！而我的数组定长所以，报错了！！
        /*int limit = 10;
        int valueLimit = 100;
        MyQueue myQueue = new MyQueue(10);
        for (int i = 0; i < limit+5; i++) {
            int v = (int)((valueLimit+1)*Math.random());
            myQueue.add(v);
            myQueue.printMyQueue();
        }
        System.out.println("==================================");
        for (int i = 0; i < limit+5; i++) {
            myQueue.poll();
            myQueue.printMyQueue();
        }*/
        /*int limit = 10;
        int valueLimit = 100;
        MyStack myStack = new MyStack(limit);
        for (int i = 0; i < limit+5; i++) {
            int v = (int)((valueLimit+1)*Math.random());
            myStack.push(v);
            myStack.printMyQueue();
        }
        System.out.println("==================================");
        for (int i = 0; i < limit+5; i++) {
            myStack.pop();
            myStack.printMyQueue();
        }*/
    }
}
