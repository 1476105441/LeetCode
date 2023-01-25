package contest.year2023.m1.d21;

public class No4 {
    public boolean isReachable(int targetX, int targetY) {
        int gcd = gcd(targetX, targetY);
        while (gcd % 2 == 0) {
            gcd /= 2;
        }
        return gcd == 1;
    }
    private int gcd(int x,int y){
        while (y > 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
}
