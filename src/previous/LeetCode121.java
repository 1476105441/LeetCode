package previous;

public class LeetCode121 {
    //不必使用动态规划
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int min, max=0;
        //当前阶段的最小值
        min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            //更新最小值
            if (prices[i] < min) {
                min = prices[i];
            }
            //计算最大利润
            if(prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }


    /*
    失败产品
    public static int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        //f存放的是以第i天结尾的最大利润
        int[] f = new int[prices.length];
        //最早只能从第一天买入，所以第一天的值是0
        f[0] = 0;
        int max=f[0];
        for(int i=1;i< prices.length;i++){
            profit(i,i-1,prices,f);
            if (f[i] > max) {
                max = f[i];
            }
        }
        return max;
    }*/

    /*用于计算以第i天结尾的最大利润
    public static void profit(int i, int k, int[] prices, int[] f) {
        if (k == -1) {
            f[i] = 0;
            return;
        }
        if (prices[i] > prices[k]) {
            f[i] = f[k] + prices[i] - prices[k];
        } else {
            profit(i, k - 1, prices, f);
        }
    }*/
}
