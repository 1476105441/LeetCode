package contest.year2023.m4.d23;

/**
 * 2652.倍数求和
 *
 * @author wjs 2023/6/11
 */
public class No2 {
    public int sumOfMultiples(int n) {
        int res = 0;
        for(int i=3;i <= n;i++) {
            if(check(i)) {
                res += i;
            }
        }
        return res;
    }
    private boolean check(int val) {
        return val % 3 == 0 || val % 5 == 0 || val % 7 == 0;
    }
}
