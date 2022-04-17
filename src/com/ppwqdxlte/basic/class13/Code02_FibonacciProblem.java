package com.ppwqdxlte.basic.class13;

/**
 * @author:李罡毛
 * @date:2021/8/21 15:43
 * 【斐波那契数列】问题：求第n个是谁
 * 1,1,2,3,5,8,13,21......
 * 左程云说：
 * 把【参数组合】玩儿成【结构化的缓存】，就是动态规划
 * 常见的4种【尝试模型】：
 *      1)从左往右的尝试模型；        比如 背包问题
 *      2)范围上的尝试模型；         比如 2个人博弈取牌
 *      3)多样本位置全对应的尝试模型     比如 N皇后、斐波那契的某种解法
 *      4)寻找业务限制的尝试模型       比如 分支限界
 *
 * 总结：题👉【暴力递归】写法（尝试）👉如果有重复解，就可以改成动态规划👉可变参数，不讲究组织只作傻缓存，
 * 【记忆化搜索】(又名“自顶向下的递归”)的方法👉若要把记忆化缓存结构作精细化组织，就是【经典动态规划】
 * 三者的时间复杂度关系？拿背包问题来说，暴力递归挨个试一一遍 O(2^N),
 *          经典动态规划 O(N*bag)二维表的长宽乘积；
 * 如果决策过程中，无枚举行为（即任何一个状态只依赖有限若干个子状态），
 *      那么记忆化搜索和经典动态规划具有等效的时间复杂度，
 * 但是如果表中某个位置有枚举行为，那么记忆化搜索就必须得改经典动态规划了！
 * 决策过程有枚举的经典题型就是：
 *      【新题】一个数组arr,元素是货币面值，每种面值可以使用任意张！
 *      arr中都是正数，且不重复，aim= 1000,用这些面值，用多少种方法搞出来这个aim?
 */
public class Code02_FibonacciProblem {
    //暴力递归【没有去重，会有重复的计算】 O(N)
    public int f1(int n){
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        return f1(n - 1) + f1(n - 2);
    }
    //自然尝试，非递归 O(N)
    public int f2(int n){
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 2; i < n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        Code02_FibonacciProblem test = new Code02_FibonacciProblem();

        int n = 19;
        System.out.println(test.f1(n));
        System.out.println(test.f2(n));
//        System.out.println(test.f3(n));
//        System.out.println("===");
//
//        System.out.println(test.s1(n));
//        System.out.println(test.s2(n));
//        System.out.println(test.s3(n));
//        System.out.println("===");
//
//        System.out.println(test.c1(n));
//        System.out.println(test.c2(n));
//        System.out.println(test.c3(n));
//        System.out.println("===");
    }
}
