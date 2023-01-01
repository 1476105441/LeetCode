package contest.year2022.m8.m8d6;

public class No4 {
    /**
     *      将数组排序的最少替换次数
     */

    /*public long minimumReplacement(int[] nums) {
        int n = nums.length,t = nums[n-1];
        long res = 0;

        for (int i = n-2; i >= 0; i--) {
            int v = nums[i] / t - 1;
            if (nums[i] % t != 0) {
                t = nums[i] % t;
                v++;
            }
            res += v;
        }

        return res;
    }*/

    public long minimumReplacement(int[] nums) {
        int n = nums.length,t = nums[n-1];
        long res = 0;

        for (int i = n-2; i >= 0; i--) {
            if (nums[i] < t) {
                t = nums[i];
                continue;
            }
            int v = nums[i] / t - 1;
            if (nums[i] % t != 0) {
                v++;
                t = nums[i] / (v+1);
            }
            res += v;
        }

        return res;
    }
}
