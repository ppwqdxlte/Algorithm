package com.ppwqdxlte.basic.class13;

/**
 * @author:李罡毛
 * @date:2021/8/21 16:41
 * 机器人走路，1~N个位置，从 start 位置走 K 步到达 aim 位置，
 * 从1往下走只能到2，从N往下走只能到N-1，求问几种走法？
 */
public class Code03_RobotWalk {
    /**没加缓存的暴力递归，有重复计算
     * @param N 位置---固定
     * @param start 起始位置
     * @param aim 目标位置
     * @param K 可以走几步
     * @return 从start走K步到aim共有几种走法
     */
    public int ways1(int N,int start,int aim,int K){
        if (N < 2 || K < 1 || start < 1 || start > N || aim < 1 || aim > N){
            return -1;
        }
        return walk1(N,start,aim,K);
    }
    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    private int walk1(int N,int cur,int aim,int rest){
        if (rest == 0){
            return cur == aim ? 1 : 0;
        }
        if (cur == 1){
            return walk1(N,2,aim,rest - 1);
        }
        if (cur == N){
            return walk1(N,N - 1,aim,rest - 1);
        }
        return walk1(N,cur - 1,aim,rest - 1)
                + walk1(N,cur + 1,aim,rest - 1);
    }
    /**动态规划，加了缓存，避免了重复计算，
     * 这个方法用了最简单的动态规划方法之：【记忆化搜索】
     * @param N 总位置
     * @param start 起始位置
     * @param aim 目标位置
     * @param K 可以走几步
     * @return 从起始位置到终点位置走K步会有几种走法
     */
    public int ways2(int N,int start,int aim,int K){
        if (N < 2 || K < 1 || start < 1 || start > N || aim < 1 || aim > N){
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];//0位置不算，1~N，0~K齐活儿
        for (int cur = 0; cur < dp.length; cur++) { //dp.length == N + 1
            for (int rest = 0; rest < dp[0].length; rest++) {//dp[0].length == K + 1
                dp[cur][rest] = -1;//表示都没动过
            }
        }
        return walk2(N,start,aim,K,dp);
    }
    private int walk2(int N,int cur,int aim,int rest,int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        // 之前没算过！
        int ans = 0;
        if (rest == 0){
            ans = cur == aim ? 1: 0;
        }else if (cur == 1){
            ans = walk2(N,2,aim,rest - 1,dp);
        }else if (cur == N){
            ans = walk2(N,N - 1,aim,rest - 1,dp);
        }else {
            ans = walk2(N,cur - 1,aim,rest -1,dp)
                    + walk2(N,cur + 1,aim,rest - 1,dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**动态规划的非递归方式，所谓的
     * 左程云说：【动态规划转移方程】其实就是你决策过程中的那个决策，
     *      暴力尝试过程中怎么组织你那个决策的，它就是动态规划中的状态转移
     */
    public int ways3(int N,int start,int K,int aim){
        if (N < 2 || K < 1 || start < 1 || start > N || aim < 1 || aim > N){
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;//初始化，默认能走到，且只有一条路径
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest -1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        Code03_RobotWalk test =  new Code03_RobotWalk();
        System.out.println(test.ways1(5,2,4,6));
        System.out.println(test.ways2(5,2,4,6));
        System.out.println(test.ways3(5,2,6,4));
    }
}
