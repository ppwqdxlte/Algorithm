package com.ppwqdxlte.basic.class02;

/**
 * @author:李罡毛
 * @date:2021/7/9 21:54
 * 递归行为，求出 L...R 范围上的最大值
 */
public class Code08_GetMax {

    // 求arr中的最大值
    public int getMax(int[] arr){
        return process(arr,0,arr.length-1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public int process(int[] arr,int L,int R){
        if (L == R) return arr[L];
        int mid = L + ((R - L)>>1);
        int maxLeft = process(arr,L,mid);
        int maxRight = process(arr,mid+1,R);
        return Math.max(maxLeft,maxRight);
    }

    public static void main(String[] args) {
        int[] arr = {1234,53,34,6,3234,64,21,10000,23,9999,100};
        Code08_GetMax test = new Code08_GetMax();
        System.out.println(test.getMax(arr));
        System.out.println(test.process(arr,5,7));
    }
}
