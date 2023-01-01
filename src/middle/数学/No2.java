package middle.数学;

public class No2 {
    //              阶乘后的零
    //给定一个整数 n ，返回 n! 结果中尾随零的数量。
    //提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
    //进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？

    //----------------------------------------------------------------------
    //失败，根本计算不出来后面的一些阶乘
    /*public int trailingZeroes(int n) {
        int res = 0;
        double temp = 1;
        if (n == 0) {
            return res;
        }

        for (int i = 2; i <= n; i++) {
            temp = i * temp;
            if (temp % 10 == 0) {
                res++;
                temp /= 10;
            }
        }

        return res;
    }*/
    //--------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------
    //思路是对的，但是穷举的效率太低，23ms
    //端正心态，就是因为自己想不出来才要学习，对不对
    //试着思考一下，在阶乘中尾随的零与什么有关？只与乘法中2和5的个数有关吗
    /*public int trailingZeroes(int n){
        int res = 0,temp,count1 = 0,count2 = 0;
        boolean flag;

        for (int i = 2; i <= n; i++) {
            temp = i;
            flag = true;
            while (flag) {
                if (temp % 2 == 0) {
                    count1++;
                    temp /= 2;
                } else if (temp % 5 == 0) {
                    count2++;
                    temp /= 5;
                }else{
                    flag = false;
                }
            }
        }

        if (count1 > count2) {
            res = count2;
        }else{
            res = count1;
        }

        return res;
    }*/
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    //效率是提高了一些，但是还是不算很好
    //如何提高效率？只记录当前数中的2和5的倍数，并且使用取对数运算来计算
    /*public int trailingZeroes(int n){
        int count1 = 0,count2 = 0,temp;
        boolean flag;

        for (int i = 2; i <= n; i += 2) {
            temp = i;
            flag = true;
            while (flag) {
                if (temp % 2 != 0) {
                    flag = false;
                }
                count1++;
                temp /= 2;
            }
        }

        for (int i = 5; i <= n; i += 5) {
            temp = i;
            flag = true;
            while (flag) {
                if (temp % 5 != 0) {
                    flag = false;
                }
                count2++;
                temp /= 5;
            }
        }

        return count1 > count2 ? count2 : count1;
    }*/
    //---------------------------------------------------------------

    //---------------------------------------------------------------
    //3ms，仍然有优化的空间
    //在一个阶乘中，2的数量是远远大于5的，所以我们只需要统计5的数量就可以了
    /*public int trailingZeroes(int n){
        int count2 = 0,temp;

        for (int i = 5; i <= n; i += 5) {
            temp = i;
            while (true) {
                if (temp % 5 != 0) {
                    break;
                }
                count2++;
                temp /= 5;
            }
        }

        return count2;
    }*/
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    //0ms，归纳得出求5的规律，每5个数就会有一个5为公因子的数
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
}
