package 中级算法复习.数学;

public class No6 {
    //          两数相除

    /**
     *  想法：使用减法，减得掉就减掉然后再使被减数左移一位，直到
     * 减不掉为止
     *  注意：应该考虑正负数相除和值溢出问题
     */
    /*public int divide(int dividend,int divisor){
        int res = 0,count,temp1 = dividend,temp2,temp3 = divisor;
        boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        if (dividend == 0) {
            return 0;
        } else if (dividend == -2147483648 && divisor == -2147483648) {    //被除数与除数都为-2147483648的特殊情况
            return 1;
        } else if (dividend == -2147483648) {    //被除数为-2147483648的特殊情况
            if (divisor == 1) {
                return -2147483648;
            } else if (divisor == -1) {
                return 2147483647;
            }
            res++;
            //由于被除数为-2147483648，不能直接转换为绝对值（会溢出），所以采用先减掉一次除数的方法
            if(divisor > 0){
                temp1 += divisor;
            }else{
                temp1 -= divisor;
            }
            temp1 = Math.abs(temp1);
        } else if (divisor == -2147483648) {
            return 0;
        }

        if (temp1 < 0) {
            temp1 = Math.abs(temp1);
        }
        if (temp3 < 0) {
            temp3 = Math.abs(temp3);
        }

        while (temp1 >= temp3) {
            count = 1;
            temp2 = temp3;
            while (temp1 >= temp2) {
                temp1 -= temp2;
                res += count;
                if (temp2 > 1073741824) {
                    break;
                } else if (temp2 == 1073741824) {
                    break;
                }
                temp2 += temp2;
                count += count;
                //System.out.println(temp2);
            }
        }

        if (flag) {
            res *= -1;
        }

        return res;
    }*/

    /**
     *  另一解法：使用二分查找加快速乘来解决此问题
     *  由于这里求的是整除的结果，所以设被除数是x，除数是y，结果为z
     * 那么 z * y <= x 一定成立，所以我们只需要找到使此不等式成立
     * 的最大的数z，就是要求的结果
     *  难点：不能使用乘法，使用快速乘替代乘法，快速乘原理与快速幂相
     * 似，使用加法替代乘法
     *  注意点：如果把两个数都转换为正数来处理的话，就要特别处理负数
     * 的最小值-2147483648，但是如果把两个数都转为负数来处理的话就
     * 无需特殊处理最小值
     */
    public int divide(int dividend,int divisor){
        int res = 0,center,temp1 = dividend,temp2,temp3 = divisor,left = 1,right = 2147483647;
        boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0); //判断两数是否异号
        //处理特殊情况
        if(dividend == 0){
            return 0;
        } else if (dividend == -2147483648 && divisor == 1) {
            return -2147483648;
        }
        //将两数都转为负数作运算
        if (temp1 > 0) {
            temp1 *= -1;
        }
        if (temp3 > 0) {
            temp3 *= -1;
        }

        //转成负数之后，若是被除数比除数还小，说
        //明被除数的绝对值比除数要大，结果为0
        if (temp3 < temp1) {
            return 0;
        }

        while (left <= right) {
            center = left + ((right - left) >> 1);
            if (quickM(center,temp3,temp1)) {
                res = center;
                if (res == 2147483647) {
                    break;
                }
                left = center + 1;
            }else {
                right = center - 1;
            }
        }

        if (flag) {
            res *= -1;
        }

        return res;
    }

    /**
     * 快速乘算法，直接计算乘法的结果可能会溢出，所以在
     * 这里采用在快速乘中进行判断
     */
    public boolean quickM(int z,int y,int x){
        int temp = 0,add = y;

        while(z > 0){
            if ((z & 1) == 1) {
                //x,y在此题中都是转为负数来处理，所以溢出的情况就是
                // temp + add < x ，为了避免在条件判断的时候溢出
                //改写成为 temp < x - add
                if (temp < x - add) {
                    return false;
                }
                temp += add;
            }

            //此处加上限定条件 z==1 是因为要避免到最后一次处理的
            //时候add溢出而导致结果为false的情况
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

    //不做判断的正常的快速乘算法，不使用递归
    public int quickAdd(int x,int y,int z){
        int add = y,res = 0;

        while (z > 0) {
            if ((z & 1) == 1) {
                res += add;
            }

            add += add;
            z = z >> 1;
        }

        return res;
    }
}
