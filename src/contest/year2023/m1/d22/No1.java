package contest.year2023.m1.d22;

public class No1 {
    //细节不对
    /*public int alternateDigitSum(int n) {
        int res = 0,c = 10;
        while (c < n) {
            c *= 10;
        }
        c /= 10;
        int flag = 0;
        while (c > 0) {
            if ((flag & 1) == 0) {
                res += n / c;
            } else {
                res -= n / c;
            }
            n = n % c;
            flag++;
            c /= 10;
        }
        return res;
    }*/

    public int alternateDigitSum(int n) {
        long res = 0,c = 10,temp = n;
        while (c <= n) {
            c *= 10;
        }
        c /= 10;
        int flag = 0;
        //System.out.println(c);
        while (c > 0) {
            if ((flag & 1) == 0) {
                res += temp / c;
            } else {
                res -= temp / c;
            }
            temp = temp % c;
            flag++;
            c /= 10;
        }
        return (int)res;
    }
}
