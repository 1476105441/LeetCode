package contest.year2023.m2.d18;

import java.util.HashSet;
import java.util.Set;

public class No3 {
    public int minImpossibleOR(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < 31; i++) {
            if(!set.contains(1<<i)) return 1 << i;
        }
        return -1;
    }
}
