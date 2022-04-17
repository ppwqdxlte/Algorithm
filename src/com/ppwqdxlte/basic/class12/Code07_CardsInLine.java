package com.ppwqdxlte.basic.class12;

/**
 * @author:李罡毛
 * @date:2021/8/19 10:24、
 * 一排扑克，两个人依次取牌，每次只能从两头取，且都是聪明人，先手肯定选最有利于自己的方案
 */
public class Code07_CardsInLine {
    // 根据规则，返回获胜者的分数
    public int win1(int[] arr){
        return Math.max(
                f(arr,0,arr.length - 1),
                s(arr,0,arr.length - 1));
    }
    // arr[L..R]，先手获得的最好分数返回
    private int f(int[] arr,int L,int R){
        if (R == L){
            return arr[L];
        }
        return Math.max(arr[L] + s(arr,L + 1,R), arr[R] + s(arr,L,R - 1));
    }
    // arr[L..R]，后手获得的最好分数返回
    private int s(int[] arr,int L,int R){
        if (R == L){
            return 0;
        }
        return Math.min(
                f(arr,L + 1,R), // 对手拿走了L位置的数
                f(arr,L,R - 1));// 对手拿走了R位置的数
    }

    public int win2(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] smap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                smap[i][j] = -1;
            }
        }
        return Math.max(
                f2(arr,0,N - 1,fmap,smap),
                s2(arr,0,N - 1,fmap,smap));
    }
    private int f2(int[] arr,int L,int R,int[][] fmap,int[][] smap){
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R){
            ans = arr[L];
        }else {
            ans = Math.max(
                    arr[L] + s2(arr,L + 1,R,fmap,smap),
                    arr[R] + s2(arr,L,R - 1,fmap,smap));
        }
        fmap[L][R] = ans;
        return ans;
    }
    private int s2(int[] arr,int L,int R,int[][] fmap,int[][] smap){
        if (smap[L][R] != -1){
            return smap[L][R];
        }
        int ans = 0;
        if (R == L){
            ans = 0;
        }else {
            ans = Math.min(
                    f2(arr,L + 1,R,fmap,smap),
                    f2(arr,L,R - 1,fmap,smap));
        }
        smap[L][R] = ans;
        return ans;
    }

    public int win3(int[] arr){
        int[][] dpf = new int[arr.length][arr.length];
        int[][] dps = new int[arr.length][arr.length];
        for (int L = dpf.length - 1; L >= 0; L--) {
            for (int R = 0; R < dps.length; R++) {
                if (R < L){//隐藏条件
                    dpf[L][R] = -1;
                    dps[L][R] = -1;
                    continue;
                }
                if (R == L){
                    dpf[L][R] = arr[L];
                    dps[L][R] = 0;
                    continue;
                }
                dpf[L][R] = Math.max(arr[L] + dps[L + 1][R],arr[R] + dps[L][R - 1]);
                dps[L][R] = Math.min(dpf[L + 1][R],dpf[L][R - 1]);
            }
        }
        return Math.max(dpf[0][arr.length - 1],dps[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        Code07_CardsInLine test = new Code07_CardsInLine();

        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(test.win1(arr));
        System.out.println(test.win2(arr));
        System.out.println(test.win3(arr));
    }
}
