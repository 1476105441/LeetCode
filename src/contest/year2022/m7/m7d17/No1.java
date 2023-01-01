package contest.year2022.m7.m7d17;

public class No1 {
    public int[] numberOfPairs(int[] nums) {
        int n = nums.length;
        int[] res = new int[2],set = new int[101];

        for (int i = 0; i < n; i++) {
            set[nums[i]]++;
        }

        for (int i = 0; i < 101; i++) {
            res[0] += set[i] / 2;
            res[1] += set[i] % 2;
        }

        return res;
    }
}
