package contest.year2022.m11.d20;

public class No1 {
    //t1
    public int unequalTriplets(int[] nums) {
        int n = nums.length, res = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[j] != nums[i])
                    for (int k = j + 1; k < n; k++) {
                        if (nums[k] != nums[j] && nums[k] != nums[i])
                            res++;
                    }
            }
        }

        return res;
    }
}
