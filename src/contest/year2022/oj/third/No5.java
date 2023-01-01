package contest.year2022.oj.third;

import java.util.Scanner;

public class No5 {
    static int[] dp;
    static{
        dp = new int[3001];
        dp[0] = 1;
        //大概理解了这个意思，画图出来就能清晰很多
        for (int i = 1; i < 3001; i++) {
            for (int j = i; j < 3001; j++) {
                dp[j] = (dp[j] + dp[j-i]) % 1000000007;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(dp[n]);
    }
}
