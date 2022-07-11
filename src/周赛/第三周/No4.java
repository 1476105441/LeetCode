package 周赛.第三周;

import java.util.Scanner;

public class No4 {
    //              判断质数
    public static void main(String[] args) {
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
    }
}
