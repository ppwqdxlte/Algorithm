package com.ppwqdxlte.basic.class08;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/26 17:09
 */
public class Code02_PrintBinaryTree {

    public void printBinaryTree(Node head){
        System.out.println("Print binary tree:");
        print(head,0,"H",15);
    }
    private void print(Node X,int level,String token,int len){
        int lenL,lenM,lenR = 0;
        if (X == null) {
            lenM = 4;
            lenL = (len - lenM)>>1;
            lenR = len - lenM - lenL;
            System.out.println(getSpace(level*len)+getSpace(lenL)+"null"+getSpace(lenR));
        }else {
            print(X.right,level + 1,"v",len);
            String val = token + X.value + token;
            lenM = val.length();
            lenL = (len - lenM)>>1;
            lenR = len - lenM - lenL;
            System.out.println(getSpace(level*len)+getSpace(lenL)+val+getSpace(lenR));
            print(X.left,level + 1,"^",len);
        }
    }
    private String getSpace(int n){
        String space = " ";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(space);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Code02_PrintBinaryTree test = new Code02_PrintBinaryTree();
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node node = generateRandomBST(maxLevel,maxValue);
            printTree(node);
            test.printBinaryTree(node);
        }
    }
}
