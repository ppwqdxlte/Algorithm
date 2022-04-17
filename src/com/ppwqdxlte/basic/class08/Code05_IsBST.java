package com.ppwqdxlte.basic.class08;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/27 10:12
 * 判断是否二叉搜索树 Binary Search Tree
 * （又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
 * 1、若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 2、若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 3、它的左、右子树也分别为二叉排序树。
 * 二叉搜索树作为一种经典的数据结构，它既有链表的快速插入与删除操作的特点，又有数组快速查找的优势；
 * 所以应用十分广泛，例如在文件系统和数据库系统一般会采用这种数据结构进行高效率的排序与检索操作。
 */
public class Code05_IsBST {

    public boolean isBST(Node head){
        return is(head).isBST;
    }
    private Info is(Node X){
        if (X == null) return new Info(true,null,null);
        Info il = X.left == null ? new Info(true,null,null) : is(X.left);
        Info ir = X.right == null ? new Info(true,null,null) : is(X.right);
        Info ix = new Info(false,null,null);
        ix.max = il.max == null ? (ir.max == null?X.value:Math.max(ir.max,X.value))
                :(ir.max == null?Math.max(il.max,X.value)
                :Math.max(Math.max(il.max,ir.max),X.value));
        ix.min = il.min == null ? (ir.min == null?X.value:Math.min(ir.min,X.value))
                :(ir.min == null?Math.min(il.min,X.value)
                :Math.min(Math.min(il.min,ir.min),X.value));
        if ((il.isBST && ir.isBST)
                && (il.max == null || il.max < X.value)
                && (ir.min == null || ir.min > X.value)){
            ix.isBST = true;
        }
        return ix;
    }
    private static class Info{
        public boolean isBST;
        public Integer max;
        public Integer min;
        public Info(){}
        public Info(boolean isBST,Integer max,Integer min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        Code05_IsBST test = new Code05_IsBST();

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

        System.out.println(test.isBST(h1));
        System.out.println(test.isBST(h2));
        System.out.println(test.isBST(h3));
    }
}
