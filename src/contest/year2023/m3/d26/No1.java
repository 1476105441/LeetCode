package contest.year2023.m3.d26;

public class No1 {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int res = 0;
        if(numOnes > k) {
            res = k;
        } else {
            res += numOnes;
            k -= numOnes;
            if(numZeros > k) return res;
            else k -= numZeros;

            res -= k;
        }
        return res;
    }
}
