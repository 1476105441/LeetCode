package review.数学;

public class No3 {
    /**
     *      3的幂
     */

    //使用数学公式的解法
    /*public boolean isPowerOfThree(int n) {
        double tmp = Math.log10(n) / Math.log10(3);

        return tmp % 1 == 0;
    }*/

    //这里有个大坑，使用log函数时会出现精度缺失，使用log10函数才行
    public boolean isPowerOfThree(int n) {
        double tmp = Math.log10(n) / Math.log10(3);
        int temp = (int) tmp;
        return temp == tmp;
    }
}
