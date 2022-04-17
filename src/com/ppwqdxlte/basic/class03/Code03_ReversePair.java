package com.ppwqdxlte.basic.class03;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.copyIntArray;
import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.generateRandomIntArray;

/**
 * @author:李罡毛
 * @date:2021/7/11 0:40
 * 【提问】求一个数组的所有逆序对的个数 X,Y 前后位置不变，且 X > Y
 */
public class Code03_ReversePair {

    public int getReversePair(int[] arr){
        if (arr == null || arr.length < 2) return 0;
        return process(arr,0,arr.length - 1);
    }
    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量
    public int process(int[] arr,int L,int R){
        if (L == R) return 0;
        int M = L + ((R - L) >> 1);
        return process(arr,L,M)
                +process(arr,M+1,R)
                +merge(arr,L,M,R);
    }
    public int merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        int p1 = M;
        int p2 = R;
        int res = 0;
        while (p1 >= L && p2 >= M+1){
            res += arr[p1] > arr[p2] ? (p2 - M):0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--]:arr[p2--];
        }
        while (p1 >= L){
            help[i--] = arr[p1--];
        }
        while (p2 >= M+1){
            help[i--] = arr[p2--];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code03_ReversePair test = new Code03_ReversePair();
        int maxSize = 50;
        int maxValue = 100;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomIntArray(maxSize,maxValue);
            int[] arr1 = copyIntArray(arr);
            int sum = test.getReversePair(arr);
            int sum1 = test.comparator(arr1);
            /*if (i<100){
                System.out.println(sum);
                System.out.println(sum1);
            }*/
            if (sum != sum1){
                isSuccess = false;
                break;
            }
        }
        //有时候光跑通都不行，还得打印出来一部分数值，对比后没问题才行
        System.out.println(isSuccess?"OK,no problem!":"Ooops!!!");
    }

}
