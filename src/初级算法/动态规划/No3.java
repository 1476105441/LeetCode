package 初级算法.动态规划;

public class No3 {
    //              最大子序和
    //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    //写题之前思考一个问题：什么样的题目需要用到动态规划来解决？是最优解问题吗？
    //动态规划三要素：1、划分子问题   2、确定动态规划函数   3、填写表格

    //----------------------------------------------------------------------------------------------
    //第一步：将问题转化成对于数组中的任意一个元素i，以i为尾的数组中求最大和的子数组（划分子问题）
    //第二步：如何确定以i为尾的子数组的最大和？想法是用一个数组dp存放以i为尾的子数组的最大和，边界值为i=0的时候，dp[i]=nums[i];
    //此后的第i项dp[i] = max(dp[i-1] + nums[i],nums[i]);
    //第三步在第二步中已实现
    //                        初级算法.动态规划，成功
    /*
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //状态数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        //用max存放最大值
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] + nums[i] > nums[i]){
                dp[i] = dp[i-1] + nums[i];
            }else
                dp[i] = nums[i];
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }
    */
    //----------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //                        分治法
    //将一个数组划分为两半，分别寻找最大子序和
    //一个数组的最大子序和有三种情况：1、在左半边的数组中    2、在右半边的数组中   3、左右半边合并的数组中（即在中间）
    public int maxSubArray(int[] nums){
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return maxSubArray(nums,0,nums.length - 1);
    }
    public int maxSubArray(int[] nums,int left,int right){
        //边界条件
        if (left == right) {
            return nums[left];
        }

        int maxLeft,maxRight,lefts,rights,max,center = left + (right - left)/2,temp = 0;

        maxLeft = maxSubArray(nums,left,center);
        maxRight = maxSubArray(nums,center + 1,right);

        if (maxLeft > maxRight) {
            max = maxLeft;
        }else{
            max = maxRight;
        }

        lefts = nums[center];
        rights = nums[center+1];
        //寻找左半边到中间的最大子序列
        for (int i = center; i >=left; i--) {
            temp += nums[i];
            if (temp > lefts) {
                lefts = temp;
            }
        }

        temp = 0;
        for (int i = center+1; i <= right; i++) {
            temp += nums[i];
            if (temp > rights) {
                rights = temp;
            }
        }

        if (lefts + rights > max) {
            max = lefts + rights;
        }
        return max;
    }
}
