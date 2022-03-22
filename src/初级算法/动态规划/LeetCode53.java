package 初级算法.动态规划;

public class LeetCode53 {
    //初级算法.动态规划
    public static int maxSubArray(int[] nums) {
        //该数组用来存放以i为结尾的连续子数组的最大和
        int[] f = new int[nums.length];
        //pre存放最大和
        int pre;
        //设置第一个元素的最大和为自身
        f[0] = nums[0];
        pre = f[0];
        for (int i = 1; i < nums.length; i++) {
            f[i] = (f[i - 1] + nums[i]) > nums[i] ? (f[i - 1] + nums[i]) : nums[i];
            if (f[i] > pre) {
                pre = f[i];
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        //System.out.println(maxSubArray(nums));
        System.out.println("leetcode53正在运行");
    }
}
