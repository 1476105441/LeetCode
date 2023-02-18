package contest.year2023.m2.d6;

public class No1 {
    public long findTheArrayConcVal(int[] nums) {
        int n = nums.length,l = 0,r = n-1;
        long res = 0;
        while (l < r) {
            int bit = 1,temp = nums[r];
            while (temp > 0) {
                bit *= 10;
                temp /= 10;
            }
            res += (long)nums[l] * bit + nums[r];
            l++;
            r--;
        }
        if (l == r) {
            res += nums[l];
        }
        return res;
    }
}
