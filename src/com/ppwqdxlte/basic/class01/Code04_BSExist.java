package com.ppwqdxlte.basic.class01;

/**
 * @author:李罡毛
 * @date:2021/7/6 21:56
 * 二分查找：
 * 【问题】：一个数组中是否包含某个数，
 * 【注意】二分查找法找的的下标，可能不是全部的符合下标！
 * main方法中测试，样本量很小时候，错误代码也能通过测试，样本量大一些时候，就发现
 * 代码逻辑错误了！
 */
public class Code04_BSExist {
    private static int binarySearchExist(int[] sortedArr,int value){
        if (sortedArr == null || sortedArr.length == 0) return -1;
        int l = 0;
        int r = sortedArr.length-1;
        int mid = 0; //中位数
        while (l<r){
            mid = l + ((r - l)>>1);
            if (sortedArr[mid]< value){
                l = mid +1;
            }else if (sortedArr[mid] > value){
                r = mid -1;
            } else {
                return mid;
            }
        }
        return sortedArr[l] == value?l:-1;
    }

    private static void insertionSort(int[] arr){
        if (arr == null || arr.length == 0) return;
        for (int i = 1; i < arr.length ; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1] ; j--) {
                swap(arr,j,j+1);
            }
        }
    }
    private static void swap(int[] arr,int i1,int i2){
        if (arr == null || i1 == i2 ||
                i1 >= arr.length || i2 >= arr.length) return;
        arr[i1] = arr[i1]^arr[i2];
        arr[i2] = arr[i1]^arr[i2];
        arr[i1] = arr[i1]^arr[i2];
    }

    private static int jdkArraysSearchExist(int[] sortedArr,int value){
        if (sortedArr == null || sortedArr.length == 0) return -1;
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] == value) return i;
        }
        return -1;
    }

    public static int[] generateRandomIntArray(int maxSize,int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0;i < arr.length;i++){
            // [-? , +?]
            arr[i] = (int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testLoop = 1000;
        int countOfExist = 0;
        int countOfNotFound = 0;
        boolean isFailed = false;
        for (int i = 0; i < testLoop; i++) {
            int[] arr = generateRandomIntArray(maxSize,maxValue);
            insertionSort(arr);
            int randomValue = (int)((maxValue+1)*Math.random());
            int existIndex = binarySearchExist(arr,randomValue);
            int existIndex2 = jdkArraysSearchExist(arr,randomValue);
            /*if (existIndex != existIndex2){
                System.out.println("Failed!!!");
                isFailed = true;
                printIntArray(arr);
                System.out.println("此次随机数为："+randomValue
                        +"\n二分查找结果："+existIndex
                        +"\n普通遍历查找结果："+existIndex2);
//                break;
            }
            if (existIndex == -1 && existIndex2 != -1) System.out.println("????");*/
            if (existIndex != -1){
                countOfExist++;
                printIntArray(arr);
                System.out.println(randomValue+"\n==================");
            } else {
                countOfNotFound++;
                if (countOfNotFound <=10){
                    printIntArray(arr);
                    System.out.println(randomValue+"\n@@@@@@@@@@@@@@@@@@@@@@@");
                }
            }
        }
//        System.out.println(!isFailed?"Success!!":"");
//        System.out.println("本次实验一共找到"+countOfExist+"次。");
    }

    private static void printIntArray(int[] arr){
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }
}
