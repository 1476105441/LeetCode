package contest.year2023.m1.d21;

public class No4 {
    /**
     * 思考步骤：
     * 前置知识是二进制gcd，另一种快速求出gcd的方法
     * 有了二进制gcd知识储备之后，可以从targetX和targetY出发，倒
     * 过来走到(1,1)，倒过来的操作也就是两边都可以除以2，两边都可以
     * 加上对方
     */
    public boolean isReachable(int targetX, int targetY) {
        int gcd = gcd(targetX, targetY);
        while (gcd % 2 == 0) {
            gcd /= 2;
        }
        return gcd == 1;
    }
    //欧几里得算法求gcd
    /*private static int gcd(int x,int y){
        while (y > 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }*/

    //二进制gcd
    private static int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }
        if (y == 0) {
            return x;
        }
        if ((~x & 1) == 1) {
            if ((~y & 1) == 1) {
                return gcd(x >> 1, y >> 1) << 1;
            }
            return gcd(x >> 1, y);
        }
        if ((~y & 1) == 1) {
            return gcd(x, y >> 1);
        }
        if (x > y) {
            return gcd((x - y) >> 1, y);
        }
        return gcd(x, (y - x) >> 1);
    }
}
