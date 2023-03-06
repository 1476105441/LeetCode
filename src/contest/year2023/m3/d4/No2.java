package contest.year2023.m3.d4;

public class No2 {
    public long coloredCells(int n) {
        long res = (long)n * (n-1) * 2 + 1;
        return res;
    }
}
