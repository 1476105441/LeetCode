package contest.year2022.m7.m7d3;

public class No3 {
    /**
     *      知道秘密的人数
     */

    //需要记录每天新增了多少人
    /*public static int peopleAwareOfSecret(int n, int delay, int forget) {
        double res = 1;
        int f = 2-forget;
        double[] dp = new double[n+1];
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            //每天新增的数量取决于当前有多少人可以说出来

            double c = 0;
            int j = 1;
            //遗忘的人数，要减去
            if (f > 0) {
                res -= dp[f];
                j = f+1;
            }
            //统计可以传递秘密且没有遗忘的人数
            for (; j <= i - delay; j++) {
                c += dp[j];
            }
            dp[i] = c%1000000007;
            res += dp[i];
            f++;
        }

        return (int)(res%(1000000007));
    }*/

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        double res = 0;
        int f = 2-forget;
        double[] dp = new double[n+1];
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            //每天新增的数量取决于当前有多少人可以说出来

            double c = 0;
            int j = 1;
            //遗忘的人数，要减去
            if (f > 0) {
                j = f+1;
            }
            //统计可以传递秘密且没有遗忘的人数
            for (; j <= i - delay; j++) {
                c += dp[j];
            }
            dp[i] = c%1000000007;
            f++;
        }

        for (int i = n-forget+1; i < n + 1; i++) {
            res += dp[i];
        }

        return (int)(res%(1000000007));
    }
}
