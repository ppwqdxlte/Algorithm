package com.ppwqdxlte.basic.class03;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/11 13:38
 * 【提问】给定一个数组，X,Y是里面的元素，X位于Y的左边，且X>(Y<<1)，求这样的（X,Y）的次数
 */
public class Code04_BiggerThanRightTwice {
    public int getTimesOfBiggerThanRightTwice(int[] arr){
        if (arr == null || arr.length < 2) return 0;
        return process(arr,0,arr.length-1);
    }
    public int process(int[] arr,int L,int R){
        if (L == R) return 0;
        int M = L + ((R - L)>>1);
        return process(arr,L,M)
                +process(arr,M+1,R)
                +merge(arr,L,M,R);
    }
    public int merge(int[] arr,int L,int M,int R){
        // [L....M]   [M+1....R]
        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - M - 1;
        }

        int[] help = new int[R - L + 1];
        int i = R - L;
        int p1 = M;
        int p2 = R;
        while (p1 >= L && p2 >= M + 1){
            help[i--] = arr[p1] < arr[p2] ? arr[p2--] : arr[p1--];
        }
        while (p1 >= L){
            help[i--] = arr[p1--];
        }
        while (p2 >= M + 1){
            help[i--] = arr[p2--];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
        return ans;
    }
    //非递归方法
    public int getTimesOfBiggerThanRightTwice2(int[] arr){
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code04_BiggerThanRightTwice test = new Code04_BiggerThanRightTwice();
        int [] arr= {4,2,1,0,5,2};//5
        int ans = test.getTimesOfBiggerThanRightTwice(arr);
        System.out.println(ans);

        int maxSize = 100;
        int maxValue = 100;
        int testLoop = 10000;
        for (int i = 0; i < testLoop; i++) {
            int[] arrays = generateRandomIntArray(maxSize,maxValue);
            int[] arrays1 = copyIntArray(arrays);
            int ans1 = test.getTimesOfBiggerThanRightTwice(arrays);
            int ans2 = test.getTimesOfBiggerThanRightTwice2(arrays1);
            if (ans1 != ans2){
                System.out.println("Ooops!!");
                System.out.println(ans1+"\t"+ans2);
                break;
            }
        }
    }
}
