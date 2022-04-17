package com.ppwqdxlte.basic.class09;

import java.util.LinkedList;
import java.util.Queue;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/27 13:17
 * 判断是否满二叉树 Full Binary Tree
 * 国内教程定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。
 * 也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树
 */
public class Code01_IsFull {

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info all = process(head);
        return (1 << all.height) - 1 == all.nodes;
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }

    public boolean isFull(Node head){
        return is(head);
    }
    private boolean is(Node X){
        if (X == null) return true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(X);
        Node curEnd = X;
        Node nextEnd = null;
        int level = 0;
        int count = 0;
        while (!queue.isEmpty()){
            Node c = queue.poll();
            if (c.left != null) {
                queue.add(c.left);
                nextEnd = c.left;
            }
            if (c.right != null) {
                queue.add(c.right);
                nextEnd = c.right;
            }
            count ++;
            if (c == curEnd){
                level ++;
                curEnd = nextEnd;
            }
        }
        return count == Math.pow(2,level) - 1;
    }

    public static void main(String[] args) {
        Code01_IsFull test = new Code01_IsFull();

        Node h1 = new Node(1);
        h1.left = new Node(2);h1.right = new Node(3);
        h1.left.left = new Node(4);h1.left.right = new Node(5);
        h1.right.left = new Node(6);h1.right.right = new Node(7);
        Node h2 = new Node(5);
        h2.left = new Node(4);h2.right = new Node(6);
        h2.left.left = new Node(3);/*h2.left.right = new Node(5);*/
        /*h2.right.left = new Node(6);*/h2.right.right = new Node(7);
        Node h3 = new Node(10);
        /*h3.left = new Node(7);*/h3.right = new Node(12);
//        h3.left.left = new Node(4);h3.left.right = new Node(8);
        h3.right.left = new Node(11);h3.right.right = new Node(19);

        System.out.println(test.isFull(null));
        System.out.println(test.isFull(new Node(1)));
        System.out.println(test.isFull(h1));
        System.out.println(test.isFull(h2));
        System.out.println(test.isFull(h3));

        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
