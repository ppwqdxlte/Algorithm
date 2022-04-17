package com.ppwqdxlte.basic.class12;

import java.util.Stack;

/**
 * @author:李罡毛
 * @date:2021/8/16 20:54
 * 【汉诺塔】左中右 |、|、|三根杆子，一叠盘子N个，只能是小的摞在大的上，大的叠在小的下，
 * 已知左塔N个盘子，怎么倒腾能把所有盘子倒腾到右边？且，每次倒腾都要遵循上述规则。
 */
public class Code01_Hanoi {
    /**把左塔所有盘子倒腾到右塔-----最简单直接明了的方法
     * @param n 初始左塔N个盘子
     */
    public void hanoi1(int n){
        leftToRight(n);
    }
    private void leftToRight(int n){
        if (n == 1){// base case
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move "+n+" from left to right");
        midToRight(n - 1);
    }
    private void leftToMid(int n){
        if (n == 1){
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move "+n+" from left to mid");
        rightToMid(n - 1);
    }
    private void midToRight(int n){
        if (n == 1){
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move "+n+" from mid to right");
        leftToRight(n - 1);
    }
    private void rightToMid(int n){
        if (n == 1){
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move "+n+" from right to mid");
        leftToMid(n - 1);
    }
    private void midToLeft(int n){
        if (n == 1){
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move "+n+" from mid to left");
        rightToLeft(n - 1);
    }
    private void rightToLeft(int n){
        if (n == 1){
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move "+n+" from right to left");
        midToLeft(n - 1);
    }
    /**左塔N个盘子全部移动到右塔-----对第一种方法的进一步抽象简化
     * @param n 左塔N个盘子
     */
    public void hanoi2(int n){
        fromTo(n,"left","right","mid");
    }
    private void fromTo(int n,String from,String to,String other){
        if (n == 1){
            System.out.println("Move 1 from "+from+" to "+to);
            return;
        }
        fromTo(n-1,from,other,to);
        System.out.println("Move "+n+" from "+from+" to "+to);
        fromTo(n-1,other,to,from);
    }

    /**把左塔N个盘子全部倒腾到右塔------非递归方法，递归栈，感觉就是中序，左头右的思想
     * @param N 左塔N个盘子
     * 分析暴力递归的打印顺序，不难发现，整体好像二叉树【左 头 右】中序顺序，
     *     头结点是大盘子，左子树右子树是仅次于大盘子的较大盘子树，
     *     且整棵树都是满二叉树，盘子有几个，就有几层，每一层的结点都是一个盘子，
     *     head  from -> to,other
     *     左子结点  from -> other,to
     *     右子结点  other -> to,from
     * 【思路】那么拿Stack栈作为数据结构的话，那么压栈的顺序就是 【中序 左头右】！
     *          当record.hasLeft == true，则打印不用压回去！
     */
    public void hanoi3(int N){
        if (N < 1) return;
        Stack<Record> stack = new Stack<>();
        stack.push(new Record(N,false,"left","right","mid"));
        while (!stack.isEmpty()){
            Record cur = stack.pop();
            if (cur.base == 1){
                System.out.println("Move 1 from "+cur.from+ " to "+ cur.to);
                if (!stack.isEmpty()){
                    stack.peek().hasLeft = true;
                }
            }else {
                if (!cur.hasLeft){
                    stack.push(cur);
                    stack.push(new Record(cur.base-1,false,cur.from,cur.other,cur.to));
                }else {
                    System.out.println("Move "+cur.base+" from "+cur.from+" to "+cur.to);
                    stack.push(new Record(cur.base-1,false,cur.other,cur.to,cur.from));
                }
            }
        }
    }
    private static class Record{
        public int base;
        public boolean hasLeft;
        public String from;
        public String to;
        public String other;
        public Record(){}
        public Record(int base,boolean hasLeft,String from,String to,String other){
            this.base = base;
            this.hasLeft = hasLeft;
            this.from = from;
            this.to = to;
            this.other = other;
        }
    }

    public static void main(String[] args) {
        Code01_Hanoi test = new Code01_Hanoi();
        int n = 4;
        test.hanoi1(n);
        System.out.println("============");
        test.hanoi2(n);
		System.out.println("============");
		test.hanoi3(n);
    }
}
