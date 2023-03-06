package contest.year2023.m3.d4;

import java.util.Arrays;

public class No3 {
    int mod = 1000000007;
    public int countWays(int[][] ranges) {
        int n = ranges.length;
        Arrays.sort(ranges,(x, y) -> x[0]-y[0]);
        int cnt = 1,s = ranges[0][0],e = ranges[0][1];
        for(int i = 0;i < n;i++){
            if(ranges[i][0] <= e){
                e = Math.max(e,ranges[i][1]);
                continue;
            }
            cnt++;
            s = ranges[i][0];
            e = ranges[i][1];
        }
        return count(2,cnt);
    }
    private int count(long a,int b){
        long res = 1;
        while(b > 0){
            if((b & 1) == 1){
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return (int)(res % mod);
    }
}
