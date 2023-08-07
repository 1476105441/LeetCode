package contest.year2023.m5.d13;

import java.util.Arrays;

/**
 * No2681.英雄的力量
 *
 * @author wjs 2023/8/7
 */
public class No4 {
    //瓶颈在于遍历所有的组合
    //怎么思考？
    //1、由于是取组合数，所以数组中元素的顺序不影响结果，可以进行排序。
    //2、随便选出几个数字，寻找规律。
    public int sumOfPower(int[] nums) {
        int n = nums.length, mod = 1000000007;
        Arrays.sort(nums);
        long res = 0;
        long val = 0;
        for(int i=0;i < n;i++) {
            int v = nums[i];
            res = (res + ((long)v * v % mod) * (v + val) % mod) % mod;
            val = (2 * val + v) % mod;
        }
        return (int)res;
    }
}
