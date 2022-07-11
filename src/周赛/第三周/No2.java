package 周赛.第三周;

import java.util.Scanner;

public class No2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //直接采用相乘的方法会导致数据溢出而无法判断，为什么不使用取对数的方法？
        long l = scanner.nextLong(),r = scanner.nextLong(),k = scanner.nextLong(),temp;

        long lim = 0,count = (long)((Math.log(r)/Math.log(2))/(Math.log(k)/Math.log(2)));

        temp = 1;
        if (1 < l && k > r) {
            System.out.println(-1);
            return;
        }
        while (lim <= count) {
            lim++;
            if (temp < l) {
                temp = temp * k;
                continue;
            }
            System.out.print(temp + " ");

            temp = temp * k;
        }
    }
}
