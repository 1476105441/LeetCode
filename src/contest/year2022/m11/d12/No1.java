package contest.year2022.m11.d12;

import java.util.HashSet;
import java.util.Set;

public class No1 {
    public int distinctAverages(int[] nums) {
        int n = nums.length,res = 0,min,max,count = n;
        Set<Double> set = new HashSet<>();
        while (count > 0) {
            int j = -1,k = -1;
            min = Integer.MAX_VALUE;
            max = -1;
            for (int i = 0; i < n; i++) {
                if (nums[i] != -1 && min > nums[i]) {
                    min = nums[i];
                    j = i;
                }
                if (nums[i] != -1 && max < nums[i]) {
                    max = nums[i];
                    k = i;
                }
            }
            nums[j] = -1;
            nums[k] = -1;
            double temp = ((double) min + (double)max) / 2;
            if (!set.contains(temp)) {
                set.add(temp);
                res++;
            }
            count -= 2;
        }
        return res;
    }
}
