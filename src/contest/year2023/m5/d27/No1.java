package contest.year2023.m5.d27;

/**
 * 2706.购买两块巧克力
 *
 * @author wjs 2023/8/16
 */
public class No1 {
    public int buyChoco(int[] prices, int money) {
        int res = money;
        int n = prices.length;
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for(int i=0;i < n;i++) {
            if(min > prices[i]) {
                secondMin = min;
                min = prices[i];
            } else if(secondMin > prices[i]) {
                secondMin = prices[i];
            }
        }
        res = res - min - secondMin;
        return res >= 0 ? res : money;
    }
}
