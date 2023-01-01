package contest.year2022.m9.m9d4;

public class No2 {
    /**
     *      恰好移动 k 步到达某一位置的方法数目
     */

    //回溯超时
    /*long res;
    int end,mod = 1000000007;
    public int numberOfWays(int startPos, int endPos, int k) {
        int dis = Math.abs(startPos-endPos);
        if(dis > k)
            return 0;
        end = endPos;
        find(startPos,k);
        return (int)res;
    }
    public void find(int loc,int c){
        int dis = Math.abs(loc-end);
        if(c < dis || c == dis+1)
            return;
        if(c == dis){
            res = (res+1) % mod;
            return;
        }
        find(loc+1,c-1);
        find(loc-1,c-1);
    }*/

    /*public int numberOfWays(int startPos, int endPos, int k){
        int dis = Math.abs(startPos-endPos),res = 0,mod = 1000000007;
        if(dis > k)
            return res;

    }*/

    //使用组合数的思想,7ms
    public int numberOfWays(int startPos,int endPos,int k){
        //a是选择往负方向走的次数，这里也用来做暂存结果的变量
        int d = Math.abs(startPos - endPos),a = k-d,mod = 1000000007;
        //如果当前a是奇数，那么除2之后得到的是一个小数，不符合条件
        if(k < d || (a&1) != 0){
            return 0;
        }
        //除2之后才是真实的值
        a >>= 1;
        int[][] dp = new int[k+1][a+1];

        for(int i = 0;i < k+1;i++){
            dp[i][0] = 1;
        }
        for(int i = 1;i < k+1;i++){
            for(int j = 1;j <= Math.min(a,i);j++){
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % mod;
            }
        }

        return dp[k][a];
    }
}
