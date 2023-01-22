package contest.year2023.m1.d22;

import java.util.Arrays;

public class No2 {
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score,(x,y) -> y[k] - x[k]);
        return score;
    }
}
