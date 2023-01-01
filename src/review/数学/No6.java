package review.数学;

public class No6 {
    /**
     *      阶乘后的零
     */

    //首先思考，阶乘后的0和什么有关？
    //只有2*5会得到10是结果中带有0的
    //所以我们只需要统计2和5的数量，就
    //可以知道阶乘中有多少0了，但是在
    //一般情况下，2的数量远大于5的数量，
    //所以我们还需要换一种思路，统计5的
    //数量即可
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res += n/5;
            n /= 5;
        }
        return res;
    }
}
