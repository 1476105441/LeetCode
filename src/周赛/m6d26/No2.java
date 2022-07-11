package 周赛.m6d26;

public class No2 {
    /**
     *      统计放置房子的方式数
     */

    //使用回溯的想法试一下
    //失败，超出时间
    /*int[] value;
    int n;
    public int countHousePlacements(int n) {
        //c先将不存放任何房子的情况加上
        this.n = n;
        int k = 1,c = 1,half = (n + 1) >> 1,tc;
        value = new int[half+1];
        while (k <= half) {
            tc = search(k,-2,0,0);
            c += 2 * tc;
            for (int i = 1; i <= k; i++) {
                if (i != k) {
                    tc = 2 * value[i] * value[k];
                }else
                    tc = value[i] * value[k];
                c += tc;
            }
            k++;
        }

        return (c%1000000007);
    }

    //pre代表前一个元素的位置，我们只需要知道这个信息
    //loc是当前的位置
    //k是目前还有多少个房子需要分配
    public int search(int k,int pre,int loc,int count){
        //所有的房子都分配完了
        if (k == 0) {
            return 1;
        } else if (loc >= n) {
            return 0;
        }

        //未分配的房子比剩余的位置多，不用再回溯了
        if (k > ((n - loc + 1) >> 1)) {
            return 0;
        }

        for (int i = loc; i < n; i++) {
            //尝试把当前位置分配给房子
            //前一个必须相隔大于1
            if (pre < loc - 1) {
                int temp = search(k - 1, i, i + 2, 0);
                if (temp == 0) {
                    break;
                }
                count += temp;
            }
        }

        if (pre == -2) {
            value[k] = count;
        }

        return count;
    }*/

    //此题可以使用动态规划解决
    public int countHousePlacements(int n){
        int[] dp = new int[n+1];
        int mod = 1000000007;
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n + 1; i++) {
            //注意实现的细节，防止dp太大，相加之后直接取余
            dp[i] = (dp[i-1]+dp[i-2]) % mod;
        }

        //结果计算也要防止溢出
        return (int)((long)dp[n]*dp[n] % mod);
    }
}
