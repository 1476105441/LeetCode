package 复习.数学;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    /**
     *      计数质数
     */

    //埃式筛
    /*public int countPrimes(int n) {
        boolean[] flag = new boolean[n];
        int res = 0;

        for (int i = 2; i < n; i++) {
            if (!flag[i]) {
                res++;
                //为了防止数值溢出，要转换成long再做判断
                if ((long)i * i < n) {
                    for (int j = i*i; j < n; j+=i) {
                        flag[j] = true;
                    }
                }
            }
        }

        return res;
    }*/

    //小改版本
    /*public int countPrimes(int n) {
        boolean[] flag = new boolean[n];
        int res = 0;

        for (int i = 2; i < n; i++) {
            if (!flag[i]) {
                res++;
                //为了防止数值溢出，要转换成long再做判断
                for (long j = (long)i*i; j < n; j+=i) {
                    flag[(int)j] = true;
                }
            }
        }

        return res;
    }*/

    //线性筛
    public int countPrimes(int n){
        int[] list = new int[n+1];
        int res = 0;
        boolean[] flag = new boolean[n];

        for (int i = 2; i < n; i++) {
            if(!flag[i])
                list[res++] = i;
            for (int j = 0; j < res; j++) {
                if((long)list[j]*i < n)
                    flag[list[j]*i] = true;
                else
                    break;
                if (i % list[j] == 0)
                    break;
            }
        }

        return res;
    }

    //扩展，判断一个数是否是质数，质数的
    //分布有一定的规律：质数一定分布在6
    //的倍数的两侧
    public boolean isPrime(int num){
        if(num == 2 || num == 3)
            return true;
        if(num < 6)
            return false;

        int tmp = (int) Math.sqrt(num);
        for (int i = 5; i <= tmp; i+=6) {
            if(num % i == 0 || num % (i+2) == 0)
                return false;
        }

        return true;
    }
}
