package contest.year2023.m3.d19;

public class No1 {
    public int[] evenOddBit(int n) {
        int[] res = new int[2];
        int t = 0;
        while(n > 0){
            if((n & 1) == 1){
                if((t&1) == 1){
                    res[1]++;
                }else{
                    res[0]++;
                }
            }
            t++;
            n >>>=1;
        }
        return res;
    }
}
