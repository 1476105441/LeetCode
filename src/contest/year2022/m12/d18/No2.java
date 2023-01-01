package contest.year2022.m12.d18;

public class No2 {
    /*static int[] dp;
    static Set<Integer> set; //标记当前n是否有因子
    static {
        dp = new int[100001];
        set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            dp[i] = i;
        }
        for (int i = 4; i < 100001; i++) {
            dp[i] = i;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    set.add(i);
                    int k = i / j;
                    dp[i] = Math.min(dp[j] + dp[k],dp[i]);
                }
            }
        }
    }
    public int smallestValue(int n) {
        while (set.contains(n)) {
            //特殊情况，直接退出循环
            if (dp[n] == n)
                break;
            n = dp[n];
        }
        return dp[n];
    }*/


    //下面这种接法是错误的，因为题目需要的是分解质因数
    int[] dp;
    {
        dp = new int[100001];
        for(int i = 0;i < 4;i++)
            dp[i] = i;
        for(int i = 4;i < 100001;i++){
            dp[i] = i;
            for(int j = 2;j * j <= i;j++){
                if(i % j == 0){
                    int k = i / j;
                    dp[i] = Math.min(dp[i],dp[j+k]);
                }
            }
        }
    }
    public int smallestValue(int n) {
        return dp[n];
    }
}
