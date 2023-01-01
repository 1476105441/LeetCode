package junior.其他;

public class No2 {
    //               汉明距离
    //  两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
    //给你两个整数 x 和 y，计算并返回它们之间的汉明距离。

    //思想：可以先将x和y进行^运算，只有两者的二进制位不相同才会是1，再将每位与1进行&运算统计为1的个数
    //                成功，最优解
    public int hammingDistance(int x, int y) {

        int temp = x ^ y;

        int res = 0;

        for (int i = 1; i < 33; i++) {
            if ((temp & 1) == 1) {
                res++;
            }

            temp = temp >> 1;
        }

        return res;
    }
}
