package review.动态规划;

public class No7 {
    /**
     *      零钱兑换
     */

    //dp[i][j]表示使用第i款零钱（包括以前）满足金额j的硬币个数
    //实际上对于当前的i和j，只有两种选择：使用当前的硬币或者不使
    //用当前的硬币
    //   使用当前硬币：dp[i][j] = dp[i][j-coins[i]]
    //   不使用当前硬币：dp[i][j] = dp[i-1][j] （也就是使用之前的硬币就可以完成j金额）
    //
    //之前想到的一个问题是：这个硬币很可能不是刚好能够凑齐金额的
    //不过现在想一下可能也不是什么大问题，因为如果不能够凑齐金额
    //的话，最终会转移到边界条件去，就会等于边界条件的值

    //成功，但总感觉哪里不对劲，19ms
    /*public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        //先将数组排序（不排序也是可以的）
        for (int i = 0; i < n; i++) {
            update(coins,i);
        }
        for (int i = n-1; i > -1; i--) {
            int temp = coins[i];
            coins[i] = coins[0];
            coins[0] = temp;
            shift(coins,0,i);
        }

        int[][] dp = new int[n+1][amount+1];
        //初始化边界条件
        for (int i = 1; i < amount + 1; i++) {
            dp[0][i] = -1;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i-1][j];
                }else{
                    if(dp[i-1][j] == -1){
                        //到达这里说明前一个不可达，要判断当前是否能构成
                        if (dp[i][j - coins[i - 1]] == -1)
                            dp[i][j] = -1;
                        else
                            dp[i][j] =  dp[i][j-coins[i-1]]+1;
                    }
                    else{
                        if (dp[i][j - coins[i - 1]] == -1) {
                            dp[i][j] = dp[i-1][j];
                        }else
                            dp[i][j] = Math.min(dp[i][j-coins[i-1]]+1,dp[i-1][j]);
                    }
                }
            }
        }

        return dp[n][amount];
    }*/

    //不用做特殊判断也可以，因为如果不能凑成金额的话最终会转移到边界条件上
    /*public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n+1][amount+1];
        //初始化边界条件
        for (int i = 1; i < amount + 1; i++) {
            dp[0][i] = 100000;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.min(dp[i][j-coins[i-1]]+1,dp[i-1][j]);
                }
            }
        }

        if (dp[n][amount] > amount) {
            return -1;
        }
        return dp[n][amount];
    }*/

    //使用一维数组节省空间
    //是一种简化的思维，只维护每种金额所需的最小硬币数，即
    //dp[i]表示金额i所需要的最小硬币数，按照顺序遍历，在
    //遍历到i时，那么i-coins[j]一定已经被遍历过了，所以
    //就可以利用已有的信息来判断当前dp[i]的值
    /*public int coinChange(int[] coins, int amount){
        int[] dp = new int[amount+1];

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = 100000;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i] > dp[i - coins[j]] + 1) {
                    dp[i] = dp[i-coins[j]] + 1;
                }
            }
        }

        return dp[amount] == 100000 ? -1:dp[amount];
    }*/

    //堆排序
    /*public void shift(int[] nums,int loc,int n){
        int i = loc,j = 2*i+1;
        while (j < n) {
            if (j < n - 1 && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }
    public void update(int[] nums,int i){
        int p = (i-1)>>1;
        while (i > 0) {
            if (nums[i] > nums[p]) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }*/


    /**
     * =================================分割线========================================
     */
    //第二种解法：搜索（使用记忆化搜索优化）
    //从后往前搜索，对于当前的金额，查找哪个金额的硬币数最小
    int[] dp;
    public int coinChange(int[] coins,int amount){
        dp = new int[amount];

        return find(coins,amount);
    }

    public int find(int[] coins, int num) {
        if (num < 0) {
            return -1;
        }
        if (num == 0) {
            return 0;
        }
        if (dp[num - 1] != 0) {
            return dp[num-1];
        }
        int min = Integer.MAX_VALUE,res;
        for (int i = 0; i < coins.length; i++) {
            res = find(coins,num-coins[i]);
            if (res >= 0 && res < min) {
                min = res+1;
            }
        }

        //由于题目要求无法凑成硬币金额，返回-1，所以统一设置成无法满足就为-1
        if (min == Integer.MAX_VALUE)
            dp[num-1] = -1;
        else
            dp[num-1] = min;
        return dp[num-1];
    }
}
