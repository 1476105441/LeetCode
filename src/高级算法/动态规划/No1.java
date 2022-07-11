package 高级算法.动态规划;

public class No1 {
    /**
     *      乘积最大子数组
     */

    //这个题目有点想最大子序和呀
    //没有考虑到负数的情况，存储两个值？
    //一个最大一个最小因为负数有可能把最
    //小变成最大
    //--------------------------------
    //成功，4ms，才击败了百分制8
    /*public int maxProduct(int[] nums) {
        int n = nums.length,max = nums[0];
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0]*nums[i],nums[i]);
            dp[i][0] = Math.max(dp[i][0],dp[i-1][1] * nums[i]);
            dp[i][1] = Math.min(dp[i-1][1]*nums[i],nums[i]);
            dp[i][1] = Math.min(dp[i-1][0]*nums[i],dp[i][1]);
            if (max < dp[i][0]) {
                max = dp[i][0];
            }
        }

        return max;
    }*/


    //思考一下，如何优化？
    //没有起到优化效果，cpu运算时间极快
    /*public int maxProduct(int[] nums){
        int n = nums.length,max = nums[0];
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            int temp1 = dp[i-1][0]*nums[i],temp2 = dp[i-1][1] * nums[i];
            dp[i][0] = Math.max(temp1,nums[i]);
            dp[i][0] = Math.max(dp[i][0],temp2);
            dp[i][1] = Math.min(temp2,nums[i]);
            dp[i][1] = Math.min(temp1,dp[i][1]);
            if (max < dp[i][0]) {
                max = dp[i][0];
            }
        }

        return max;
    }*/


    //为什么用两个数组可以提升速度？为什么单独判断最大值可以提升速度？
    /*public int maxProduct(int[] nums) {
        int n = nums.length,max = nums[0];
        int[] dp1 = new int[n],dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i-1]*nums[i],Math.max(nums[i],dp2[i-1] * nums[i]));
            dp2[i] = Math.min(dp2[i-1]*nums[i],Math.min(dp1[i-1]*nums[i],nums[i]));
        }
        for (int i = 0; i < n; i++) {
            if (max < dp1[i]) {
                max = dp1[i];
            }
        }

        return max;
    }*/


    //只使用两个变量
    public int maxProduct(int[] nums){
        int n = nums.length,max,b,s;
        max = nums[0];
        b = nums[0];
        s = nums[0];

        for (int i = 1; i < n; i++) {
            int t1,t2;
            t1 = Math.max(b*nums[i],Math.max(s*nums[i],nums[i]));
            t2 = Math.min(b*nums[i],Math.min(s*nums[i],nums[i]));
            b = t1;
            s = t2;
            if (b > max) {
                max = b;
            }
        }

        return max;
    }
}
