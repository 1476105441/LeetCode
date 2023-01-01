package review.字符串;

public class No2 {
    /**
     *      整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */

    //注意审题，不允许存储64位整数，翻转过来后可能超出32位整数范围
    public int reverse(int x){
        int res = 0,flag1 = 214748364,flag2 = -214748364;
        while (x != 0) {
            int t = x % 10;
            if ((res > flag1 || (res ==flag1 && t > 7)) || (res < flag2 || ( res == flag2 && t < -8))) {
                return 0;
            }
            res = res * 10 + t;
            x /= 10;
        }
        return res;
    }
}
