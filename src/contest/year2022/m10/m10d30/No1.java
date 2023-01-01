package contest.year2022.m10.m10d30;

public class No1 {
    public int averageValue(int[] nums) {
        int n = nums.length,sum = 0,count = 0;

        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 0 && nums[i] % 3 == 0) {
                sum += nums[i];
                count++;
            }
        }

        return count == 0 ? 0 : sum /count;
    }
}
