package contest.year2023.m1.d8;

public class No1 {
    public int maximumCount(int[] nums) {
        int n = nums.length,t1 = 0,t2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                t1++;
            } else if (nums[i] > 0) {
                t2++;
            }
        }
        return t1 > t2 ? t1 : t2;
    }
}
