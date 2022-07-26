package 高级算法.动态规划;

public class No6 {
    /**
     *      戳气球
     */

    //在每一个气球处，有两种选择，选择戳破或者不
    //戳破，两种选择都与前一个气球的选择有关
    //失败，想的过于简单
    /*public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return nums[0];
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0] * nums[1];

        for (int i = 1; i < n-1; i++) {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = Math.max(dp[i-1][1]+nums[i]*nums[i+1],dp[i-1][0]+nums[i-1]*nums[i]*nums[i+1]);
        }
        dp[n-1][1] = Math.max(dp[n-2][1]+nums[n-1],dp[n-2][0]+nums[n-2]*nums[n-1]);

        return dp[n-1][1];
    }*/

    //选择戳哪个气球和什么有关？
    //想法：除了维护选择的值之外还要维护一个”右边的数“
    /*public int maxCoins(int[] nums){
        int n = nums.length;
        int[][] dp = new int[][];
    }*/

    //解法一：记忆化搜索
    //逆向思维，戳破气球的过程很难想，但是可以反过来想，
    //向集合中不断添加气球，使得结果最大，添加的过程需
    //要使用深搜+记忆化来去除重复的结果
    /*int[][] max;
    int[] val;
    public int maxCoins(int[] nums){
        int n = nums.length;
        val = new int[n+2];
        max = new int[n+2][n+2];
        for (int i = 0; i < n; i++) {
            val[i+1] = nums[i];
        }
        val[0] = 1;
        val[n+1] = 1;

        return find(0,n+1);
    }
    //find函数的返回值就是l到r之间（不包括l和r）的最大气球数
    public int find(int l,int r){
        if (l >= r-1) {
            return 0;
        }
        if (max[l][r] != 0) {
            return max[l][r];
        }

        int m = Integer.MIN_VALUE;
        for (int i = l+1; i < r; i++) {
            int tmp = val[i] * val[l] * val[r] + find(i,r) + find(l,i);
            if (tmp > m) {
                m = tmp;
            }
        }

        max[l][r] = m;
        return m;
    }*/

    //解法二：动态规划
    //从方法一中可以看到，计算一个区间内的最大值的时候
    //需要用到子区间的最大值，将原问题分解成了重合的子
    //问题，所以可以转换一下做题方法，采用动态规划填表
    //的方式解决此题目
    public int maxCoins(int[] nums){
        int n = nums.length;
        int[] val = new int[n+2];
        int[][] dp = new int[n+2][n+2];

        for (int i = 1; i <= n; i++) {
            val[i] = nums[i-1];
        }
        val[0] = val[n+1] = 1;

        for (int l = 2; l <= n + 1; l++) {
            for (int i = 0; i < n + 2 - l; i++) {
                int j = i + l,temp = val[i] * val[j];
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],dp[i][k]+dp[k][j]+val[k] * temp);
                }
            }
        }

        return dp[0][n+1];
    }
}
