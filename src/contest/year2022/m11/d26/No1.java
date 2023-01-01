package contest.year2022.m11.d26;

public class No1 {
    public int numberOfCuts(int n) {
        if(n == 1)
            return 0;
        if((n & 1) == 1)
            return n;
        else
            return n/2;
    }
}
