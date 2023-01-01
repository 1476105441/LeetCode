package contest.year2022.m8.m8d28;

import java.util.Arrays;

public class No1 {
    /**
     *      和有限的最长子序列
     */

    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length,m = queries.length;
        int[] res = new int[m];
        Arrays.sort(nums);

        for (int i = 0; i < m; i++) {
            int sum = 0,j = 0;
            while (j < n && sum + nums[j] <= queries[i]) {
                sum += nums[j];
                j++;
            }
            res[i] = j;
        }

        return res;
    }
}
