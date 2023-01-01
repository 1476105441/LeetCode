package review.数学;

public class No10 {
    /**
     *      两数相除
     */

    //不使用除法，那就使用减法？
    /*public int divide(int dividend, int divisor) {
        boolean flag = false;
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0)
            flag = true;
        if (divisor == -2147483648) {
            if(dividend == -2147483648)
                return 1;
            else
                return 0;
        }
        long x,y;
        x = Math.abs(dividend);
        y = Math.abs(divisor);
        if (dividend == -2147483648) {
            x = 2147483648L;
        }

        int res = count(x, y);
        if(flag)
            res = -res;
        return res;
    }

    public int count(long x,long y){
        int c = 0;
        while (x > y) {
            x -= y;
            c++;
        }
        return c;
    }*/

    int res;
    long x;
    public int divide(int dividend, int divisor) {
        boolean flag = false;
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0)
            flag = true;
        if (divisor == -2147483648) {
            if(dividend == -2147483648)
                return 1;
            else
                return 0;
        }else if(divisor == 1)
            return dividend;
        long y;
        x = Math.abs(dividend);
        y = Math.abs(divisor);
        if (dividend == -2147483648) {
            if(divisor == -1) {
                return 2147483647;
            }
            x = 2147483648L;
        }

        count(y,1);

        if(flag)
            res = -res;
        return res;
    }
    public void count(long y,int c){
        if(y > x)
            return;
        long ty = y+y;
        int tc = c+c;
        count(ty,tc);
        if (y <= x) {
            x -= y;
            res += c;
        }
    }
}
