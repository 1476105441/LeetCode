package contest.year2022.m10.m10d9;

public class No2 {
    /**
     *      找出前缀异或的原始数组
     */

    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] res = new int[n];
        res[0] = pref[0];

        for (int i = 1; i < n; i++) {
            res[i] = pref[i] ^ pref[i-1];
        }

        return res;
    }
}
