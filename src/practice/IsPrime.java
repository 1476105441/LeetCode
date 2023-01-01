package practice;

import java.util.Scanner;

public class IsPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        filter1(n);
    }

    //埃氏筛
    //从2开始判断当前数是否为质数，若是质数，则将其倍数标记为合数
    //标记倍数时注意：要从当前的值开始，避免重复，因为当前值i之前
    //的质数k已经标记过i * k了
    public static void filter1(int n){
        if (n < 2) {
            System.out.println("输入的数中不包含质数");
        }

        int count = 0;
        boolean[] isC = new boolean[n+1];

        for (int i = 2; i*i <= n; i++) {
            if (!isC[i]) {
                int k = i;
                while (i * k <= n) {
                    isC[i*k] = true;
                    k++;
                }
            }
        }

        System.out.println("小于等于" + n + "的质数有：");
        for (int i = 2; i <= n; i++) {
            if (!isC[i]) {
                System.out.print(i + " ");
                count++;
            }
        }

        System.out.println("\n总数： " + count);
    }

    //线性筛

}
