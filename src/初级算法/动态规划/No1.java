package 初级算法.动态规划;

public class No1 {
    //                 爬楼梯
    //  假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

    //思路：爬到n阶台阶的方法只与第n-1和第n-2阶台阶有关
    /*
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        //dp数组第i项存放爬第i阶台阶有多少种方法，只与第i-1和第i-2阶台阶有关
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n-1];
    }*/

    public int climbStairs(int n){
        //实际上可以只用两个变量来代替数组
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int temp1,temp2,temp3 = 0;
        temp1 = 1;
        temp2 = 2;
        for (int i = 2; i < n; i++) {
            temp3 = temp1 + temp2;
            temp1 = temp2;
            temp2 = temp3;
        }
        return temp3;
    }
}
