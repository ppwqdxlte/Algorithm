package com.ppwqdxlte.basic.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author:李罡毛
 * @date:2021/7/9 11:30
 * 双向链表实现 栈 和 队列
 */
public class Code03_DoubleEndsQueueToStackAndQueue {
    /**双向结点
     * @param <T> 节点值，泛型
     */
    public static class Node<T>{
        public T value;
        public Node<T> last;
        public Node<T> next;
        public Node(T value){
            this.value = value;
        }
    }

    /**双向队列【本例中构建栈和队列的基础】
     * @param <T> 元素值，泛型
     */
    public static class DoubleEndsQueue<T>{
        public Node<T> head;
        public Node<T> tail;
        public void addToHead(Node<T> node){
            if (node == null) return;
            if (head == null) {
                node.last = null;
                node.next = null;
                head = node;
                tail = head;
            }
            head.last = node;
            node.next = head;
            head = node;
        }
        public void addToTail(Node<T> node){
            if (node == null) return;
            if (tail == null){
                node.last = null;
                node.next = null;
                tail = node;
                head = tail;
            }
            tail.next = node;
            node.last = tail;
            tail = node;
        }
        public Node<T> popFromHead(){
            if (head == null) return null;
            Node<T> originHead = head;
            if (head == tail) {
                head = null;
                tail = null;
                return originHead;
            }
            head.next.last = null;
            head = head.next;
            return originHead;
        }
        public Node<T> popFromTail() {
            if (tail == null) return null;
            Node<T> originTail = tail;
            if (tail == head){
                head = null;
                tail = null;
                return originTail;
            }
            tail.last.next = null;
            tail = tail.last;
            return originTail;
        }
        public boolean isEmpty(){
            return head == null;
        }
    }

    /**底层用双向链表实现的栈
     * @param <T>
     */
    public static class MyStack<T>{
        private final DoubleEndsQueue<T> doubleEndsQueue = new DoubleEndsQueue<>();
        public MyStack(){/*doubleEndsQueue = new DoubleEndsQueue<T>();*/}
        public void push(T value){
            Node<T> node = new Node<>(value);
            doubleEndsQueue.addToTail(node);
        }
        public T pop(){
            if (isEmpty())return null;
            return doubleEndsQueue.popFromTail().value;
        }
        public boolean isEmpty(){
            return doubleEndsQueue.isEmpty();
        }
    }

    /**底层用双向链表实现的队列
     * @param <T>
     */
    public static class MyQueue<T>{
        private final DoubleEndsQueue<T> doubleEndsQueue = new DoubleEndsQueue<>();
        public MyQueue(){}
        public void add(T value){
            Node<T> node = new Node<>(value);
            doubleEndsQueue.addToHead(node);
        }
        public T poll(){
            if (isEmpty()) return null;
            return doubleEndsQueue.popFromTail().value;
        }
        public boolean isEmpty(){
            return doubleEndsQueue.isEmpty();
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
        int testTimes = 1000000;
        boolean isFaild = false;
        for (int i = 0; i < testTimes && !isFaild; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> jdkStack = new Stack<>();
            Queue<Integer> jdkQueue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                if (Math.random()<0.5){//增
                    int newValue = (int)((maxValue+1)*Math.random());
                    myStack.push(newValue);
                    myQueue.add(newValue);
                    jdkStack.push(newValue);
                    jdkQueue.add(newValue);
                }else {//删
                    if (jdkStack.isEmpty()) continue;
                    if (!isEqual(myStack.pop(),jdkStack.pop())) {
                        System.out.println("MyStack ooops!");
                        isFaild = true;
                        break;
                    }
                    if (!isEqual(myQueue.poll(),jdkQueue.poll())){
                        System.out.println("MyQueue ooops!");
                        isFaild = true;
                        break;
                    }
                }
            }
        }
        System.out.println("finished!");
    }

}
