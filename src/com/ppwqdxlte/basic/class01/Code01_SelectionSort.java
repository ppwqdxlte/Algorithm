package com.ppwqdxlte.basic.class01;

import java.util.Arrays;

/**
 * @author:李罡毛
 * @date:2021/7/5 22:53
 * 选择排序，从下标看，从前到后依次确定值，先确定最小值，再确定次小值，以此类推，最后确定最大值
 * 【对数器】：随机生成数组，复制数组，用JDK传统排序和自己写的排序方法相对比，
 * 多次随机样本测试即可得出结果
 */
public class Code01_SelectionSort {

    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int i = 0;i < arr.length-1;i++){
            int minIndex = i;
            for (int j = i+1;j<arr.length;j++){
                minIndex = arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr, i, minIndex);
        }
    }
    /**
     * ^取反，定理1：符合交换律和结合律，
     *       定理2：0^N == N,且 N^N == 0;
     * 只能实现两个不同地址的值交换，交换相同内存地址的值会出错
     * 【异或可以看成：无进位相加！！】
     */
    private static void swap(int[] arr, int i1, int i2) {
        if (i1 == i2 || arr[i1] == arr[i2]) return;
        arr[i1] = arr[i1] ^ arr[i2];
        arr[i2] = arr[i1] ^ arr[i2];
        arr[i1] = arr[i1] ^ arr[i2];
    }

    /**
     * Math.random()   [0,1)
     * Math.random() * N  [0,N)
     * (int)(Math.random() * N)  [0, N-1]
     */
    public static int[] generateRandomIntArray(int maxSize,int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0;i < arr.length;i++){
            // [-? , +?]
            arr[i] = (int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testLoop = 100000;
        boolean isSuccess = true;
        for (int i = 0;i < testLoop; i++){
            int[] arr1 = generateRandomIntArray(maxSize,maxValue);
            int[] arr2 = getCopyOfIntArray(arr1);
            selectionSort(arr1);
            jdkIntArraySort(arr2);
            if (!isEqual(arr1,arr2)){
                isSuccess = false;
                printIntArray(arr1);
                printIntArray(arr2);
                break;
            }
        }
        System.out.println(isSuccess?"OK,no problem":"Failed!");

    }
    /**
     * Java JDK 默认排序方法
     * for test
     */
    private static void jdkIntArraySort(int[] arr2) {
        Arrays.sort(arr2);
    }

    private static int[] getCopyOfIntArray(int[] arr) {
        if (arr == null) return null;
        int[] arr2 = new int[arr.length];
        for (int i = 0;i < arr2.length;i++){
            arr2[i] = arr[i];
        }
        return arr2;
    }

    private static boolean isEqual(int[] arr1,int[] arr2){
        if (arr1 == null && arr2 != null || arr2 == null && arr1 != null) return false;
        if (arr1 == null && arr2 == null) return true;
        if (arr1.length != arr2.length) return false;
        for (int i = 0;i < arr1.length;i++){
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private static void printIntArray(int[] arr){
        if (arr == null) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }
}
