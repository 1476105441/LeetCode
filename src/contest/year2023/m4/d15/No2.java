package contest.year2023.m4.d15;

/**
 * 2640. 一个数组所有前缀的分数
 *
 * @author wjs 2023/5/18
 */
public class No2 {
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length, max=0;
        long sum=0;
        int[] convert = new int[n];
        long[] res = new long[n];
        for(int i=0;i < n;i++) {
            max = Math.max(max,nums[i]);
            convert[i] = max + nums[i];
            sum += convert[i];
            res[i] = sum;
        }
        return res;
    }
}
