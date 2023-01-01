package review.其他;

public class No3 {
    /**
     *      颠倒二进制位
     */


    //解法一：逐位反转
    /*public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            if ((n & 1) == 1) {
                res += 1;
            }
            res = res << 1;
            n = n>>1;
        }
        if ((n & 1) == 1) {
            res += 1;
        }
        return res;
    }*/

    //解法二：分治
    //先11调换，然后22调换，44调换，88调换，1616调换
    public int reverseBits(int n) {
        int res = n,t1 = 0x55555555,t2 = 0x33333333,t3 = 0x0f0f0f0f,t4 = 0x00ff00ff,t5 = 0x0000ffff;
        res = (res>>>1) & t1 | (res & t1) << 1;
        res = (res>>>2) & t2 | (res & t2) << 2;
        res = (res>>>4) & t3 | (res & t3) << 4;
        res = (res>>>8) & t4 | (res & t4) << 8;
        res = (res>>>16) & t5 | (res & t5) << 16;
        return res;
    }
}
