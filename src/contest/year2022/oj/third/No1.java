package contest.year2022.oj.third;

import java.util.Scanner;

public class No1 {
    //其实就是个找规律的题目，一时间没有想到，就一直卡住了
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
        }
        //判断当前区间中有多少个数对
        for (int i = 0; i < n; i++) {
            int l = nums[i][0],r = nums[i][1];
            int dif = r-l+1,res = 0;
            if ((l & 1) == 1) {
                res = res ^ l;
                dif--;
            }
            if ((r & 1) == 0) {
                res = res ^ r;
                dif--;
            }
            if(((dif/2) & 1) == 1)
                res = res ^ 1;

            System.out.println(res);
        }
    }
}
