package contest.year2022.m11.d6;

public class No2 {
    //妈的，欠缺了思考，导致出问题了
    /*public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length,l = 0,r = 0;
        int[] bucket = new int[100001];
        long res = 0,sum = 0;

        while (r < n) {
            if (bucket[nums[r]] != 0) {
                l = r;
                bucket = new int[100001];
                sum = 0;
            }
            sum += nums[r];
            bucket[nums[r]]++;
            if (r - l + 1 == k) {
                res = Math.max(sum, res);
                bucket[nums[l]]--;
                sum -= nums[l];
                l++;
            }

            r++;
        }

        return res;
    }*/

    //通过
    public long maximumSubarraySum(int[] nums,int k){
        int n = nums.length,l = 0,r = 0;
        int[] bucket = new int[100001];
        long res = 0,sum = 0;

        while (r < n) {
            while (bucket[nums[r]] != 0) {
                sum -= nums[l];
                bucket[nums[l]]--;
                l++;//这里出了问题
            }
            sum += nums[r];
            bucket[nums[r]]++;
            if (r - l + 1 == k) {
                res = Math.max(sum, res);
                bucket[nums[l]]--;
                sum -= nums[l];
                l++;
            }

            r++;
        }

        return res;
    }
}
