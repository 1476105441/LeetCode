package contest.year2023.m1.d8;

public class No4 {
    public int findCrossingTime(int n, int k, int[][] time) {
        return 0;
    }
    public boolean check(int[][] time,int i,int j){
        int c1 = time[i][0] + time[i][1],c2 = time[j][0] + time[j][1];
        return c1 > c2 || (c1 == c2 && i > j);
    }
}
