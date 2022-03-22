package 初级算法.动态规划;

import java.util.Stack;

public class No2 {
    //               买卖股票的最佳时机
    //  给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    //你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    //返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

    //是否是在数组中进行特殊查找？yes，所以可以用双指针
    /*
    public int maxProfit(int[] prices) {
        //min指针记录最小值位置，quick指针进行遍历，max存放最大利润
        int min = 0,max = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[min]) {
                min = i;
            }else{
                if (prices[i] - prices[min] > max) {
                    max = prices[i] - prices[min];
                }
            }
        }

        return max;
    }*/

    //想想动态规划怎么解决？
    //  与数组中的第二题一样，动态规划的状态都是当天有两种状态：当天手里持有股票；当天手里没有股票
    //而与之前有所不同的是：当天手里有股票的两种情况里，前一天手里没股票而当天购入股票变成了当天购
    //入股票，不必加上前一天手里没有股票的值
    /*public int maxProfit(int[] prices){
        if (prices == null || prices.length == 0) {
            return 0;
        }

        //表示状态的数组
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (dp[i-1][0] > dp[i-1][1] + prices[i]) {
                dp[i][0] = dp[i-1][0];
            }else{
                dp[i][0] = dp[i-1][1] + prices[i];
            }

            if (dp[i-1][1] > -prices[i]){
                dp[i][1] = dp[i-1][1];
            }else{
                dp[i][1] = -prices[i];
            }
        }
        return dp[prices.length-1][0];
    }*/
    //------------------------------------------------------------------

    //----------------------------------------------------------------------
    //                          动态规划优化版
    /*
    public int maxProfit(int[] prices){
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int temp1 = 0,temp2 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //判断当天手中没有持有股票的最大利润
            if (temp1 < temp2 + prices[i]) {
                temp1 = temp2 + prices[i];
            }

            //当天持有股票的最大利润
            if (temp2 < -prices[i]) {
                temp2 = -prices[i];
            }
        }

        return temp1;
    }*/
    //------------------------------------------------------------------------------

    //------------------------------------------------------------------------------
    //                           利用单调栈解决（双指针的另一种形式）
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            //如果栈顶元素大于prices[i]，那么栈顶元素出栈，
            //把prices[i]压栈，要始终保证栈顶元素是最小的
            if (stack.peek() > prices[i]) {
                stack.pop();
                stack.push(prices[i]);
            } else {
                //否则如果栈顶元素不大于prices[i]，就要计算
                //prices[i]和栈顶元素的差值
                max = Math.max(max, prices[i] - stack.peek());
            }
        }
        return max;
    }
}
