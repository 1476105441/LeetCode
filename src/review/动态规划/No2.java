package review.动态规划;

public class No2 {
    /**
     *      买卖股票的最佳时机
     */

    //一次遍历
    /*public int maxProfit(int[] prices) {
        int min = prices[0],res = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > res) {
                res = prices[i] - min;
            }
        }

        return res;
    }*/

    //动态规划
    //一天结束时，有两种状态：手里有股票和手里没股票
    public int maxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }

        return dp[n-1][0];
    }
}
