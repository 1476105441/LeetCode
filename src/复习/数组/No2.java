package 复习.数组;

public class No2 {
    /**
     *      买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
     * 在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     *
     * 提示：
     *     1 <= prices.length <= 3 * 104
     *     0 <= prices[i] <= 104
     */

    //解法一：动态规划，在一天结束时有两种状态：手上
    //有股票或者手上没有股票
    /*public int maxProfit(int[] prices){
        int own = -prices[0],empty = 0,newOwn,newEmpty;

        for (int i = 1; i < prices.length; i++) {
            newOwn = Math.max(own,empty - prices[i]);
            newEmpty = Math.max(empty,own + prices[i]);

            own = newOwn;
            empty = newEmpty;
        }

        return Math.max(own+prices[prices.length-1],empty);
    }*/

    //解法二：贪心
    //低买高卖
    public int maxProfit(int[] prices){
        int res = 0,own = -1,i = 0;

        while (i < prices.length) {
            if (own == -1) {
                if (i + 1 == prices.length) {
                    break;
                }
                if (prices[i] < prices[i + 1]) {
                    own = prices[i];
                }else{
                    i++;
                }
            }else{
                if (i + 1 == prices.length || prices[i] > prices[i+1]) {
                    res += prices[i]-own;
                    own = -1;
                }else{
                    i++;
                }
            }
        }

        return res;
    }
}
