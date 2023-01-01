package contest.year2022.m9.m9d25;

public class No2 {
    /**
     *      按位与最大的最长子数组
     */
    public int longestSubarray(int[] nums) {
        int n = nums.length,max = 0,res = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(nums[i],max);
        }
        int temp = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == max)
                temp++;
            else
                temp = 0;
            res = Math.max(res,temp);
        }
        return res;
    }
}
