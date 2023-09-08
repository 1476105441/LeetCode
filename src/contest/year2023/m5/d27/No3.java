package contest.year2023.m5.d27;

import java.util.Arrays;

/**
 * 2708.一个小组的最大实力值
 *
 * @author wjs 2023/8/25
 */
public class No3 {
    // 正数全都要，负数要偶数个
    public long maxStrength(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        long res = 0;
        Arrays.sort(nums);
        res = nums[n-1];
        int nIndex = -1;
        boolean flag = false, exec = false;
        if(res <= 0) {
            flag = true;
            if(res < 0) {
                nIndex = n-1;
            }
            res = 1;
        }
        for(int i = n-2;i >= 0;i--) {
            if(nums[i] > 0) {
                res *= nums[i];
                exec = true;
            } else if(nums[i] < 0) {
                if(nIndex == -1) {
                    nIndex = i;
                }
                break;
            }
        }
        if((nIndex % 2) == 0) {
            nIndex--;
        }
        for(int i=0;i <= nIndex;i++) {
            exec = true;
            res *= nums[i];
        }
        // 没执行过，并且第一个元素小于等于0，则直接返回0
        if(!exec && flag) {
            return 0;
        }
        return res;
    }
}
