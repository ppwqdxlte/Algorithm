package com.ppwqdxlte.basic.class09;

import java.util.LinkedList;
import java.util.Queue;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/26 22:31
 * 判断是否完全二叉树 Complete Binary Tree
 * 层序，1~M连续的结点中间没有断点
 */
public class Code02_IsCBT {

    public boolean isCBT(Node head){
        return is(head);
    }
    private boolean is(Node X){
        if (X == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(X);
        Node pre = null;
        while (!queue.isEmpty()){
            Node c = queue.poll();
            if (pre != null && (c.left != null || c.right != null)) return false;
            if (c.left == null && c.right != null) return false;
            if (c.left != null){
                queue.add(c.left);
            }
            if (c.right != null){
                queue.add(c.right);
            }
            if (c.left == null || c.right == null){
                pre = c;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Code02_IsCBT test = new Code02_IsCBT();

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
//        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(test.isCBT(head));

    }
}
