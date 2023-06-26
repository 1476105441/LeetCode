package contest.year2023.m4.d29;

/**
 * k个元素的最大和
 *
 * @author wjs 2023/6/26
 */
public class No1 {
    public int maximizeSum(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        for(int i=0;i < n;i++) {
            max = Math.max(max,nums[i]);
        }
        return max * k + (k-1) * k / 2;
    }
}
