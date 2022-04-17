package com.ppwqdxlte.basic.class08;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/28 20:45
 * 求二叉树的2个点最远距离，经过的个数为距离，包含头尾
 */
public class Code07_MaxDistance {

    public int getMaxDistance(Node head){
        Info ih = md(head);
        return md(head) == null ? 0 :ih.maxDist;
    }
    private Info md(Node X){
        if (X == null) return null;
        Info il = md(X.left);
        Info ir = md(X.right);
        int height = 1;
        int maxDist = 1;
        if (il != null){
            height = il.height + 1;
            maxDist = Math.max(il.maxDist,il.height + 1);
        }
        if (ir != null){
            height = Math.max(height,ir.height + 1);
            maxDist = Math.max(ir.maxDist,ir.height + 1);
        }
        if (ir != null && il != null){
            maxDist = Math.max(Math.max(il.maxDist,ir.maxDist),
                    il.height + ir.height + 1);
        }
        return new Info(maxDist,height);
    }
    private static class Info{
        public int maxDist;
        public int height;
        public Info(){}
        public Info(int maxDist,int height){
            this.maxDist = maxDist;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        Code07_MaxDistance test = new Code07_MaxDistance();

        int maxLevel = 5;
        int maxValue = 10;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node h = generateRandomBST(maxLevel,maxValue);
            System.out.println("?????"+getBTSize(h)+"\t"+test.getMaxDistance(h));
            printTree(h);
            System.out.println("============================");
        }
    }
}
