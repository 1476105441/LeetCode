package contest.year2023.m3.d5;

public class No4 {
    //失败
    /*public int waysToReachTarget(int target, int[][] types) {
        int n = types.length,mod = 1000000001;
        //第一个元素是方法数，第二个元素是当前题目做了多少道
        int[][][] dp = new int[n+1][target+1][2];
        for(int i = 0;i <= target;i++){
            dp[0][i][0] = 1;
        }
        //使用的数量超出了应该怎么判断？
        for(int i=1;i <= n;i++){
            int count = types[i-1][0],val = types[i-1][1];
            for(int j=1;j <= target;j++){
                //选择不执行当前任务
                dp[i][j][0] = dp[i-1][j][0];
                if(j >= val){
                    if(dp[i][j-val][1] < count){
                        dp[i][j][0] = (dp[i][j-val][0]%mod+dp[i][j][0]%mod) % mod;
                        dp[i][j][1] = dp[i][j-val][1]+1;
                    }
                }
            }
        }
        return dp[n][target][0];
    }*/

    //错误的例子，写的有多处错误
    /*public int waysToReachTarget(int target, int[][] types) {
        int n = types.length,mod = 1000000001;
        int[][] dp = new int[n+1][target+1];
        //for(int i=0;i <= target;i++) dp[0][i] = 1;
        dp[0][0] = 1;
        for(int i=1;i <= n;i++){
            int count = types[i-1][0],val = types[i-1][1];
            for(int j=1;j <= target;j++){
                for(int k=1;k <=count && k <= j/val;k++){
                    dp[i][j] = (dp[i][j] + dp[i-1][j-k*val]) % mod;
                }
            }
        }
        return dp[n][target] % mod;
    }*/

    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length,mod = 1000000007;
        int[][] dp = new int[n+1][target+1];
        //for(int i=0;i <= target;i++) dp[0][i] = 1;
        dp[0][0] = 1;
        for(int i=1;i <= n;i++){
            int count = types[i-1][0],val = types[i-1][1];
            for(int j=0;j <= target;j++){
                //不选当前题目
                dp[i][j] = dp[i-1][j];
                //选择当前的题目
                for(int k=1;k <=count && k <= j/val;k++){
                    dp[i][j] = (dp[i][j] + dp[i-1][j-k*val]) % mod;
                }
            }
        }
        return dp[n][target];
    }
}
