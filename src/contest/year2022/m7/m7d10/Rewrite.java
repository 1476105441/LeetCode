package contest.year2022.m7.m7d10;

import java.util.ArrayList;
import java.util.List;

public class Rewrite {
    //t1
    public int fillCups(int[] amount) {
        int  n = amount.length,res = 0,max,next,total = 0;
        for (int i = 0; i < n; i++) {
            total += amount[i];
        }

        while (total > 0) {
            max = -1;
            next = -1;
            if (amount[0] > amount[1]) {
                if (amount[0] > amount[2]) {
                    max = 0;
                    next = amount[2] > amount[1] ? 2 : 1;
                } else {
                    max = 2;
                    next = 0;
                }
            } else{
                if (amount[1] > amount[2]) {
                    max = 1;
                    next = amount[2] > amount[0] ? 2 : 0;
                } else{
                    max = 2;
                    next = 1;
                }
            }
            amount[max]--;
            total--;
            if (amount[next] != 0) {
                amount[next]--;
                total--;
            }
            res++;
        }

        return res;
    }

    //t2
    class SmallestInfiniteSet {
        boolean[] set; //代表元素有没有在集合中移除
        int pos;

        public SmallestInfiniteSet() {
            set = new boolean[1001];
            pos = 1;
        }

        public int popSmallest() {
            while(set[pos])
                pos++;
            set[pos] = true;
            return pos;
        }

        public void addBack(int num) {
            if(set[num])
                set[num] = false;
            if(num < pos)
                pos = num;
        }
    }

    //t3
    public boolean canChange(String start, String target) {
        char[] s = start.toCharArray(),t = target.toCharArray();
        int n = s.length,r = 0,l = 0;
        List<Integer> tr = new ArrayList<>(),tl = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(t[i] == 'L')
                tl.add(i);
            else if(t[i] == 'R')
                tr.add(i);
        }
        int lp = n,rp = -1,pos = tr.size()-1;
        for (int i = n-1; i > -1 ; i--) {
            if(s[i] == 'L')
                lp = i;
            else if (s[i] == 'R') {
                r++;
                if(pos == -1 || i > tr.get(pos) || i <= lp && lp <= tr.get(pos)){
                    return false;
                }
                s[tr.get(pos)] = 'R';
                s[i] = '_';
                pos--;
            }
        }
        if(r != tr.size())
            return false;
        pos = 0;
        for (int i = 0; i < n; i++) {
            if(s[i] == 'L'){
                l++;
                if(pos == tl.size() || i < tl.get(pos) || i >= rp && rp >= tl.get(pos)){
                    return false;
                }
                pos++;
            }
            else if (s[i] == 'R')
                rp = i;
        }
        if(l != tl.size())
            return false;

        return true;
    }

    //t4
    //1、计算数的质因数分解后有多少个质因数，不同的质因数之间是
    //组合关系，相同的质因数之间是累加
    /*static final int MK = 13,M = 10013,N = 10001,MOD = 1000000007;
    static int[][] dp;//计算组合数的dp数组
    static List<Integer>[] lists;//计算每个元素质因数个数的数组
    static {
        dp = new int[M][MK+1];
        lists = new List[N];
        //1、计算每个整数中的不同质因数的个数
        for (int i = 1; i < N; i++) {
            int x = i;
            lists[i] = new ArrayList<>();
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    int c = 1;
                    for (x /= j;x % j == 0;x /= j)
                        c++;
                    lists[i].add(c);
                }
            }
            //处理剩下一个质因数的情况
            if(x != 1)
                lists[i].add(1);
        }

        //2、计算组合数
        dp[0][0] = 1;
        for (int i = 1; i < M; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, MK); j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % MOD;
            }
        }
    }
    public int idealArrays(int n, int maxValue) {
        int res = 0;

        //可以从1-maxValue中所有数为数组底部
        for (int i = 1; i <= maxValue; i++) {
            long c = 1;
            for (Integer j : lists[i]) {
                c = (c * dp[n-1+j][j]) % MOD;
            }
            res = (res + (int)c) % MOD;
        }

        return res;
    }*/

    //单个质因子的个数不可能超过13个，因为最小的质因子：2的14次方等于16384，从题目给的数据量来看，不可能超过这个数值
    static int mod = 1000000007,m = 10001,k = 13,n = 10014;
    static List<Integer>[] list = new List[10001];
    static int[][] dp = new int[n][k+1];
    static{
        //1、计算每个元素有多少个质因数
        for(int i = 1;i < m;i++){
            int x = i;
            list[i] = new ArrayList<>();
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    int c = 1;
                    for (x /= j;x % j == 0;x /= j)
                        c++;
                    list[i].add(c);
                }
            }
            if(x > 1)
                list[i].add(1);
        }
        //2、计算组合数
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k,i); j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % mod;
            }
        }
    }
    public int idealArrays(int n, int maxValue) {
        int res = 0;

        for (int i = 1; i <= maxValue; i++) {
            long c = 1;
            for (Integer j : list[i]) {
                c = (c * dp[n+j-1][j]) % mod;
            }
            res = (res + (int)c) % mod;
        }

        return res;
    }
}
