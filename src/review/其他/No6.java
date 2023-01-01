package review.其他;

public class No6 {
    /**
     *      缺失数字
     */

    //解法一：求和法
    /*public int missingNumber(int[] nums) {
        int t1 = 0,t2 = 0;
        for (int i = 0; i < nums.length; i++) {
            t1 += nums[i];
            t2 += i;
        }
        t2 += nums.length;

        return t2 - t1;
    }*/

    //解法二：位运算法
    /*public int missingNumber(int[] nums){
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i] ^ (i+1);
        }
        return res;
    }*/

    //解法三：等差数列求和公式
    public int missingNumber(int[] nums){
        int n = nums.length;
        int sum1 = 0,sum2 = n*(n+1)/2;
        for (int i = 0; i < nums.length; i++) {
            sum1 += nums[i];
        }
        return sum2 - sum1;
    }
}
