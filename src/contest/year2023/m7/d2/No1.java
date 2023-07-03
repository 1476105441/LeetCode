package contest.year2023.m7.d2;

/**
 * 2760. 最长奇偶子数组
 *
 * @author wjs 2023/7/2
 */
public class No1 {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int res = 0;
        int n = nums.length, pre = -1, size = 0;
        for(int i=0;i < n;i++) {
            if(pre == -1 && nums[i] <= threshold && nums[i] % 2 == 0){
                //开启一个子数组
                pre = 0;
                size = 1;
            } else if(pre != -1) {
                if(nums[i] <= threshold && nums[i] % 2 != pre) {
                    size++;
                    pre = nums[i] % 2;
                } else {
                    res = Math.max(res,size);
                    if(nums[i] <= threshold && nums[i] % 2 == 0) {
                        size = 1;
                        pre = 0;
                    } else {
                        size = 0;
                        pre = -1;
                    }
                }
            }
        }
        if(pre != -1) {
            res = Math.max(res,size);
        }
        return res;
    }
}
