package com.ppwqdxlte.basic.class09;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/27 12:41
 * 判断是否平衡二叉树
 * 平衡二叉树（Balanced Binary Tree）又被称为AVL树（有别于AVL算法），
 * 且具有以下性质：
 *          它是一 棵空树  或  它的左右两个子树的高度差的绝对值不超过1，
 *          并且左右两个子树都是一棵平衡二叉树。
 * 这个方案很好的解决了二叉查找树退化成链表的问题，
 * 把插入，查找，删除的时间复杂度最好情况和最坏情况都维持在O(logN)。
 * 但是频繁旋转会使插入和删除牺牲掉O(logN)左右的时间，
 * 不过相对二叉查找树BST来说，时间上稳定了很多。
 */
public class Code03_IsBalenced {

    private static class Info{
        public boolean isBalenced;
        public Integer height;
        public Info(){}
        public Info(boolean ib,Integer h){
            isBalenced = ib;
            height = h;
        }
    }

    public boolean isBalenced(Node head){
        return is(head).isBalenced;
    }
    private Info is(Node X){
        if (X == null) return new Info(true,0);
        Info il = X.left == null ? new Info(true,0) : is(X.left);
        Info ir = X.right == null ? new Info(true,0) : is(X.right);
        Info ix = new Info(false,0);
        if ((il.isBalenced && ir.isBalenced) && Math.abs(il.height - ir.height) <= 1){
            ix.isBalenced = true;
        }
        ix.height = Math.max(il.height,ir.height) + 1;
        return ix;
    }

    public static void main(String[] args) {
        Code03_IsBalenced test = new Code03_IsBalenced();

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

        System.out.println(test.isBalenced(null));
        System.out.println(test.isBalenced(new Node(1)));
        System.out.println(test.isBalenced(h1));
        System.out.println(test.isBalenced(h2));
        System.out.println(test.isBalenced(h3));
    }
}
