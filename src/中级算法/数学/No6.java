package 中级算法.数学;

public class No6 {
    //              两数相除
    //给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
    //返回被除数 dividend 除以除数 divisor 得到的商。
    //整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

    //-----------------------------------------------------------------------
    //                  肯定超出时间限制，太慢了
    /*public int divide(int dividend, int divisor) {
        long res = 0,temp1 = dividend,temp2 = divisor;
        if (dividend < 0) {
            temp1 = Math.abs(temp1);
        }
        if (divisor < 0) {
            temp2 = Math.abs(temp2);
        }
        while (temp1 > temp2) {
            temp1 -= temp2;
            res++;
        }

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            return (int)(res * -1);
        }
        return (int)res;
    }*/
    //---------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    //              二分查找+快速乘
    public int divide(int dividend, int divisor){
        int res = 0,temp1 = dividend,temp2 = divisor,low = 1,high = 2147483647,center;
        //处理边界情况，将所有取值为最小值或者会溢出的情况进行特别处理
        //因为二分查找时的查找范围是1——2147483647
        //除数为最小值时也得进行特殊处理，也是因为我们进行快速乘的方法
        //无法处理除数为最小值的情况
        if (dividend == 0) {
            return 0;
        } else if (dividend == -2147483648) {
            if (divisor == -1) {
                return 2147483647;
            } else if (divisor == 1) {
                return -2147483648;
            } else if (divisor == -2147483648) {
                return 1;
            }
        }else if (divisor == -2147483648) {
            return 0;
        }

        //将除数和被除数全部整理为负数来进行处理
        if (dividend > 0) {
            temp1 = dividend * -1;
        }
        if (divisor > 0) {
            temp2 = divisor * -1;
        }

        while (low <= high) {
            center = low + ((high - low) >> 1);
            if (quickAdd(temp2, center, temp1)) {
                res = center;
                if (center == Integer.MAX_VALUE) {
                    break;
                }
                low = center + 1;
            }else{
                high = center - 1;
            }
        }

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            res = res * -1;
        }

        return res;
    }

    // 快速乘，计算zy的值
    /*public static int quickAdd(int y, int z) {
        int add = y;

        if (z == 1) {
            return y;
        }
        //z为奇数时
        add += add;
        if ((z & 1) != 0) {
            z = z >> 1;
            return quickAdd(add,z) + y;
        }else{
            z = z >> 1;
            return quickAdd(add,z);
        }
    }*/

    //快速乘，不使用递归的方法
    //如此直接计算出答案的方法将会导致你的结果可能会溢出（在此题目中），因为若
    // 是两个很大的负数相乘，其结果会超出32位的乘积
    public boolean quickAdd(int y, int z,int x) {
        int add = y,res = 0;

        while (z != 0) {

            if ((z & 1) != 0) {
                if (res < x - add) {
                    return false;
                }
                res += add;
            }

            if (z != 1) {
                if (add < x - add) {
                    return false;
                }
                add += add;
            }

            z = z >> 1;
        }

        return true;
    }
}
