package contest.year2022.m7.m7d10;

public class No1 {
    /**
     *      装满杯子需要的最短总时长
     */


    public int fillCups(int[] amount) {
        int res = 0,max,maxi,next,nexti,zero = 0;

        while (true) {
            max = Integer.MIN_VALUE;
            next = Integer.MIN_VALUE;
            maxi = 0;
            nexti = 0;
            for (int i = 0; i < 3; i++) {
                if (amount[i] == 0) {
                    zero++;
                }
                if (amount[i] > max) {
                    max = amount[i];
                    maxi = i;
                }
            }
            for (int i = 0; i < 3; i++) {
                if (i == maxi) {
                    continue;
                }
                if (amount[i] > next) {
                    next = amount[i];
                    nexti = i;
                }
            }
            if (zero >= 2) {
                break;
            }
            amount[maxi]--;
            amount[nexti]--;
            res++;
            zero = 0;
        }

        for (int i = 0; i < 3; i++) {
            res += amount[i];
        }
        return res;
    }
}
