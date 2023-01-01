package review.数学;

public class No9 {
    /**
     *      x 的平方根
     */

    //二分查找
    public int mySqrt(int x) {
        int l = 0,r = 46340,c,res = -1;
        while (l <= r) {
            c = l+((r-l)>>1);
            if (c * c > x) {
                r = c-1;
            } else if (c * c < x) {
                l = c+1;
                res = c;
            }else
                return c;
        }
        return res;
    }
}
