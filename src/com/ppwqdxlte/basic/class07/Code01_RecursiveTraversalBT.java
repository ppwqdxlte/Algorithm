package com.ppwqdxlte.basic.class07;

import java.util.*;

/**
 * @author:李罡毛
 * @date:2021/7/22 20:53
 * 递归遍历二叉树
 * 新概念！！！【递归序】
 * 【先序】头左右
 * 【中序】左头右
 * 【后序】左右头
 */
public class Code01_RecursiveTraversalBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int v){
            value = v;
        }
    }
    /**递归序
     * @param head
     */
    public void f(Node head){
        if (head == null) return;
        //1
        f(head.left);
        //2
        f(head.right);
        //3
    }
    public void pre(Node head){
        if (head == null) return;
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }
    public void in(Node head){
        if (head == null) return;
        pre(head.left);
        System.out.println(head.value);
        pre(head.right);
    }
    public void pos(Node head){
        if (head == null) return;
        pre(head.left);
        pre(head.right);
        System.out.println(head.value);
    }

    /**For test
     * 生成随机二叉树
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generateRandomBST(int maxLevel,int maxValue){

        return generate(1,maxLevel,maxValue);
    }
    public static Node generate(int curLevel,int maxLevel,int maxValue){
        if (curLevel > maxLevel || Math.random() < 0.25) return null;
        Node node  = new Node((int)((maxValue+1)*Math.random()) - (int)((maxValue+1)*Math.random()));
        node.left = generate(curLevel + 1,maxLevel,maxValue);
        node.right = generate(curLevel + 1,maxLevel,maxValue);
        return node;
    }

    /**For test
     * 打印二叉树，左转90°横着看
     * @param head
     */
    public static void printTree(Node head) {
        System.out.println("Print Tree:");
        printInOrder(head,0,"H",15);
    }
    private static void printInOrder(Node head, int height, String mark, int len) {
        int lenM = "null".length();
        int lenL = (len - lenM)>>1;
        int lenR = len - lenM - lenL;
        if (head == null){
            System.out.println(getSpace(len*height) + getSpace(lenL)+"null"+getSpace(lenR));
            return;
        }
        printInOrder(head.right,height+1,"v",len);
        String val = mark + head.value + mark;
        lenM = val.length();
        lenL = (len - lenM)>>1;
        lenR = len - lenM - lenL;
        val = getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(len*height) + val);
        printInOrder(head.left,height+1,"^",len);
    }
    private static String getSpace(int num) {
        String space = " ";
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < num; i++) {
            str.append(space);
        }
        return str.toString();
    }

    /**For test
     * 判断两个二叉树是否等值且结构一样
     * @param head1 二叉树1
     * @param head2 二叉树2
     * @return 是否值等值结构
     */
    public static boolean isSameValueStructure(Node head1,Node head2){
        if (head1 != null && head2 == null) return false;
        if (head1 == null && head2 != null) return false;
        if (head1 == null) return true;
        if (head1.value != head2.value) return false;
        return isSameValueStructure(head1.left,head2.left)
                && isSameValueStructure(head1.right,head2.right);
    }
    /**For test
     * String转Node
     * @param value 字符串
     * @return 生成的结点
     */
    public static Node strToNode(String value){
        if (value == null) return null;
        return new Node(Integer.valueOf(value));
    }

    /**求出二叉树的size
     * @param head 二叉树头结点
     * @return 二叉树的size
     */
    public static int getBTSize(Node head){
        if (head == null) return 0;
        List<Node> nodes = new ArrayList<>();
        in(head,nodes);
        return nodes.size();
    }
    private static void in(Node X,List<Node> list){
        if (X == null) return;
        in(X.left,list);
        list.add(X);
        in(X.right,list);
    }
    /**求出二叉搜索树的size，如果不是BST，就返回0
     * @param head 二叉树头结点
     * @return 二叉搜索树的size
     */
    public static int getBSTSize(Node head){
        if (head == null) return 0;
        List<Node> nodes = new ArrayList<>();
        in(head,nodes);
        for (int i = 0; i < nodes.size()-1; i++) {
            if (nodes.get(i+1).value <= nodes.get(i).value) return 0;
        }
        return nodes.size();
    }

    public static void main(String[] args) {
        Code01_RecursiveTraversalBT test = new Code01_RecursiveTraversalBT();
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        test.pre(head);
        System.out.println("========");
        test.in(head);
        System.out.println("========");
        test.pos(head);
        System.out.println("========");
    }
}
