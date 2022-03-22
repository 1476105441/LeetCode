package 中级算法复习.数学;

public class No4 {
    //              Pow(x, n)

    public double myPow(double x,int n){
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1/x;
        }
        double res ,temp;
        temp = myPow(x,n/2);
        if ((n & 1) == 1) {
            if (n < 0) {
                res = temp * temp * (1/x);
            }else{
                res = temp * temp * x;
            }
        }else{
            res = temp * temp;
        }
        return res;
    }
}
