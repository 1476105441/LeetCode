package contest.year2023.m2.d19;

public class No3 {
    //看了题解之后写出来的，还没有彻底理解
    /*private static int[] primes = new int[]{2,3,5,7,11,13,17,19,23,29};
    private static int MX = 31,PN = primes.length,mod = 1000000007;
    private static int[] masks = new int[MX];
    static{
        for (int i = 2; i < MX; i++) {
            int mask = 0;
            for (int j = 0;j < PN;j++) {
                int p = primes[j];
                if (i % p == 0) {
                    if (i % (p * p) == 0) {
                        mask = -1;
                        break;
                    }
                    mask |= (1 << j);
                }
            }
            masks[i] = mask;
        }
    }
    public int squareFreeSubsets(int[] nums) {
        int n = nums.length,m = 1 << PN;
        int[] dp = new int[m];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            int mask = masks[nums[i]];
            if (mask >= 0) {
                //从后往前遍历，因为从前往后遍历的话，会导致后面的结果被前面修改的结果所影响
                for (int j = m-1; j >= mask; j--) {
                    if ((j | mask) == j) {
                        dp[j] = (dp[j] + dp[j ^ mask]) % mod;
                    }
                }
            }
        }
        long res = 0;
        //得要从空集开始加，因为如果nums中只有一个1的话，dp[0]的值
        //为2，正确的返回结果是1，让res-1就是正确结果，而如果从下标
        //1开始遍历的话，返回结果就是0-1等于-1
        for (int i = 0; i < m; i++) {
            res = res + dp[i];
        }
        //此处需要特别注意，如果在上面进行mod的话，会出现一个问题
        //那就是当res == 1000000007的时候，mod了的结果为0，返
        //回res-1就是-1，结果错误
        return (int)((res-1) % mod);
    }*/

    //解题思路：题目的数据范围非常小，我们可以这点入手。
    //可以将数字分解为质数的乘积，拥有相同质数的数字不能放到一个子集
    //中，这样构造出来的子集就是一个无平方子集
    private static int[] primes = new int[]{2,3,5,7,11,13,17,19,23,29};
    private static int mx = 31,pn = primes.length,mod = 1000000007;
    private static int[] masks = new int[mx];
    static{
        for (int i = 2; i < mx; i++) {
            int mask = 0;
            for (int j = 0; j < pn; j++) {
                int p = primes[j];
                if (p > i) {
                    break;
                }
                if (i % p == 0) {
                    if (i % (p * p) == 0) {
                        mask = -1;
                        break;
                    }
                    mask = mask | (1 << j);
                }
            }
            masks[i] = mask;
        }
    }
    public int squareFreeSubsets(int[] nums) {
        int n = nums.length,m = 1 << pn;
        int[] dp = new int[m];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            int mask = masks[nums[i]];
            if (mask >= 0) {
                for (int j = m-1; j >= mask; j--) {
                    //j如果包含mask，则需要更新集合个数
                    if ((j | mask) == j) {
                        dp[j] = (dp[j] + dp[j ^ mask]) % mod;
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i < m; i++) {
            res += dp[i];
        }
        return (int)((res - 1) % mod);
    }
}
