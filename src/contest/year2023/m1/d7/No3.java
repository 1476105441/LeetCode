package contest.year2023.m1.d7;

public class No3 {
    public int xorBeauty(int[] nums) {
        int n = nums.length,res = 0;
        for (int i = 0; i < n; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
