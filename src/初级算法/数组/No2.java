package 初级算法.数组;

public class No2 {
    /*
    买卖股票的最佳时机 II
          给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
        设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
        注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

        作者：力扣 (LeetCode)
        链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        提示：
            1 <= prices.length <= 3 * 104
            0 <= prices[i] <= 104

    */

    /*
    //-----------------------------------------------------------------------------------------------
    //                                 贪心算法（此题的最优解）
    public static int maxProfit(int[] prices) {
        //想法：从第一天开始遍历，temp记录第一天的股票价格，如果遇到第二天价格比该天低的，将该天的价格减去temp得到的
        // 值加到和sum里然后将temp设置为第二天，重复上述操作，直到结束。

        if (prices.length == 1) {
            return 0;
        }
        int temp, sum = 0;
        temp = prices[0];
        for (int i = 0; i < prices.length; i++) {
            //防止数组越界
            if (i + 1 != prices.length && prices[i] > prices[i+1]) {
                sum += prices[i] - temp;
                temp = prices[i+1];
            } else if (i == prices.length-1 && prices[i] >= prices[i-1]) {
                //如果最后一个比前面一个大或者相等，就说明还有可以赚的利润没有加上，反之则没有
                sum += prices[i] - temp;
            }
        }
        return sum;
    }
    //----------------------------------------------------------------------------------------------
    */

    //----------------------------------------------------------------------------------------------
    //                                       初级算法.动态规划
    public static int maxProfit(int[] prices){
        if (prices == null || prices.length <2) {
            return 0;
        }
        //dp[i][0]表示第i天交易完之后手里没有股票的最大利润，
        // dp[i][1]表示第i天交易完之后手里有股票的最大利润。
        int[][] dp = new int[prices.length][2];

        //设置边界条件
        dp[0][0] = 0;//第一天没有进行交易，利润为0
        dp[0][1] = -prices[0];//第一天进行交易了，利润

        for(int i=1;i<prices.length;i++){
            //第i天交易完之后手里没有股票有两种情况：
            //  1、当天没有进行交易，也就是说前一天交易完之后已经是手里没有股票的状态了，dp[i][0]=dp[i-1][0]
            //  2、当天进行了交易之后手里没有股票了，前一天交易完之后手里是有股票的，dp[i][0]=dp[i][1]+prices[i]
            //最大利润就是取两种情况的最大值
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            //第i天交易完之后手里有股票的情况也有两种：
            //  1、当天没有进行交易，也就是说，前一天交易结束的时候手里已经有股票了，dp[i][1]=dp[i-1][1]
            //  2、当天进行了购买股票的交易操作，也就是说前一天交易结束的时候手里已经没有股票了，当天才能进行购买股票的
            //  操作，dp[i][1]=dp[i-1][0]-prices[i]
            //最大利润也是取两种情况的最大值
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        //最后一天肯定是没有股票的时候，利润才会最大
        return dp[prices.length-1][0];
    }
    //----------------------------------------------------------------------------------------------


    /*----------------------------------------------------------------------------------------------
    //                                 初级算法.动态规划（优化版）
    public static int maxProfit(int[] prices){
        //想法，第i天的利润只与前一天有关，不必使用二维数组，只用两个变量即可解决
        //dp1是当天交易结束时手里有股票的最大利润，dp0是交易结束之后手里没有股票的最大利润
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int dp1,dp0,newDp1,newDp0;
        //设置边界
        dp0 = 0;
        dp1 = -prices[0];
        for(int i=1;i<prices.length;i++){
            newDp1 = Math.max(dp0 - prices[i],dp1);
            newDp0 = Math.max(dp0,dp1+prices[i]);
            dp1 = newDp1;
            dp0 = newDp0;
        }
        return dp0;
    }//---------------------------------------------------------------------------------------------
    */
}
