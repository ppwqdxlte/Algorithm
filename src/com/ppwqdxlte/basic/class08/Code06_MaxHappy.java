package com.ppwqdxlte.basic.class08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * @author:李罡毛
 * @date:2021/7/27 17:51
 * 【提问】公司等级分明，多叉树，无环，每个节点不限子结点数量，但是只有一个父结点
 * 就是说只有一个大boss，每个成员只有一个直属领导，但是可以有多个下属，公司要搞一个活动，邀请某个人的话，
 * 它的直接下属都不可以参加，怎么邀请能使所有活动参与者的快乐总值最大？？
 */
public class Code06_MaxHappy {

    private static class Emp{//employee
        public Integer happy;
        public List<Emp> subs;//subordinates
        public Emp(){ }
        public Emp(int h,List<Emp> ss){
            happy = h;
            subs = ss;
        }
    }
    private static class Scheme{
        public List<Emp> invited;
        public Integer totalHappy = 0;
        public Scheme(List<Emp> inv){
            invited = inv;
            if (inv != null){
                for (Emp e : inv) {
                    totalHappy += e.happy;
                }
            }
        }
    }
    public Scheme getMaxHappy(Emp boss){
        Scheme tb = tryOneTry(boss);
        return tb;
    }
    private Scheme tryOneTry(Emp X){
        //邀请boss
        Scheme ib = invite(X);
        //不邀请boss
        Scheme noBoss = notInvite(X);
        if (ib.totalHappy < noBoss.totalHappy){
            ib = noBoss;
        }
        return ib;
    }
    private Scheme merge(Scheme s1,Scheme s2){
        if (s1 == null) return s2;
        if (s2 == null) return s1;
        for (Emp e :
                s2.invited) {
            s1.invited.add(e);
            s1.totalHappy += e.happy;
        }
        return s1;
    }
    private Scheme invite(Emp X){
        if (X == null) return new Scheme(null);
        List<Emp> invited = new LinkedList<>();
        invited.add(X);
        Scheme ix = new Scheme(invited);
        for (Emp e : X.subs) {
            ix = merge(ix,notInvite(e));
        }
        return ix;
    }
    private Scheme notInvite(Emp X){
        if (X == null) return new Scheme(null);
        Scheme nix = new Scheme(new LinkedList<>());
        for (Emp e : X.subs) {
            nix = merge(nix,tryOneTry(e));
        }
        return nix;
    }


    /*
    *
    * 以下是左老师的解法
    *
    *
    */
    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    public static int maxHappy2(Employee head) {
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    public static class Info {
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    public static Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        int no = 0;
        int yes = x.happy;
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.no, nextInfo.yes);
            yes += nextInfo.no;

        }
        return new Info(no, yes);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }


    public static void main(String[] args) {
        Code06_MaxHappy test = new Code06_MaxHappy();

        Emp boss = new Emp(10,new LinkedList<>());
        Emp emp1 = new Emp(500,new LinkedList<>());
        Emp emp2 = new Emp(4,new LinkedList<>());
        Emp emp3 = new Emp(100,new LinkedList<>());
        boss.subs.add(emp1);boss.subs.add(emp2);boss.subs.add(emp3);
        Emp emp11 = new Emp(28,new LinkedList<>());
        Emp emp21 = new Emp(30,new LinkedList<>());
        Emp emp31 = new Emp(50,new LinkedList<>());
        Emp emp12 = new Emp(3,new LinkedList<>());
        Emp emp22 = new Emp(6,new LinkedList<>());
        Emp emp32 = new Emp(7,new LinkedList<>());
        Emp emp13 = new Emp(20,new LinkedList<>());
        Emp emp23 = new Emp(14,new LinkedList<>());
        Emp emp33 = new Emp(19,new LinkedList<>());
        emp1.subs.add(emp11);emp1.subs.add(emp12);emp1.subs.add(emp13);
        emp2.subs.add(emp21);emp2.subs.add(emp22);emp2.subs.add(emp23);
        emp3.subs.add(emp31);emp3.subs.add(emp32);emp3.subs.add(emp33);

        System.out.println(test.getMaxHappy(boss).totalHappy);
    }
}
