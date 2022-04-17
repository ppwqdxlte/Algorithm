package com.ppwqdxlte.basic.class01;

import java.util.Arrays;

/**
 * @author:李罡毛
 * @date:2021/7/6 13:26
 * 冒泡排序，从末尾往数组头的方向确定值的顺序,就是先确定最大值，再确定次大值，依此类推，最后确定最小值
 */
public class Code02_BubbleSort {
    private static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2 )return;
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) swap(arr,j,j+1);
            }
        }
    }

    private static void swap(int[] arr, int i1, int i2) {
        if (arr == null || i1 == i2 ||
                i1 >= arr.length || i2 >= arr.length) return;
        arr[i1] = arr[i1]^arr[i2];
        arr[i2] = arr[i1]^arr[i2];
        arr[i1] = arr[i1]^arr[i2];
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testLoop = 1000000;
        boolean isSuccess = true;
        for (int i = 0; i < testLoop; i++) {
            int[] arr1 = getRandomIntArray(maxSize,maxValue);
            int[] arr2 = getCopyOfIntArray(arr1);
            bubbleSort(arr1);
            jdkArraySort(arr2);
            if (!isEqual(arr1,arr2)){
                isSuccess = false;
                printIntArray(arr1);
                printIntArray(arr2);
                break;
            }
        }
        System.out.println(isSuccess?"No problem!":"Failed!");
    }

    private static void printIntArray(int[] arr1) {
        if (arr1 == null) return;
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] +"\t");
        }
        System.out.println();
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) return true;
        if (arr1 == null && arr2 != null || arr1 != null && arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private static void jdkArraySort(int[] arr2) {
        Arrays.sort(arr2);
    }

    private static int[] getCopyOfIntArray(int[] arr1) {
        if (arr1 == null) return null;
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }

    private static int[] getRandomIntArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
        }
        return arr;
    }
}
