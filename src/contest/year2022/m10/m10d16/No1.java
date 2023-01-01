package contest.year2022.m10.m10d16;

import java.util.HashSet;
import java.util.Set;

public class No1 {
    public int findMaxK(int[] nums) {
        int n = nums.length,res = -1;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if(nums[i] < 0)
                set.add(nums[i]);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && set.contains(-nums[i])) {
                res = Math.max(res,nums[i]);
            }
        }

        return res;
    }
}
