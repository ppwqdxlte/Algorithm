package com.ppwqdxlte.basic.class03;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/10 23:14
 * 【提问】求出一个数组中的所有小和，
 * “小和”是什么？数组中的某个元素，它左边所有比它小的元素之和，就叫【小和】
 */
public class Code02_SmallSum {

    public int getSmallSum(int[] arr){
        if (arr == null || arr.length < 2) return 0;
        return process(arr,0,arr.length - 1);
    }
    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    public int process(int[] arr,int L,int R){
        if (L == R) return 0;
        int M = L + ((R - L)>>1);
        return process(arr,L,M)
                +process(arr,M+1,R)
                +merge(arr,L,M,R);
    }
    public int merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;//result;
        while (p1 <= M && p2 <= R) {
            //小和 要是右边先越界，那停留在左边的指针的值肯定比右边大了，因为
            // 哪怕左边和右边相等，也要先紧着左边先放进help[]里并下移左边指针
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }
    
    public int comparator(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[i] > arr[j]?arr[j]:0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Code02_SmallSum test = new Code02_SmallSum();
        int maxSize = 50;
        int maxValue = 100;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomIntArray(maxSize,maxValue);
            int[] arr1 = copyIntArray(arr);
            int sum = test.comparator(arr);
            int sum1 = test.getSmallSum(arr1);
            if (sum != sum1){
                isSuccess = false;
                break;
            }
            /*if (i<100){
                System.out.println(sum);
                System.out.println(sum1);
            }*/
        }
        //有时候光跑通都不行，还得打印出来一部分数值，对比后没问题才行
        System.out.println(isSuccess?"OK,no problem!":"Ooops!!!");
    }
}
