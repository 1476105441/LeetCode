package algorithm.dp;

public class No45 {
    //跳跃游戏 II
    //动态规划，42ms
    /*public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for(int i = 1;i < n;i++){
            dp[i] = 10000;
        }
        for(int i = 0;i < n;i++){
            for(int j = 1;j <= nums[i] && i + j < n;j++){
                dp[i+j] = Math.min(dp[i+j],dp[i]+1);
            }
        }
        return dp[n-1];
    }*/

    //贪心，1ms
    //类似于一种分块的想法，遍历标记当前能够到达的最远下标，
    //在最远下标之内的所有下标都能够通过当前的跳跃次数到达，
    //遍历每个能够到达的区域，更新最远下标。
    public int jump(int[] nums) {
        int n = nums.length;
        int res = 0,maxPos = 0,end = 0;
        for(int i = 0;i < n-1;i++){
            maxPos = Math.max(maxPos,i + nums[i]);
            if(i == end){
                end = maxPos;
                res++;
            }
        }
        return res;
    }
}
