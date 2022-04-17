package com.ppwqdxlte.basic.class02;
/**
 *
 * 递归复杂度模型之一：
 *
 *      T(N) = a*T(N/b) + O(N^d);
 *
 *          N__________样本量
 *          a__________子递归重复了几次
 *          b__________子递归的样本量
 *          d__________当前递归中除了子递归的代码，其它代码的复杂度
 *      那最终怎么计算T(N)的复杂度呢？TODO:怎么算出来的？
 *          1)log(b)a > N^d     O(N^(log(b)a)   以b为底的a的对数 N的d次幂
 *          2)log(b)a < N^d     O(N^d)
 *          3)log(b)a == N^d    O((N^d)*logN)
 *
 * HashMap HashSet 底层原理差不多，
 *
 * 【定理】哈希表 增删改查 ，都是O(1)
 */