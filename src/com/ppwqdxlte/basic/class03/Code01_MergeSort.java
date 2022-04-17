package com.ppwqdxlte.basic.class03;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/10 20:46
 * 【并归排序】
 */
public class Code01_MergeSort {
    //并归排序
    public void mergeSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        process(arr,0,arr.length-1);
    }
    // 请把arr[L..R]排有序
    // l...r N
    // T(N) = 2 * T(N / 2) + O(N)
    // O(N * logN)
    public void process(int[] arr,int L,int R){
        if (L == R) return;
        int M = L + ((R - L)>>1);
        process(arr,L,M);
        process(arr,M+1,R);
        merge(arr,L,M,R);
    }
    public void merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
    }
    //非递归de合并排序 复杂度同上
    public void mergeSort2(int[] arr,int L,int R){
        if (arr == null || arr.length < 2) return;
        int mergeSize = 1;//步长，合并排序嘛，分左右两边，表示左边子数组的size
        int N = R - L + 1;//样本量
        while (mergeSize < N){
            //虽然每次步长不同，但都从头开始
            int left = L;
            while (left <= R){
                if (mergeSize >= N - left ) break;// (mid=left+mergeSize-1)<N-1才行
                int mid = left + mergeSize - 1;
                int right = Math.min(mid + mergeSize,N - 1);
                merge(arr,left,mid,right);
                left = right + 1;
            }
            if (mergeSize > N>>1) break;//看似多此一举，实防int溢出
            mergeSize <<= 1;
        }
    }
    public static void main(String[] args) {
        Code01_MergeSort testMergeSort = new Code01_MergeSort();
        int maxSize = 50;
        int maxValue = 100;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomIntArray(maxSize,maxValue);
            int[] arr1 = copyIntArray(arr);
            int[] arr2 = copyIntArray(arr);
            /*if (i < 3){
                printIntArray(arr);
                printIntArray(arr1);
                printIntArray(arr2);
                System.out.println("\n\n\n");
            }*/
            testMergeSort.mergeSort(arr1);
            testMergeSort.mergeSort2(arr2,0,arr2.length-1);
            if (!isEqual(arr1,arr2)){
                isSuccess = false;
                break;
            }
           /* if (i < 3){
                printIntArray(arr);
                printIntArray(arr1);
                printIntArray(arr2);
                System.out.println("\n\n\n");
            }*/
        }
        //有时候光跑通都不行，还得打印出来一部分数值，对比后没问题才行
        System.out.println(isSuccess?"OK,no problem!":"Ooops!!!");
    }

}
