package contest.year2022.m10.m10d16;

import java.util.HashSet;
import java.util.Set;

public class No2 {
    public int countDistinctIntegers(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
            set.add(reverse(nums[i]));
        }

        return set.size();
    }
    public int reverse(int num){
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
            res *= 10;
        }
        return res /= 10;
    }

    public static void main(String[] args) {
        No2 no2 = new No2();
        System.out.println(no2.countDistinctIntegers(new int[]{1, 13, 10, 12, 31}));
    }
}
