package 复习.动态规划;

public class No3 {
    /**
     *      最大子序和
     */

    //dp[i]表示以i元素为结尾的连续
    //子数组的最大和，有两种状态转移
    //而来：
    // 1、加上前一个连续的序列和
    // 2、直接以当前元素本身为结尾
    /*public int maxSubArray(int[] nums) {
        int n = nums.length,max = nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (dp[i - 1] + nums[i] > nums[i]) {
                dp[i] = dp[i - 1] + nums[i];
            }else
                dp[i] = nums[i];

            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }*/


    //使用分治，将数组分成两部分，每
    //部分单独求数组的最小值，然后再
    //加上从两边各部分开始的最大部分
    //来求最大子序和
    public int maxSubArray(int[] nums){
        return countMax(nums,0,nums.length-1);
    }

    public int countMax(int[] nums,int l,int r){
        if (l == r) {
            return nums[l];
        }
        int m1,m2,m3,max,c = l+((r-l)>>1);
        m1 = countMax(nums,l,c);
        m2 = countMax(nums,c+1,r);
        int left,right,temp = 0;
        left = nums[c];
        right = nums[c+1];

        for (int i = c; i >= l; i--) {
            temp += nums[i];
            if (temp > left) {
                left = temp;
            }
        }
        temp = 0;
        for (int i = c+1; i <= r; i++) {
            temp += nums[i];
            if (temp > right) {
                right = temp;
            }
        }

        m3 = left+right;
        if (m1 > m2) {
            max = Math.max(m1,m3);
        }else
            max = Math.max(m2,m3);

        return max;
    }
}
