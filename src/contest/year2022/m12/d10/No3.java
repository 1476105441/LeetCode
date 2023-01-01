package contest.year2022.m12.d10;

public class No3 {
    public int maxJump(int[] stones) {
        int n = stones.length,res = stones[1] - stones[0];

        for (int i = 0; i < n-2; i++) {
            int temp = stones[i+2] - stones[i];
            res = Math.max(res,temp);
        }

        return res;
    }
}
