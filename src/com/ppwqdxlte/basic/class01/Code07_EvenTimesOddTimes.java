package com.ppwqdxlte.basic.class01;

/**
 * @author:李罡毛
 * @date:2021/7/7 16:18
 * 【题目】一个数组中只有若干种数出现了奇数次，其它数都出现偶数次，怎么找到并打印这个（些）数？
 */
public class Code07_EvenTimesOddTimes {
    /**
     * 只有一种数出现奇数次
     */
    private static void getTheOddNumber(int[] oddEvenArr){
        int exr = 0;
        for (int i = 0; i < oddEvenArr.length; i++) {
            exr ^= oddEvenArr[i];
        }
        System.out.println(exr);
    }
    /**
     * 有2种数出现了奇数次，其它数都是偶数次
     */
    private static void getTwoOddNumbers(int[] oddEvenArr){
        int eor = 0;
        for (int i = 0; i < oddEvenArr.length; i++) {
            eor^=oddEvenArr[i];
        }
        int eor2 = eor&((~eor)+1);
        int a = 0;
        int b = 0;
//        System.out.println(eor2);
        for (int i = 0; i < oddEvenArr.length; i++) {
            if ((eor2&oddEvenArr[i]) == eor2 ){
//                System.out.println("1类：："+oddEvenArr[i]);
                a ^= oddEvenArr[i];
            }else {
//                System.out.println("2类：："+oddEvenArr[i]);
                b ^= oddEvenArr[i];
            }
        }
        System.out.println(a+"\t"+b);
    }
    /**
     * 上述方法的改良版，利用N^N == 0 化解掉一个odd获得另一个odd
     */
    private static void getTwoOddNumbers2(int[] arr){
        if (arr == null || arr.length < 2) return;
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor^=arr[i];
        }
        int rightOne = eor&((~eor)+1);//获得最右边的1，其余各位都0，1所在的位上区分了a和b的不同
        int oddA = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOne&arr[i]) != 0) {
                oddA^=arr[i];
            }
        }
        System.out.println(oddA+"\t"+(oddA^eor));
    }
    /**
     * 一个数转化成二进制后，bit位上是1的次数
     */
    private static void bit1Counts(int value){
        int count = 0;
        int oldValue = value;
        while (value != 0){
            value ^= value&((~value)+1);//不进位相加，去掉value中最右边的1
            count++;
        }
        System.out.println(oldValue+"的二进制形式包含了"+count+"个1.");
    }
    public static void main(String[] args) {
        int[] oddEvenArr1 = {1,1,1,1,0,0,9,9,5,5,5,8,8,8,8,6,6,6,6,6,6};
        getTheOddNumber(oddEvenArr1);

        int[] oddEvenArr2 = {6,6,6,3,3,3,3,2,2,7,7,7,7,7,7,8,8,8,0,0};
        getTwoOddNumbers(oddEvenArr2);
        getTwoOddNumbers2(oddEvenArr2);

        int n = -65535;
        bit1Counts(n);
    }
}
