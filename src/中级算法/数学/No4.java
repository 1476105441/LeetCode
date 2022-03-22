package 中级算法.数学;

public class No4 {
    //              Pow(x, n)
    //实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1/x;
        }
        double temp;
        temp = myPow(x,n/2);
        if ((n & 1) != 0) {
            if (n < 0) {
                return temp * temp * (1/x);
            }
            return temp * temp * x;
        }else{
            return temp * temp;
        }
    }
}
