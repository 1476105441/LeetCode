package 周赛.m7d23;

import java.util.*;

public class No4 {
    /**
     *      不可能得到的最短骰子序列
     */

    //失败
    /*int[] dp;
    {
        dp = new int[100001];
        dp[1] = 1;
        for (int i = 2; i < 100001; i++) {
            dp[i] = i + dp[i-1];
        }
    }
    public int shortestSequence(int[] rolls, int k) {
        Map<Integer, Set<String>> map = new HashMap<>();

        for (int i = 0; i < rolls.length;) {
            Set<Integer> set = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            sb.append(rolls[i]);
            Set<String> set1 = map.get(1);
            if(set1 == null){
                set1 = new HashSet<>();
                map.put(1,set1);
            }
            set1.add(sb.toString());
            set.add(rolls[i]);
            int j,c=1;
            for (j = i+1; j < rolls.length && !set.contains(rolls[j]); j++) {
                c++;
                set.add(rolls[j]);
                Set<String> set2 = map.get(c);
                if(set2 == null){
                    set2 = new HashSet<>();
                    map.put(c,set2);
                }
                set2.add(sb.toString());
                sb.append(rolls[j]);
            }
            i = j;
        }

        for (int i = 1; i <= k; i++) {
            Set<String> set = map.get(i);
            long size = dp[k] / (dp[i]+dp[k-i]);
            if(set == null || set.size() < size)
                return i;
        }
        return -1;
    }*/

    //由于题目要找的是不能得到的最短子序列
    /*public int shortestSequence(int[] rolls,int k){
        Set<Integer> set = new HashSet<>();
        int res = 0;

        for (int i = 0; i < rolls.length; i++) {
            set.add(rolls[i]);
            if (set.size() == k) {
                set = new HashSet<>();
                res++;
            }
        }

        return res+1;
    }*/

    //使用数组代替set
    public int shortestSequence(int[] rolls,int k){
        int[] set = new int[k+1];
        int res = 1,c = 0;

        for (int i = 0; i < rolls.length; i++) {
            if (set[rolls[i]] < res) {
                set[rolls[i]]++;
                c++;
                if (c == k) {
                    c = 0;
                    res++;
                }
            }
        }

        return res;
    }
}
