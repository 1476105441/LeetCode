package 周赛.m7d23;

public class No2 {
    /**
     *     全 0 子数组的数目
     */

    long[] temp = new long[100001];
    {
        temp[1] = 1;
        for (int i = 2; i < 100001; i++) {
            temp[i] = i + temp[i-1];
        }
    }
    public long zeroFilledSubarray(int[] nums) {
        long res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int j;
                for ( j = i+1; j < nums.length; j++) {
                    if (nums[j] != 0)
                        break;
                }
                res += temp[j-i];
                i = j;
            }
        }

        return res;
    }
}
