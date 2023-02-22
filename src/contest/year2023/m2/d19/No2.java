package contest.year2023.m2.d19;

public class No2 {
    //如果前面一位为1，则最好加上1
    public int minOperations(int n) {
        int[] set = new int[32];
        int temp = n,loc = 31;
        while (temp > 0) {
            set[loc] = temp & 1;
            loc--;
            temp = temp >>> 1;
        }
        int res = 0;
        for (int i = 31; i > -1; i--) {
            if (set[i] == 1) {
                res++;
                boolean flag = false;
                while (i > 0 && set[i - 1] == 1) {
                    i--;
                    flag = true;
                }
                if (flag && i > 0) {
                    set[i-1] = 1;
                }
            }
        }
        return res;
    }
}
