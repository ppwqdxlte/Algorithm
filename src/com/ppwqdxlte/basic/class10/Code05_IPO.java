package com.ppwqdxlte.basic.class10;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:李罡毛
 * @date:2021/8/2 22:03
 * 【提问】成本（投资）数组，利润数组，一一对应各个项目，W初始资金，最多做K个项目，怎么投资能获得最大利润？
 */
public class Code05_IPO {

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金(原文写错了，应该是利润最大化)
    public int findMaximizedCapital(int K,int W,int[] profits,int[] capitals){
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Program(profits[i],capitals[i]));
        }
        while (K > 0){
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W){
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()){
                return W;
            }
            W += maxProfitQ.poll().p;
            K --;
        }
        return W;
    }
    private static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }
    private static class MaxProfitComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }

    public static void main(String[] args) {
        Code05_IPO test = new Code05_IPO();

        int[] cost = {10,300,100,500,10000,20000};
        int[] profits = {1000,90,500,20000,100000,50000};
        System.out.println(test.findMaximizedCapital(4,100,profits,cost));

    }
}
