package 中级算法.动态规划;

public class No4 {
    //              零钱兑换
    //给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    //计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
    //你可以认为每种硬币的数量是无限的。

    //----------------------------------------------------------------------------
    //                      动态规划解决
    /*public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        int count = -1;

        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < amount + 1; i++) {
            dp[0][i] = 10000;
        }

        for (int i = 1; i < coins.length + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (coins[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                }else{
                    if (dp[i-1][j] < dp[i][j-coins[i-1]] + 1) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i][j-coins[i-1]] + 1;
                    }
                }
            }
        }

        if (dp[coins.length][amount] != 10000) {
            count = dp[coins.length][amount];
        }
        return count;
    }*/
    //-------------------------------------------------------------------------

    //------------------------------------------------------------------------
    //                          贪心法（失败产品，并不能使用贪心法来解决）
    // 将硬币按面额进行排序，然后每次选择面额最大的，直到不能选
    //由于此题目的硬币面额并不固定（如63,481等，并非整数型的硬币面额），如果一味的按照大小来选择硬币
    // 会导致最终达不到给定的面额数
    /*public int coinChange(int[] coins, int amount){
        int temp = amount,count = 0,res = -1;

        sort(coins);

        for (int i = 0; i < coins.length && temp != 0; i++) {
            count += temp/coins[i];

            temp = temp % coins[i];
        }

        if (temp == 0) {
            res = count;
        }

        return res;
    }

    //堆排序的调整函数
    public void shift(int[] nums,int n,int end){
        int i = n,j = 2 * i + 1,temp;

        while (j <= end) {
            if (j + 1 <= end && nums[j] > nums[j+1]) {
                j++;
            }
            if (nums[i] > nums[j]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }else{
                break;
            }
            i = j;
            j = 2 * i + 1;
        }
    }
    public void sort(int[] nums){
        int n = nums.length,temp;

        //调整为最大堆
        for (int i = (n-2)/2; i >= 0; i--) {
            shift(nums,i,n-1);
        }

        for (int i = n-1; i >= 0; i--) {
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i-1);
        }
    }*/
    //---------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //                      优化版的动态规划
    //使用一维数组解决
    public int coinChange(int[] coins, int amount){
        int[] dp = new int[amount + 1];

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            dp[i] = 100000;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i] > dp[i - coins[j]] + 1) {
                    dp[i] = dp[i-coins[j]] + 1;
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
