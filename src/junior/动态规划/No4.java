package junior.动态规划;

public class No4 {
    //                 打家劫舍
    //  你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    //
    //提示：
    //    1 <= nums.length <= 100
    //    0 <= nums[i] <= 400

    //什么样的问题是动态规划问题？原问题有多个解，求最优解
    //动态规划满足？最优性原理：即原问题的最优解是由子问题的最优解所构成
    //动态规划的解题？1、划分子问题   2、确定动态规划函数   3、填表
    //第一步：将问题转换成dp[i]所存储的是到第i栋之间所能取得的最高金额，此最高金额只由dp[i-2]和dp[i-3]所决定，因为i-1是和i相邻了，不能取
    //第二步：
    //      i-2>=0 && i-3>=0时：dp[i] = max(nums[i] + dp[i-2],nums[i] + d[i-3]);
    //      i=2 ： dp[i] = nums[i] + dp[i-2];
    //      i=1 || i=0 ： dp[i] = nums[i];
    //第三步：填表

    /*public int rob(int[] nums) {
        if (nums == null || nums.length ==0) {
            return 0;
        }
        int n = nums.length;

        boolean flag = true;

        //dp[]数组存储当前最大金额
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (flag) {
                if (i < 2) {
                    dp[i] = nums[i];
                }else{
                    //i==2时
                    dp[i] = nums[i] + dp[i-2];
                    flag = false;
                }
                continue;
            }

            dp[i] = nums[i] + Math.max(dp[i-2],dp[i-3]);
        }

        if (n == 1){
            return nums[0];
        }

        if (dp[n-1] > dp[n-2]){
            return dp[n-1];
        }else{
            return dp[n-2];
        }
    }*/

    //-------------------------------------------------------------

}
