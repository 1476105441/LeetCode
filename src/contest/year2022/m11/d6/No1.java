package contest.year2022.m11.d6;

public class No1 {
    //对数组执行操作

    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n-1; i++) {
            if (nums[i] != 0 && nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i+1] = 0;
            }
        }
        int l = 0,r = 0;
        while (r < n) {
            if (nums[r] != 0) {
                nums[l] = nums[r];
                l++;
            }
            r++;
        }
        while (l < n) {
            nums[l++] = 0;
        }

        return nums;
    }
}
