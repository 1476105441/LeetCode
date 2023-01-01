package review.数学;

public class No8 {
    /**
     *      Pow(x, n)
     */

    //分治法
    //注意要考虑x为1的特殊情况，还有指数如果为负数，也要特殊考虑
    //负数最小值-2147483648
    public double myPow(double x, int n) {
        if(x == 1 || x == 0)
            return x;
        long m = n;
        if(n < 0){
            m = -m;
            x = (1/x);
        }
        return count(x,m);
    }
    public double count(double x, long n){
        if(n == 1)
            return x;
        if(n == 0)
            return 1;
        double tmp = count(x, n >> 1);
        if((n & 1) == 1){
            return tmp * tmp * x;
        }else
            return tmp * tmp;
    }
}
