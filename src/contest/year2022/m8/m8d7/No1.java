package contest.year2022.m8.m8d7;

import java.util.*;

public class No1 {
    /**
     *      算术三元组的数目
     */

    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length,res = 0;

        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < n; i++) {
            if (set.contains(nums[i] + diff)) {
                if (set.contains(nums[i] + diff + diff)) {
                    res++;
                }
            }
        }
        return res;
    }
}
