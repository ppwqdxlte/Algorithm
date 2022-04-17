package com.ppwqdxlte.basic.class03;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/11 16:57
 * 【分区与快排】
 */
public class Code06_PartitionAndQuickSort {
    /**arr[L..R]上，以arr[R]位置的数做划分值
     * @param arr
     * @param L
     * @param R
     * @return <= arr[R]最右边的下标
     */
    public int partition(int[] arr,int L,int R){
        if (L > R) return -1;
        if (L == R) return L;
        int lessEqual = L - 1;
        int i = L;
        while (i < R){
            if (arr[i] <= arr[R]){
                swap(arr,i,++lessEqual);
            }
            i++;
        }
        swap(arr,++lessEqual,R);
        return lessEqual;
    }
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**荷兰三色国旗分区
     * @param arr
     * @param L
     * @param R
     * @return 分区后==arr[R]的下标闭区间
     */
    public int[] netherlandsFlag(int[] arr,int L,int R){
        if (L > R) return new int[]{-1,-1};
        if (L == R) return new int[]{L,L};
        int less = L - 1;//< arr[R]左区的右边界
        int more = R;//> arr[R]右区的左边界，arr[R]作旁观者，故而R暂时为边界
        int i = L;
        while (i < more){//不超过 右区边界
            if (arr[i] == arr[R]){
                i++;
            }else if (arr[i] < arr[R]){
                swap(arr,i++,++less);
            }else {
                swap(arr,i,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less + 1,more};
    }

    /**快排 1.0
     * @param arr
     */
    public void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process1(arr, 0, arr.length - 1);
    }
    public void process1(int[] arr,int L,int R){
        if (arr == null || arr.length < 2) return;
        if (L >= R) return;
        int M = partition(arr,L,R);
        process1(arr,L,M-1);//如果覆盖住M,那么会发生栈溢出异常
        process1(arr,M+1,R);
    }
    /**快排 2.0
     * @param arr
     */
    public void quickSort2(int[] arr){
        if (arr == null || arr.length < 2) return;
        process2(arr, 0, arr.length - 1);
    }
    public void process2(int[] arr,int L,int R){
        if (L >= R) return;
        int[] range = netherlandsFlag(arr,L,R);
        process2(arr,L,range[0]-1);
        process2(arr,range[1]+1,R);
    }
    /**快排 3.0
     * @param arr
     */
    public void quickSort3(int[] arr){
        if (arr == null || arr.length < 2) return;
        process3(arr, 0, arr.length - 1);
    }
    public void process3(int[] arr,int L,int R){
        if (L >= R) return;
        /*
        //这是我自己想的方法
        int[] range = netherlandsFlag2(arr,L,R);
        process2(arr,L,range[0]-1);
        process2(arr,range[1]+1,R);*/

        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }
    public int[] netherlandsFlag2(int[] arr,int L,int R){
        if (L > R) return new int[]{-1,-1};
        if (L == R) return new int[]{L,L};
        int randIndex = L + (int) ((R - L + 1)*Math.random());
        int less = L - 1;
        int more = R + 1;
        int i = L;
        while (i <= R){
            if (arr[i] == arr[randIndex]){
                i ++;
            }else if (arr[i] < arr[randIndex]){
                swap(arr,i++,++less);
            }else if (arr[i] > arr[randIndex]){
                swap(arr,i,--more);
            }
        }
        return new int[]{less + 1,more - 1};
    }

    // for test
    public static void main(String[] args) {
        Code06_PartitionAndQuickSort test = new Code06_PartitionAndQuickSort();
        //Test partition() and netherlandsFlag()
        int[] arr00 = {10,23,5,23,67,3,7,15};
        int[] arr01 = copyIntArray(arr00);
        int lessThan = test.partition(arr01,0,arr01.length-1);
        System.out.println(lessThan);
        printIntArray(arr01);
        System.out.println("*****************************");
        int[] arr02 = copyIntArray(arr00);
        int[] netherlandsFlag = test.netherlandsFlag(arr02,0,arr02.length-1);
        printIntArray(netherlandsFlag);
        printIntArray(arr02);
        //Test quickSort1
        int[] arr03 = copyIntArray(arr00);
        test.quickSort1(arr03);
        printIntArray(arr03);
        //Test quickSort2
        int[] arr04 = copyIntArray(arr00);
        test.quickSort1(arr04);
        printIntArray(arr04);
        //Test quickSort3
        int[] arr05 = copyIntArray(arr00);
        test.quickSort1(arr05);
        printIntArray(arr05);


        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomIntArray(maxSize, maxValue);
            int[] arr2 = copyIntArray(arr1);
            int[] arr3 = copyIntArray(arr1);
            test.quickSort1(arr1);
            test.quickSort2(arr2);
            test.quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
