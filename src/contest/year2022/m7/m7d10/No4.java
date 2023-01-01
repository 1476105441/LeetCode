package contest.year2022.m7.m7d10;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    /**
     *      统计理想数组的数目
     */

    //回溯，超时
    /*int[] nums;
    int m,res;
    public int idealArrays(int n, int maxValue) {
        nums = new int[n];
        m = maxValue;
        res = 0;
        find(0,1);

        return res;
    }
    public void find(int loc,int val){
        if (loc == nums.length) {
            res = (res+1) % 1000000007;
            return;
        }
        for (int i = val; i <= m; i++) {
            if (loc == 0 || i % nums[loc - 1] == 0) {
                nums[loc] = i;
                find(loc+1,nums[loc]);
            }
        }
    }*/


    //超时
    /*int[] nums;
    int m,res;
    public int idealArrays(int n, int maxValue) {
        nums = new int[n];
        m = maxValue;
        res = 0;
        find(0,1);

        return res;
    }
    public void find(int loc,int val){
        if (loc == nums.length) {
            res = (res+1) % 1000000007;
            return;
        }
        for (int i = 1; i*val <= m; i++) {
            nums[loc] = i*val;
            find(loc+1,nums[loc]);
        }
    }*/


    //分解质因数+组合问题
    //从头部开始回溯的寻找每一个位置会导致超时，考虑
    //理想数组的结尾元素似乎可以解决这个问题
    //成功，42ms
    /*public int idealArrays(int n, int maxValue){
        //将maxValue内的所有值都计算质因
        //子，然后计算质因子个数的组合数
        int res = 0;
        for (int i = 1; i <= maxValue; i++) {
            res = (res + count(i,n)) % 1000000007;
        }
        return res;
    }

    //计算质因子个数
    public int count(int k,int n){
        int temp = k;
        //list数组用于统计不同的质因子个数
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i*i <= temp; i++) {
            if (temp % i == 0) {
                int c = 1;
                for (temp /= i;temp % i == 0;temp /= i)
                    c++;
                list.add(c);
            }
        }
        if (temp > 1) {
            list.add(1);
        }
        return countNum(list,n);
    }
    //计算组合数
    public int countNum(List<Integer> list,int n){
        long res = 1;
        for (Integer i : list) {
            res = (res * dp[n+i-1][i]) % 1000000007;
        }
        return (int)res;
    }
    static int[][] dp = new int[10014][14];
    static{
        dp[0][0] = 1;
        for (int i = 1; i < 10014; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i,13); j++) {
                dp[i][j] = (dp[i-1][j]+dp[i-1][j-1])%1000000007;
            }
        }
    }*/


    //时间吊锤上一个啊，13ms
    //使用静态代码块进行预先处理
    /*static final int MX = 10001,N = 10014,K = 13,MOD = 1000000007;
    static List<Integer>[] lists = new List[10001];
    static int[][] dp = new int[N][14];
    static{
        //计算在MaxValue范围内，每个可取元素的质因子的个数
        for (int i = 1; i < MX; i++) {
            int x = i;
            List<Integer> list = new ArrayList<>();
            lists[i] = list;
            for (int j = 2; j*j <= x; j++) {
                if (x % j == 0) {
                    int c = 1;
                    for (x /= j;x % j == 0;x /= j)
                        c++;
                    list.add(c);
                }
            }
            if (x > 1)
                list.add(1);
        }

        //计算dp数组的值
        dp[0][0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, K); j++) {
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % MOD;
            }
        }
    }

    public int idealArrays(int n, int maxValue){
        int res = 0;
        for (int i = 1; i <= maxValue; i++) {
            long c = 1;
            for (Integer j : lists[i]) {
                c = c * dp[n+j-1][j] % MOD;
            }
            res = (res+(int)c)%MOD;
        }
        return res;
    }*/


    //重新写一遍，首先，预处理计算出每个数字的质因子数量
    //还要预处理出每个组合数的值
    //由于题目限定了最大数值为10000，所以一个数值的最多
    //的质因子个数不超过13个
    static final int MK = 13,N = 10014,M = 10001,MOD = 1000000007;
    //为什么N是10014？将原数组中的空位数量看成有n-1个可以放隔板的位置，每多一个质因数就多了一个放置隔板的位置，所以总的位置是n-1+k
    static List<Integer>[] lists;
    static int[][] dp;
    static{
        lists = new List[M];
        dp = new int[N][14];

        //第一步，计算每个最大值的质因子个数，要分别统计相同质因子的个数和不同质因子的个数
        for (int i = 1; i < M; i++) {
            int x = i;
            List<Integer> list = new ArrayList<>();
            lists[i] = list;
            //质因子从2开始，对于乘积小于2的所有元
            //素，都做一次判断是否是质因子
            for (int j = 2; j*j <= x; j++) {
                if (x % j == 0) {
                    int c = 1;
                    //做重复的计算，统计相同质因子的个数
                    for (x /= j; x % j == 0; x /= j) {
                        c++;
                    }
                    list.add(c);
                }
            }
            if (x != 1) {
                list.add(1);
            }
        }

        //第二步，计算隔板法中所有组合取得的值
        dp[0][0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, MK); j++) {
                dp[i][j] = (dp[i-1][j]+dp[i-1][j-1])%MOD;
            }
        }
    }

    public int idealArrays(int n, int maxValue){
        int res = 0;
        for (int i = 1; i <= maxValue; i++) {
            long c = 1;
            for (Integer j : lists[i]) {
                c = (c * dp[n+j-1][j]) % MOD;
            }
            res = (res+(int)c) % MOD;
        }
        return res;
    }
}
