package contest.year2023.m2.d18;

public class No2 {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        int max = 0,min = 0,l1 = -1,l2 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                l1 = i;
            }
            if (nums[i] < min) {
                min = nums[i];
                l2 = i;
            }
        }
        max = 0;
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == l1 || i == l2) {
                continue;
            }
            max = Math.max(max,nums[i]);
            min = Math.min(min,nums[i]);
        }
        int res = Math.min(changeMin(nums),changeMax(nums));
        return Math.min(max - min,res);
    }
    private int changeMin(int[] nums){
        int n = nums.length;
        int min = Integer.MAX_VALUE,second = Integer.MAX_VALUE,mLoc = -1,sLoc = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (min > nums[i]) {
                sLoc = mLoc;
                mLoc = i;
                second = min;
                min = nums[i];
            } else if (second > nums[i]) {
                second = nums[i];
                sLoc = i;
            }
            max = Math.max(max,nums[i]);
        }
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == mLoc || i == sLoc) {
                continue;
            }
            min = Math.min(min,nums[i]);
        }
        return max - min;
    }
    private int changeMax(int[] nums){
        int n = nums.length;
        int max = 0,second = 0,mLoc = -1,sLoc = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (max < nums[i]) {
                sLoc = mLoc;
                mLoc = i;
                second = max;
                max = nums[i];
            } else if (second < nums[i]) {
                second = nums[i];
                sLoc = i;
            }
            min = Math.min(min,nums[i]);
        }
        max = 0;
        for (int i = 0; i < n; i++) {
            if (i == mLoc || i == sLoc) {
                continue;
            }
            max = Math.max(max,nums[i]);
        }
        return max - min;
    }
}
