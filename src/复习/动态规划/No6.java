package 复习.动态规划;

public class No6 {
    /**
     *      不同路径
     */

    //解法一：动态规划，不必多说
    /*public int uniquePaths(int m,int n){
        int[][] dp = new int[m][n];

        //初始化边界条件
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }*/

    //解法二：数学思维
    //这个思维我直呼牛逼，从第一个位置移动
    //到右下角位置的过程中，一共需要移动（
    //m+n-2）次，其中有m-1次是往下移动，只
    //需要在m+n-2次中选出m-1次作为向下移动，
    //所以该题就转换成了组合问题

    //失败版本，由于阶乘太大，溢出
    /*public int uniquePaths(int m,int n){
        if (m == 1 || n == 1) {
            return 1;
        }
        int t = m+n-2,a = n-1;
        long temp=1,r2=1,r3=1;

        for (int i = 1; i <= t; i++) {
            temp *= i;
            if (i == a) {
                r2 = temp;
            }
            if (i == m - 1) {
                r3 = temp;
            }
        }

        return (int)(temp/(r2*r3));
    }*/

    //寻求解决办法，分析组合公式与所求值之间的关系
    //得到一个简单的求解组合公式的方法
    public int uniquePaths(int m,int n){
        long res = 1;
        int t = n,s = 1;
        for (; s < m; s++, t++) {
            res = res * t / s;
        }
        return (int)res;
    }
}
