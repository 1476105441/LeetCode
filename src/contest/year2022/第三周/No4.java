package contest.year2022.第三周;

import java.math.BigInteger;
import java.util.Scanner;

public class No4 {
    //              判断质数
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        long[] nums = new long[t];

        for (int i = 0; i < t; i++) {
            nums[i] = scanner.nextLong();
        }

        boolean flag;
        for (int i = 0; i < t; i++) {
            flag = false;
            if (nums[i] == 2) {
                System.out.println("Yes");
                continue;
            }
            if (nums[i] % 2 == 0 || nums[i] == 1) {
                System.out.println("No");
                continue;
            }
            for (double j = 3; j * j <= nums[i]; j += 2) {
                if (nums[i] % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }*/


    //超时
    /*public static void main(String[] args) {
        int n;
        long x;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            x = scanner.nextLong();
            boolean flag = true;
            for (long j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }*/

    public static void main(String[] args) {
        int n;
        BigInteger x;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            x = scanner.nextBigInteger();
            if(x.isProbablePrime(1))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
