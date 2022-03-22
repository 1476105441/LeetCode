package 中级算法.数学;

public class No5 {
    //              x 的平方根
    //给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
    //由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
    //注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。

    //穷举法不行，直接超时，需要改进改进
    //-------------------------------------------------------------
    /*public int mySqrt(int x) {
        int temp = 0,i = 1;

        while (true) {
            temp = i * i;
            if (temp > x) {
                break;
            }
            i++;
        }

        return i-1;
    }*/
    //-------------------------------------------------------------------

    //-------------------------------------------------------------------
    //              成功，使用二分查找，1ms
    public int mySqrt(int x){
        long low = 0,high = x,center,res = 0,temp;

        while (low <= high) {
            center = low + (high - low) / 2;
            temp = center * center;
            if (temp > x) {
                temp = (center - 1) * (center - 1);
                if (temp > x) {
                    high = center - 1;
                }else{
                    res = center - 1;
                    break;
                }
            }else{
                temp = (center + 1) * (center + 1);
                if (temp > x) {
                    res = center;
                    break;
                }else{
                    low = center + 1;
                }
            }
        }

        return (int)res;
    }
}
