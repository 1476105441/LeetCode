package contest.year2023.m1.d1;

import java.util.HashSet;
import java.util.Set;

public class No2 {
    public int distinctPrimeFactors(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int total = nums[i];
            for (int j = 2; j * j <= total; j++) {
                if (total % j == 0) {
                    for (total /= j; total % j == 0; total /= j) {
                    }
                    set.add(j);
                }
            }
            if(total > 1)
                set.add(total);
        }
        return set.size();
    }
}
