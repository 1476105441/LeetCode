package contest.year2022.m7.m7d17;

import java.util.Arrays;

public class No4 {
    public int minOperations(int[] nums, int[] numsDivide) {
        int m = nums.length,n = numsDivide.length,min = numsDivide[0];
        for (int i = 1; i < n; i++) {
            min = gcd(min,numsDivide[i]);
        }
        Arrays.sort(nums);
        int c = 0;
        for (int i = 0; i < m; i++) {
            if(min % nums[i] == 0)
                return c;
            c++;
        }
        return -1;
    }
    public int gcd(int x,int y){
        while (y > 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
}
