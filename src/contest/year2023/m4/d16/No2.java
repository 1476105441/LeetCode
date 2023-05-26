package contest.year2023.m4.d16;

/**
 * 2644. 找出可整除性得分最大的整数
 *
 * @author wjs 2023/5/27
 */
public class No2 {
    public int maxDivScore(int[] nums, int[] divisors) {
        int res = Integer.MAX_VALUE, max = 0;
        int n = divisors.length, m = nums.length;
        for(int i=0;i < n;i++){
            int v = divisors[i],c = 0;
            for(int j=0;j < m;j++){
                if(nums[j] % v == 0){
                    c++;
                }
            }
            if(c > max){
                max = c;
                res = v;
            } else if(c == max){
                res = Math.min(v,res);
            }
        }
        return res;
    }
}
