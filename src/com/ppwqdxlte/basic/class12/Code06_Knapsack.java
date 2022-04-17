package com.ppwqdxlte.basic.class12;

/**
 * @author:李罡毛
 * @date:2021/8/19 9:52
 * 【背包问题】
 */
public class Code06_Knapsack {
    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public int maxValue1(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0){
            return -1;
        }
        //尝试函数
        return process(w,v,0,bag);
    }
    private int process(int[] w,int[] v,int index,int rest){
        if (rest < 0){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        int p1 = process(w,v,index + 1,rest);//不算w[index]
        int p2 = 0;
        int next = process(w,v,index + 1,rest - w[index]);//算上w[index]
        if (next != -1){
            p2 = v[index] + next;
        }
        return Math.max(p1,p2);
    }

    public int maxValue2(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag < 1){
            return -1;//无效题设
        }
        //记忆化搜索（升级版的尝试函数）
        int[][] cache = new int[w.length+1][bag+1];//0~w.length-1,w.length;0~bag-1,bag
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                cache[i][j] = -1;
            }
        }
        return proWithCache(w,v,0,bag,cache);
    }
    private int proWithCache(int[] w,int[] v,int index,int rest,int[][] cache){
        if (cache[index][rest] != -1){
            return cache[index][rest];
        }
        if (rest < 0){
            return -1;
        }
        if (index == w.length){
            cache[index][rest] = 0;
            return cache[index][rest];
        }
        int p1 = process(w,v,index + 1,rest);
        int p2 = 0;
        if (rest >= w[index]){
            p2 = process(w,v,index + 1,rest - w[index]) + v[index];
        }
        cache[index][rest] = Math.max(p1,p2);
        return cache[index][rest];
    }

    public int maxValueDP(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag < 1){
            return -1;//无效题设
        }
        //经典动态规划，此题画表，可知从下往上，从左往右，最终结果是【0，bag】位于右上角
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int i = w.length; i >= 0 ; i--) {
            for (int j = 0; j <= bag; j++) {
                if (i == w.length){
                    dp[i][j] = 0;
                    continue;
                }
                int p1 = dp[i + 1][j];//不算w[index]
                int p2 = 0;
                if (j >= w[i]){
                    p2 = dp[i + 1][j - w[i]] + v[i];//算上w[index]
                }
                dp[i][j] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        Code06_Knapsack test = new Code06_Knapsack();

        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(test.maxValue1(weights, values, bag));
        System.out.println(test.maxValue2(weights,values,bag));
        System.out.println(test.maxValueDP(weights,values,bag));
    }
}
