package 中级算法复习.数学;

public class No2 {
    //          阶乘后的零

    /*public int trailingZeroes(int n){
        int count = 0;
        while (n > 1) {
            count += n / 5;
            count += n / 25;
            count += n / 125;
            n /= 125;
        }

        return count;
    }*/

    /**
     * 思想：相当于从区间中抽出有五的数，初始时，区间长度为1
     * 每满五个区间就抽出一个包含五的数，长度为1的区间全部抽
     * 完后再将区间长度变为5，继续重复抽取，直到区间长度大于
     * 当前的数。
     * 算法中用n/5就是计算有多少个5，再让n自除5，再进行除5
     * 算术时，就相当于最开始的n除以25，以此类推，此算法就
     * 可以计算出n有多少个5
     */
    public int trailingZeroes(int n){
        int count = 0;
        while (n > 1) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
