package com.ppwqdxlte.basic.class14;

import java.util.HashMap;

/**
 * @author:李罡毛
 * @date:2021/8/26 19:42
 */
public class Code01_StickersToSpellword {

    public int minStickers1(String[] stickers,String target){
        int ans = process1(stickers,target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // 所有贴纸stickers，每一种贴纸都有无穷张
    // target
    // 返回 ：最少张数
    private int process1(String[] stickers,String target){
        if (target.length() == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target,first);
            if (rest.length() != target.length()){
                min = Math.min(min,process1(stickers,rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }
    private String minus(String target,String sticker){
        /*
        //普通方法
        String[] stickers = sticker.split("");
        String[] tarC = target.split("");
        for (int i = 0; i < tarC.length; i++) {
            for (int j = 0; j < stickers.length; j++) {
                if (tarC[i].equals(stickers[j])){
                    tarC[i] = "";
                    stickers[j] = "";
                    break;
                }
            }
        }
        StringBuffer ans = new StringBuffer(tarC[0]);
        for (int i = 1; i < tarC.length; i++) {
            ans.append(tarC[i]);
        }
        return ans.toString();*/

        //高能方法
        char[] tarC = target.toCharArray();
        char[] stick = sticker.toCharArray();
        int[] count = new int[26];
        for (char c : tarC) {
            count[c - 'a']++;
        }
        for (char c : stick){
            count[c - 'a']--;
        }
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                ans.append((char)('a' + i));
            }
        }
        return ans.toString();
    }

    public int minStickers2(String[] stickers,String target){
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[N][26];
        for (int s = 0; s < stickers.length; s++) {
            for (char c : stickers[s].toCharArray()) {
                counts[s][c - 'a'] ++;
            }
        }
        int ans = process2(counts,target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    private int process2(int[][] stickers,String target){
        if (target.length() == 0){
            return 0;
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] tChars = target.toCharArray();
        int[] tcCounts = new int[26];
        for (char c : tChars) {
            tcCounts[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            // 尝试第一张贴纸是谁
            int[] firstSticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!不过滤一部分的话，可能会遇见死循环)
            if (firstSticker[tChars[0] - 'a'] > 0){//第一种票可以覆盖目标字符串首字符，若连首字符都盖不住，那就免谈，减少计算
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < 26; j++) {
                    if (tcCounts[j] > 0){
                        int nums = tcCounts[j] - firstSticker[j];//>0 还剩没贴完，=0 刚刚好，<0 完全满足且有富余
                        for (int k = 0; k < nums; k++) {
                            buffer.append((char)(j + 'a'));
                        }
                    }
                }
                String rest = buffer.toString();
                min = Math.min(min,process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public int minStickers3(String[] stickers,String target){
        int[][] counts = new int[stickers.length][26];
        for (int i = 0; i < counts.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                counts[i][c - 'a'] ++;
            }
        }
        HashMap<String,Integer> dp = new HashMap<>();
        dp.put("",0);//剩余没贴的"",换句话就是全贴好了的话,则需要0张贴纸
        int ans = process3(counts,target,dp);
        return ans = ans == Integer.MAX_VALUE ? -1 : ans;
    }
    private int process3(int[][] stickers,String t,HashMap<String,Integer> dp){
        if (dp.containsKey(t)){
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tCounts = new int[26];
        for (char c : target) {
            tCounts[c - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0){//贴纸捕获到tar首字符才有资格被使用，以后能用几张继续深度递归，不关此时的事儿
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < 26; j++) {
                    if (tCounts[j] > 0){
                        for (int k = 0; k < tCounts[j] - sticker[j]; k++) {//用了sticker这张贴纸之后，在j位置对应的字符，还有几个没贴
                            buffer.append((char)(j + 'a'));
                        }
                    }
                }
                String rest = buffer.toString();

                min = Math.min(min,process3(stickers,rest,dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t,ans);
        return ans;
    }


    public static void main(String[] args) {
        Code01_StickersToSpellword test = new Code01_StickersToSpellword();

        String qw = test.minus("qwe", "e");
        System.out.println(qw);
    }
}
