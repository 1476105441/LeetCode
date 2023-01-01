package contest.year2022.m10.m10d2;

public class No1 {
    /**
     *      公因子的数目
     */

    public int commonFactors(int a, int b) {
        int min = Math.min(a,b),res = 0;
        for (int i = 1; i <= min; i++) {
            if(a % i == 0 && b % i == 0)
                res++;
        }
        return res;
    }
}
