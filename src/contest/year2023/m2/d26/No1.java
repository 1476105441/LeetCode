package contest.year2023.m2.d26;

public class No1 {
    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length,rSum = 0,lSum = 0;
        for(int i = 0;i < n;i++){
            rSum += nums[i];
        }
        int[] res = new int[n];
        for(int i = 0;i < n;i++){
            rSum -= nums[i];
            res[i] = Math.abs(lSum - rSum);
            lSum += nums[i];
        }
        return res;
    }
}
