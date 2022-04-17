package com.ppwqdxlte.basic.class07;

import java.util.Stack;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/22 21:16
 * 非递归方式遍历二叉树
 * 【理解诀窍】：画出二叉树，先序，中序，后序 分别有 不同的 压栈出栈路线，
 *              根据路线思考处理逻辑
 */
public class Code02_UnRecursiveTraversalBT {
    /**先序非递归处理（打印）二叉树
     * @param head
     */
    public void pre(Node head){
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            System.out.println(head.value);
            if (head.right != null){
                stack.add(head.right);
            }
            if (head.left != null){
                stack.add(head.left);
            }
        }
    }
    /**中序非递归处理（打印）二叉树
     * @param head
     */
    public void in(Node head){
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null){//每行最右结点弹出就会变空，但是会加入下一层最右非空结点
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.println(head.value);
                head = head.right;
            }
        }
    }
    /**后序非递归处理（打印）二叉树
     * @param head
     */
    public void pos1(Node head){
        if (head == null) return;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()){
            head = s1.pop();
            s2.push(head);//头 右 左
            if (head.left != null){
                s1.push(head.left);
            }
            if (head.right != null){
                s1.push(head.right);
            }
        }
        while (!s2.isEmpty()){
            System.out.println(s2.pop().value);
        }
    }
    /**后序非递归处理（打印）二叉树
     * @param head
     */
    public void pos2(Node head){
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c = null;
        while (!stack.isEmpty()){
            c = stack.peek();
            if (c.left != null && head != c.left && head != c.right){
                stack.add(c.left);
            }else if (c.right != null && head != c.right){
                stack.add(c.right);
            }else {
                c = stack.pop();
                System.out.println(c.value);
                head = c;
            }
        }
    }

    /**后序非递归处理（打印）二叉树
     * 要了亲命了，想了大半天的结果！！！
     * 只用了一个栈，两个额外变量！非递归的二叉树后序打印！！！
     * @param head
     */
    public void pos0(Node head){
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        Node c = null;
        Node f = null;//标记待处理（打印）结点的父结点，或 处理完了的子结点
        while (!stack.isEmpty()){
            c = stack.peek();//栈顶值，不弹   2
            if (c.right != null && f != c.left && f != c.right){
                stack.add(c.right);
            }
            if (c.left != null && f != c.left && f != c.right){
                stack.add(c.left);
            }
            if (f == c.right || (c.right == null && c.left == null)
                || (f == c.left && c.right == null)){//右节点为null，考虑在内！否则BUG！
                c = stack.pop();//弹出
                System.out.println(c.value);
            }
            f = c;
        }
    }

    public static void main(String[] args) {
        Code02_UnRecursiveTraversalBT test = new Code02_UnRecursiveTraversalBT();

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.left = new Node(10);
        head.left.right.right = new Node(11);
        head.right.left.left = new Node(12);
        head.right.left.right = new Node(13);
        head.right.right.left = new Node(14);
        head.right.right.right = new Node(15);

        test.pre(head);
        System.out.println("========");
        test.in(head);
        System.out.println("========");
        test.pos0(head);
        System.out.println("========");
        test.pos1(head);
        System.out.println("========");
        test.pos2(head);
        System.out.println("========");

    }
}
