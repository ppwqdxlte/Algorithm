package com.ppwqdxlte.basic.class07;

import java.util.LinkedList;
import java.util.Queue;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/23 22:19
 * 按层遍历二叉树
 * 宽度范畴
 */
public class Code03_LevelTraversalBT {

    public void levelTraversalBT(Node head){
        if (head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            System.out.println(head.value);
            if (head.left != null){
                queue.add(head.left);
            }
            if (head.right != null){
                queue.add(head.right);
            }
        }
    }

    public static void main(String[] args) {
        Code03_LevelTraversalBT test = new Code03_LevelTraversalBT();

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

        test.levelTraversalBT(head);
    }
}
