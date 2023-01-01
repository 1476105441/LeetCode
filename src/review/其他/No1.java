package review.其他;

public class No1 {
    /**
     *      位1的个数
     */

    //预处理
    int[] dp;
    {
        dp = new int[256];
        dp[0] = 0;
        for (int i = 1; i < 256; i++) {
            dp[i] = dp[i>>1] + (i&1);
        }
    }
    public int hammingWeight(int n) {
        int tmp = n,res = 0,c = 0;
        while (c < 4) {
            int loc = tmp & 0xff;
            res += dp[loc];
            tmp = tmp >> 8;
            c++;
        }
        return res;
    }
}
