package contest.year2023.m2.d5;

public class No3 {
    /*public int minCapability(int[] nums, int k) {
        int n = nums.length,res = Integer.MAX_VALUE;
        int[][] dp = new int[n][2];
        if (k == 1) {
            res = nums[0];
        }
        for (int i = 0; i < 2; i++) {
            dp[i][0] = nums[i];
            dp[i][1] = k - 1;
        }
        for (int i = 2; i < n; i++) {
             dp[i][0] = Math.max(nums[i],dp[i-2][0]);
             dp[i][1] = dp[i-2][1] - 1;
            if (dp[i][1] == 0) {
                res = Math.min(res,dp[i][0]);
            }
            if (i > 2 && dp[i - 3][0] < dp[i][0]) {
                dp[i][0] = dp[i-3][0];
                dp[i][1] = dp[i-3][1] - 1;
                if (dp[i][1] == 0) {
                    res = Math.min(res,dp[i][0]);
                }
            }
        }
        return res;
    }*/

    /*int n;
    int[] nums;
    public int minCapability(int[] nums, int k){
        n = nums.length;
        this.nums = nums;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res,dfs(i,k-1));
        }
        return res;
    }
    private int dfs(int loc,int k){
        if ((n - loc + 1) / 2 < k) {
            return Integer.MAX_VALUE;
        }
        if (k == 0) {
            return nums[loc];
        }
        int min = Integer.MAX_VALUE;
        for (int i = loc + 2; i < n; i++) {
            min = Math.min(min,dfs(i,k-1));
        }
        if (min != Integer.MAX_VALUE) {
            min = Math.max(nums[loc],min);
        }
        return min;
    }*/

    //二分+贪心
    /*int[] nums;
    int n,k;
    public int minCapability(int[] nums, int k){
        n = nums.length;
        this.k = k;
        this.nums = nums;
        int l = 0,r = 1000000000,c;
        while (l < r) {
            c = l + ((r-l) >> 1);
            if (check(c)) {
                r = c;
            } else {
                l = c + 1;
            }
        }
        return l;
    }*/
    /*private boolean check(int val){
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= val) {
                count++;
                i++;
            }
        }
        return count >= k;
    }*/

    /*private boolean check(int val){
        int f0 = 0,f1 = 0;
        for (int i = 0; i < n; i++) {
            int temp = f1;
            if (nums[i] <= val && temp < f0 + 1) {
                temp = f0 + 1;
            }
            f0 = f1;
            f1 = temp;
        }
        return f1 >= k;
    }*/


    //重新写一遍
    //题目的思想是要找到所有窃取链路中，最大窃取金额最小的值
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int l = 0,r = 1000000000,c;
        while(l < r){
            c = l + ((r-l) >> 1);
            if(check(c,nums,k)){
                r = c;
            } else {
                l = c+1;
            }
        }
        return l;
    }
    //在这个函数中要做的只是统计偷盗价值小于val的，窃取链路中窃取数量的最大值
    //使用dp数组写法
    /*private boolean check(int val,int[] nums,int k){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0] > val ? 0 : 1;
        if(n > 1){
            dp[1] = nums[1] > val ? dp[0] : 1;
        }
        for(int i = 2;i < n;i++){
            //不偷当前
            dp[i] = dp[i-1];
            if(nums[i] <= val){
                dp[i] = Math.max(dp[i],dp[i-2]+1);
            }
        }
        return dp[n-1] >= k;
    }*/

    //实际上不难发现，当前的选择只与前两个元素相关，所以我们可以用两个变量替代整个数组
    private boolean check(int val,int[] nums,int k){
        int n = nums.length;
        int d1 = 0,d2 = 0;
        for (int i = 0; i < n; i++) {
            int temp = d2;
            if (nums[i] <= val) {
                temp = Math.max(temp,d1 + 1);
            }
            d1 = d2;
            d2 = temp;
        }
        return d2 >= k;
    }
}
