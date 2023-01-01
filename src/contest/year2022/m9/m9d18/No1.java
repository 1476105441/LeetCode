package contest.year2022.m9.m9d18;

public class No1 {
    /**
     *      最小偶倍数
     */

    public int smallestEvenMultiple(int n) {
        if ((n & 1) == 0) {
            return n;
        }
        return n * 2;
    }
}
