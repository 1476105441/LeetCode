package contest.year2022.m10.m10d30;

public class No3 {
    //美丽整数：每个位相加的和小于target
    //每一个位可以选择向前一位进一，这样可以减少一些值
    public long makeIntegerBeautiful(long n, int target) {
        long res = 0,temp = n,bit = 1;
        //需要减少的数量
        int c = count(n),pre = 0;//pre 代表进位
        if(c <= target)
            return 0;
        else
            c -= target;

        while (c > 0) {
            int now = (int) ((temp % (10*bit)) / bit + pre);
            if (now != 0) {
                pre = 1;
                c = c - now + 1;
                res += (10 - now) * bit;
            }
            bit *= 10;
        }

        return res;
    }
    //计算每个位相加的和
    public int count(long n){
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}
