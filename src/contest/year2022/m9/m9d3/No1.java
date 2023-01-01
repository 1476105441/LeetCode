package contest.year2022.m9.m9d3;

import java.util.HashSet;
import java.util.Set;

public class No1 {
    /**
     *      和相等的子数组
     */

    public boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length-1; i ++) {
            int sum = nums[i] + nums[i+1];
            if (set.contains(sum)) {
                return true;
            }
            set.add(sum);
        }
        return false;
    }
}
