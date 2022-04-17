package com.ppwqdxlte.basic.class08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/7/26 10:23
 * 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
 * 【提问】将N叉树编成二叉树：X最左子结点a不动，a的弟弟结点b、c依次作为a的单链右叉树，
 *          同理，d作为a的最左子结点不动，d的弟弟结点e作为哥哥d的右结点......
 * N-ary Tree:
 *       X
 *    /  |  \
 *   a   b   c
 *  / \
 * d   e
 * Binary Tree:
 *     X
 *    /
 *   a
 *  / \
 * d   b
 *  \   \
 *   e   c
 */
public class Code01_EncodeNaryTreeToBT {
    /**
     * 多叉树，有多少叉不一定
     * 注意！子结点的集合尚未初始化！！要结点初始化的时候手动初始化！
     */
    public static class Node{
        public int value;
        public List<Node> children;
        public Node(){}
        public Node(int v){
            value = v;
        }
        public Node(int v,List<Node> childs){
            value = v;
            children = childs;
        }
    }
    public static Node encodeNaryTreeToBT(Node head){
        encode(head);
        return head;
    }
    private static void encode(Node X){
        if (X == null) return;
        if (X.children == null || X.children.size() == 0) return;
        //处理左子结点
        Node leftSon = X.children.get(0);//最左子结点也许为null
        encode(leftSon);
        //X 右树都挪到左树
        Node pre = leftSon;
        for (int i = 1; pre != null && i < X.children.size(); i++) {
            if (pre.children == null) pre.children = new ArrayList<>();
            Node c = X.children.get(i);
            encode(X.children.get(i));//处理右子结点
            pre.children.add(c);
            pre = c;
        }
        //原子集合清空，只添加左结点
        X.children.clear();
        X.children.add(leftSon);
    }
    /**生成随机的多叉树
     * @param maxLevel 最深层数
     * @param maxNary 最多子结点数
     * @param maxValue 结点最大值
     * @return 多叉树
     */
    public static Node generateRandomNaryTree(int maxLevel,int maxNary,int maxValue){
        Node head = null;
        if (Math.random() < 0.2) return head;
        head = new Node((int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random())
                ,Math.random()<0.8?new ArrayList<>():null);
        generate(head,1,maxLevel,maxNary,maxValue);
        return head;
    }
    private static void generate(Node X,int curLevel,int maxLevel,int maxNary,int maxValue){
        if (curLevel > maxLevel || X == null || X.children == null) return;
        int nary = (int)((maxNary + 1)*Math.random());
        for (int i = 0; i < nary; i++) {
            Node son = new Node((int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random())
                    ,Math.random()<0.8?new ArrayList<>():null);
            generate(son,curLevel + 1,maxLevel,maxNary,maxValue);
            X.children.add(son);
        }
    }
    /**简单粗暴打印NT
     * @param head
     */
    private static void printNT(Node head){
        System.out.println("Print N-ary Tree:");
        pnt(head,null);
        System.out.println();
    }
    private static void pnt(Node X,Node father){
        if (X == null || X.children == null || X.children.size() == 0) {
            System.out.print(X == null ? X : X.value +"["+ (father==null ? null:father.value) +"]\t");
        }else {
            System.out.print(X.value +"["+ (father==null ? null:father.value) +"]\t");
            for (int i = 0; i < X.children.size(); i++) {
                pnt(X.children.get(i),X);
            }
        }
    }

    public static void main(String[] args) {
        /*Node n1 = new Node(1,new ArrayList<>()),n2 = new Node(2,new ArrayList<>());
        Node n3 = new Node(3),n4 = new Node(4),n5 = new Node(5),n6 = new Node(6);
        n1.children.add(n2);n1.children.add(n3);n1.children.add(n4);
        n2.children.add(n5);n2.children.add(n6);
        printNT(n1);
        Node bt = encodeNaryTreeToBT(n1);
        printNT(bt);*/

        int maxLevel = 4;
        int maxNary = 3;
        int maxValue = 100;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node node = generateRandomNaryTree(maxLevel, maxNary, maxValue);
            printNT(node);
            Node bT = encodeNaryTreeToBT(node);
            printNT(bT);
        }
    }

}
