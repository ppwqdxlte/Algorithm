package com.ppwqdxlte.basic.class14;

/**
 * @author:李罡毛
 * @date:2021/8/26 19:43
 * 【题目】求出两个字符串最长公共序列
 */
public class Code02_LongestCommonSubsequence {

    public int lcs1(String s1,String s2){
        if (s1 == null || s1 == null || s1.length() == 0 || s2.length() == 0){
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        //尝试
        return process1(str1,str2,str1.length - 1,str2.length - 1);
    }
    private int process1(char[] chars1,char[] chars2,int i,int j){
        boolean isEqual = chars1[i] == chars2[j];
        if (i == 0 && j == 0){
            return isEqual ? 1:0;
        }
        if (i == 0) return isEqual ? 1 : process1(chars1,chars2,i,j - 1);
        if (j == 0) return isEqual ? 1 : process1(chars1,chars2,i - 1,j);
        int p1 = process1(chars1,chars2,i - 1,j);
        int p2 = process1(chars1,chars2,i,j - 1);
        int p3 = chars1[i] == chars2[j] ? (1 + process1(chars1,chars2,i - 1,j - 1)) : 0;
        return Math.max(p1,Math.max(p2,p3));
    }

    public int lcs2(String s1,String s2){
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0){
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[][] dp = new int[chars1.length][chars2.length];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int j = 1; j < chars2.length; j++) {
            dp[0][j] = chars1[0] == chars1[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                /*dp[i][j] = dp[i - 1][j - 1];
                if (chars1[i] == chars2[j]){
                    dp[i][j] += 1;
                }*/
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = chars1[i] == chars2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[chars1.length - 1][chars2.length - 1];
    }
}
