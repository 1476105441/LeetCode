package contest.year2023.m5.d13;

import java.util.Arrays;

/**
 * 2679.矩阵中的和
 *
 * @author wjs 2023/8/5
 */
public class No2 {
    public int matrixSum(int[][] nums) {
        int res = 0;
        int m = nums.length;
        int n = nums[0].length;
        for(int i=0;i < m;i++) {
            Arrays.sort(nums[i]);
        }
        for(int i=n-1;i >= 0;i--) {
            int max = 0;
            for(int j=0;j < m;j++) {
                max = Math.max(max,nums[j][i]);
            }
            res += max;
        }
        return res;
    }
}
