package contest.year2022.m11.d12;

public class No2 {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int MOD = 1000000007;
        long[] dp = new long[high+1];
        long res = 0;

        dp[0] = 1;
        for (int i = Math.min(zero,one); i < high + 1; i++) {
            if(i >= zero)
                dp[i] = (dp[i] + dp[i-zero]) % MOD;
            if(i >= one)
                dp[i] = (dp[i] + dp[i-one]) % MOD;
        }

        for (int i = low; i <= high; i++) {
            res = (res+dp[i]) % MOD;
        }

        return (int)res;
    }
}
