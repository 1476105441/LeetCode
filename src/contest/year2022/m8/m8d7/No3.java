package contest.year2022.m8.m8d7;

public class No3 {
    /**
     *      检查数组是否存在有效划分
     */

    //失败了，早上没写对
    /*public boolean validPartition(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                if(i < 2 || nums[i-2] == nums[i])
                    dp[i+1] = 1;
                else if(dp[i-1] == 0)
                    return false;
            } else if (nums[i] - nums[i - 1] == 1) {
                if (i >= 2) {
                    if(nums[i-2] == nums[i-1]-1 && dp[i-2] == 1)
                        dp[i+1] = 1;
                }
            }
        }

        return dp[n] == 1;
    }*/

    //还是失败
    /*public boolean validPartition(int[] nums){
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                if (dp[i] == 2) {
                    return false;
                }

                dp[i+1] = 1;
            } else if (nums[i] == nums[i - 1] + 1) {
                if (dp[i] == 2) {
                    dp[i+1] = 1;
                }else if(dp[i-1] == 1)
                    dp[i+1] = 2;
            }
        }

        return dp[n] == 1;
    }*/

    //看了题解，发现根本不用想那么多
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] && dp[i - 1])
                dp[i+1] = true;
            else if(i > 1 && nums[i] == nums[i-1] && nums[i-1] == nums[i-2] && dp[i-2])
                dp[i+1] = true;
            else if(i > 1 && nums[i] == nums[i-1]+1 && nums[i] == nums[i-2]+2 && dp[i-2])
                dp[i+1] = true;
        }
        return dp[n];
    }
}
