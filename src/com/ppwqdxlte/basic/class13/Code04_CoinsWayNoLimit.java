package com.ppwqdxlte.basic.class13;

/**
 * @author:李罡毛
 * @date:2021/8/24 12:09
 * 新题：一个数组arr,元素是货币面值，每种面值可以使用任意张！
 * arr中都是正数，且不重复，aim= 1000,用这些面值，用多少种方法搞出来这个aim?
 */
public class Code04_CoinsWayNoLimit {

    public int coinsWay(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process(arr,0,aim);
    }
    // arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
    private int process(int[] arr,int index,int rest){
        if (index == arr.length){//没钱了
            return rest == 0 ? 1:0;
        }
        int ways = 0;
        for (int piece = 0; piece * arr[index] <= rest ; piece++) {
            ways += process(arr,index + 1,rest - piece * arr[index]);
        }
        return ways;
    }

    public int coinWaysWithCache(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] cache = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                cache[i][j] = -1;
            }
        }
        return proWithCache(arr,0,aim,cache);
    }
    private int proWithCache(int[] arr,int index,int rest,int[][] cache){
        if (cache[index][rest] != -1){
            return cache[index][rest];
        }
        if (index == arr.length){
            cache[index][rest] = rest == 0 ? 1 : 0;
            return cache[index][rest];
        }
        cache[index][rest] = 0;
        for (int i = 0; i*arr[index] <= rest ; i++) {
            cache[index][rest] += process(arr,index + 1,rest - i*arr[index]);
        }
        return cache[index][rest];
    }

    public int dp1(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                for (int i = 0; i*arr[index] <= rest ; i++) {
                    dp[index][rest] += dp[index + 1][rest - (i*arr[index])];
                }
            }
        }
        return dp[0][aim];
    }

    public int dp2(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= arr[index])
                dp[index][rest] += dp[index][rest - arr[index]];
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Code04_CoinsWayNoLimit test = new Code04_CoinsWayNoLimit();

        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = test.coinsWay(arr, aim);
            int ans2 = test.dp1(arr, aim);
            int ans3 = test.dp2(arr, aim);
            int ans4 = test.coinWaysWithCache(arr,aim);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("aim is : " + aim);
                System.out.println(ans1+"\t"+ans2+"\t"+ans3+"\t"+ans4);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
