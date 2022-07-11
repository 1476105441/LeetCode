package 复习.动态规划;

public class No1 {
    /**
     *      爬楼梯
     */

    //每次只能跨一步或者跨两步
    //所以第n个台阶只能由n-1和n-2跨来
    //f(n) = f(n-1) + f(n-2)
    public int climbStairs(int n){
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
