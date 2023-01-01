package senior.动态规划;

public class No2 {
    /**
     *      最佳买卖股票时机含冷冻期
     */

    //每天结束时还是两种状态：手中有股票和手中没有股票
    //但是与之前不同的是手中有股票的情况发生了改变：不
    //能从前一天手中没有股票转移而来，而是从前前天手中
    //没有股票转移而来
    //------------------------------------------
    //成功，1ms，没有击败全部
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][1]+prices[1],dp[0][0]);
        dp[1][1] = Math.max(dp[0][1],dp[0][0]-prices[1]);

        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][1]+prices[i],dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
        }

        return dp[n-1][0];
    }
}
