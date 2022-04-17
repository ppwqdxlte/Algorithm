package com.ppwqdxlte.basic.class03;

import java.util.HashSet;
import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/11 15:09
 * 【提问】 一个数组，再给一个闭区间[x,y]，数组里面符合此闭区间的所有区间和有几种？
 * 比如[-2,5,-1] 闭区间为[-2,2]，那么符合此区间的数组的区间(x,y)都有：
 * 注意：x和y顺序不变，且x可以等于y
 *              [0,0],[0,2],[2,2]
 *       分别对应的数组元素为：
 *              [-2,-2],[-2,5,-1],[-1,-1]
 */
public class Code05_CountOfRangeSum {
    public int getCountOfRangeSum(int [] arr,int lower,int upper){
        if (arr == null || arr.length == 0) return 0;
        HashSet rangeSums = new HashSet();
        process(arr,0,arr.length-1,lower,upper,rangeSums);
        return rangeSums.size();
    }
    //不必排序
    public void process(int[] arr,int L,int R,int lower,int upper,HashSet rangeSums){
        if (arr == null || arr.length == 0) return;
        if (L == R) {
            merge(arr,L,L,L,lower,upper,rangeSums);
            return;
        }
        int M = L + ((R - L)>>1);
        process(arr,L,M,lower,upper,rangeSums);
        process(arr,M+1,R,lower,upper,rangeSums);
        merge(arr,L,M,R,lower,upper,rangeSums);
    }
    public void merge(int[] arr,int L,int M,int R,int lower,int upper,HashSet rangeSums){
        if (L == R) {
            if (arr[L] >= lower && arr[L] <= upper){
                rangeSums.add(arr[L]*2);
            }
            return;
        }
        for (int i = L; i <= M; i++) {
            for (int j = M + 1; j <= R; j++) {
                if (arr[i] >= lower && arr[j] <= upper && arr[i] <= arr[j]){
                    int rangeSum = 0;
                    for (int k = i; k <= j; k++) {
                        rangeSum += arr[k];
                    }
                    rangeSums.add(rangeSum);
                }
            }
        }
    }

    public int comparator(int[] arr,int lower,int upper){
        if (arr == null || arr.length == 0) return 0;
        HashSet rangeSums = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] >= lower && arr[j] <= upper && arr[i] <= arr[j]){
                    int rangeSum = 0;
                    for (int k = i; k <= j; k++) {
                        if (i == j) rangeSum += arr[k];//如果不重复加一次，就少了j == i的情形
                        rangeSum += arr[k];
                    }
//                    System.out.println(rangeSum);
                    rangeSums.add(rangeSum);
                }
            }
        }
        return rangeSums.size();
    }

    public static void main(String[] args) {
        Code05_CountOfRangeSum test = new Code05_CountOfRangeSum();
        int[] arr0 = {-2,5,-1};//-4,2,-2
        int[] arr01 = {3,2,1,5,0,4};//4,2,0
        System.out.println(test.comparator(arr0,-2,1));
        System.out.println(test.getCountOfRangeSum(arr0,-2,1));
        System.out.println("----");
        System.out.println(test.comparator(arr01,-1,1));
        System.out.println(test.getCountOfRangeSum(arr01,-1,1));

        int maxSize = 100;
        int maxValue = 100;
        int testLoop = 10000;
        for (int i = 0; i < testLoop; i++) {
            int[] arrays = generateRandomIntArray(maxSize,maxValue);
            int[] arrays1 = copyIntArray(arrays);
            int randLower = (int) (2*(maxValue+1)*Math.random())-(int) (2*(maxValue+1)*Math.random());
            int randUpper = randLower + (int) (2*(maxValue+1)*Math.random());
            int ans1 = test.comparator(arrays,randLower, randUpper);
            int ans2 = test.getCountOfRangeSum(arrays1,randLower,randUpper);
            if (ans1 != ans2){
                System.out.println("Ooops!!");
                System.out.println(ans1+"\t"+ans2);
                break;
            }
        }
    }
}
