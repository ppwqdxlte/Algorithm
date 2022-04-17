package com.ppwqdxlte.basic.class05;

import java.util.HashMap;

/**
 * @author:李罡毛
 * @date:2021/7/16 16:55
 * // TODO:该程序的对数器跑不过，你能发现bug在哪吗？
 */
public class Code01_TrieTree {
    /**
     * 前缀树节点类型(1)：子节点数组表示
     */
    public static class Node1{
        public int pass;
        public int end;
        public Node1[] nexts;
        public Node1(){
            pass = 0;
            end = 0;
            nexts = new Node1[26];//此类前缀树的场景是，所有path都是【小写26个英文字母】
        }
    }
    /**
     * 前缀树节点类型(2)：子节点动态数组集合表示
     */
    public static class Node2{
        public int pass;
        public int end;
        public HashMap<Integer,Node2> nexts;
        public Node2(){
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }
    /**
     * 前缀树(1)
     */
    public static class Trie1{
        public Node1 node;//根结点
        public Trie1(){
            node = new Node1();
        }
        //增
        public void insert(String word){
            if (word == null || word.length() == 0) return;
            char[] chars = word.toCharArray();
            Node1 father = node;//操作结点初始化为前缀树的根节点
            for (int i = 0; i < chars.length; i++) {
                if (father.nexts[chars[i]-97] == null){
                    father.nexts[chars[i]-97] = new Node1();
                }
                father.nexts[chars[i]-97].pass ++;
                father = father.nexts[chars[i]-97];
            }
            father.end ++;
        }
        //删
        public void delete(String word){
            if (word == null || word.length() == 0) return;
            if (search(word) == 0) return;
            char[] chars = word.toCharArray();
            Node1 father = node;
            for (int i = 0; i < chars.length; i++) {
//                if (father.nexts[chars[i]].pass == 0) return;//有没必要有待商榷
                father.nexts[chars[i]-97].pass --;
                father = father.nexts[chars[i]-97];
            }
            father.end --;
//            if (father.end < 0) father.end = 0;//有没必要有待商榷
        }
        // word这个单词之前加入过几次
        public int search(String word){
            if (word == null || word.length() == 0) return 0;
            char[] chars = word.toCharArray();
            Node1 father = node;
            for (int i = 0; i < chars.length; i++) {
                if (father.nexts[chars[i]-97] == null){
                    return 0;
                }
                if (father.nexts[chars[i]-97].pass == 0){
                    return 0;
                }
                father = father.nexts[chars[i]-97];
            }
            return father.end;
        }
        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if (pre == null || pre.length() == 0) return 0;
            char[] chars = pre.toCharArray();
            Node1 father = node;
            for (int i = 0; i < chars.length; i++) {
                if (father.nexts[chars[i]-97] == null){
                    return 0;
                }
                if (father.nexts[chars[i]-97].pass == 0){
                    return 0;
                }
                father = father.nexts[chars[i]-97];
            }
            return father.pass;
        }
    }
    /**
     * 前缀树(2)
     */
    public static class Trie2{
        public Node2 node;
        public Trie2(){
            node = new Node2();
        }
        //增
        public void insert(String word){
            if (word == null || word.length() == 0) return;
            char[] chars = word.toCharArray();
            Node2 father = node;
            for (int i = 0; i < chars.length; i++) {
                if (!father.nexts.containsKey((int)chars[i])){
                    Node2 newNode = new Node2();
                    father.nexts.put((int)chars[i],newNode);
                }
                father.nexts.get((int)chars[i]).pass ++;
                father = father.nexts.get((int)chars[i]);
            }
            father.end ++;
        }
        //删
        public void delete(String word){
            if (word == null || word.length() == 0) return;
            if (search(word) == 0) return;
            char[] chars = word.toCharArray();
            Node2 father = node;
            for (int i = 0; i < chars.length; i++) {
                father.nexts.get((int)chars[i]).pass --;
                father = father.nexts.get((int)chars[i]);
            }
            father.end --;
        }
        // word这个单词之前加入过几次
        public int search(String word){
            if (word == null || word.length() == 0) return 0;
            char[] chars = word.toCharArray();
            Node2 father = node;
            for (int i = 0; i < chars.length; i++) {
                if (!father.nexts.containsKey((int)chars[i])){
                    return 0;
                }
                if (father.nexts.get((int)chars[i]).pass == 0){
                    return 0;
                }
                father = father.nexts.get((int)chars[i]);
            }
            return father.end;
        }
        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if (pre == null || pre.length() == 0) return 0;
            char[] chars = pre.toCharArray();
            Node2 father = node;
            for (int i = 0; i < chars.length; i++) {
                if (!father.nexts.containsKey((int)chars[i])){
                    return 0;
                }
                if (father.nexts.get((int)chars[i]).pass == 0){
                    return 0;
                }
                father = father.nexts.get((int)chars[i]);
            }
            return father.pass;
        }
    }
    /**
     * For test
     * 绝对正确的数据结构
     */
    public static class RightStructure{
        public HashMap<String,Integer> box;//key存储字符串，value存储字符串次数
        public RightStructure(){
            box = new HashMap<>();
        }
        //增
        public void insert(String word){
            if (word == null || word.length() == 0) return;
            if (!box.containsKey(word)){
                box.put(word,1);
            }else {
                int value = box.get(word);
                box.replace(word,value,value + 1);
            }
        }
        //删
        public void delete(String word){
            if (word == null || word.length() == 0) return;
            if (!box.containsKey(word)) return;
            int value = box.get(word);
            if (value == 1) box.remove(word);
            box.replace(word,value,value - 1);
        }
        // word这个单词之前加入过几次
        public int search(String word){
            if (word == null || word.length() == 0) return 0;
            if (!box.containsKey(word)) return 0;
            return box.get(word);
        }
        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if (pre == null || pre.length() == 0) return 0;
            int count = 0;
            for (String key: box.keySet()) {
                if (key.contains(pre)
                        && key.substring(0,pre.length()).equals(pre)) count += box.get(key);
            }
            return count;
        }
    }
    /**For test
     * 生成随机字符串
     * @param strLen 限制长度
     * @return 随机字符串
     */
    public static String generateRandomString(int strLen){
        if (strLen <= 0) return null;
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < strLen; i++) {
            char c = (char)(97 + 26*Math.random());
            str.append(c);
        }
        return str.toString();
    }
    /**For test
     * 打印前缀树的对比数据结构
     * @param rightStructure
     */
    public static void printRightStructure(RightStructure rightStructure){
        System.out.println("\n---------------RightStructure----------------");
        HashMap<String,Integer> hashMap = rightStructure.box;
        for (String key: hashMap.keySet()) {
            for (int i = 0; i < hashMap.get(key); i++) {
                System.out.print(key+"\t");
            }
        }
    }
    /**For test
     * 生成随机字符串 数组
     * @param strLen 限制长度
     * @param arrSize 限制容量
     * @return 随机字符串数组
     */
    public static String[] generateRandomStringArray(int strLen,int arrSize){
        int size =(int)((arrSize + 1)*Math.random());
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            int len = (int)((strLen + 1)*Math.random());
            strArr[i] = generateRandomString(len);
        }
        return strArr;
    }
    public static void main(String[] args) {
        int strLenLimit = 100;
        int arrSizeLimit = 100;
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            String[] strArr = generateRandomStringArray(strLenLimit,arrSizeLimit);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            RightStructure rightStructure = new RightStructure();
            for (int j = 0; j < strArr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25){
                    trie1.insert(strArr[j]);
                    trie2.insert(strArr[j]);
                    rightStructure.insert(strArr[j]);
                }else if (decide < 0.5){
                    trie1.delete(strArr[j]);
                    trie2.delete(strArr[j]);
                    rightStructure.delete(strArr[j]);
                }else if (decide < 0.75){
                    int ans1 = trie1.search(strArr[j]);
                    int ans2 = trie2.search(strArr[j]);
                    int ans3 = rightStructure.search(strArr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("search Oops!");
//                        System.out.println(strArr[j]+"\t"+ans1+"\t"+ans2+"\t"+ans3);
//                        printRightStructure(rightStructure);
                    }
                }else {
                    int ans1 = trie1.prefixNumber(strArr[j]);
                    int ans2 = trie2.prefixNumber(strArr[j]);
                    int ans3 = rightStructure.prefixNumber(strArr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("pre Oops!");
//                        System.out.println(strArr[j]+"\t"+ans1+"\t"+ans2+"\t"+ans3);
//                        printRightStructure(rightStructure);
                    }
                }
            }
        }
        System.out.println("Finished!");
    }
}
