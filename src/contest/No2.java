package contest;

public class No2 {
    //思考一个问题：一共有几种错误的情况？
    //1、缺失天数
    //2、天数重复（需要计算到底重复了几天）
    //3、最终的天数没有达到n天
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);

        int n, m;
        n = s.nextInt();
        m = s.nextInt();

        int[][] dp = new int[m][2];
        int temp = 0, day = 1, count = 1,maxDay = 0;

        int flag = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = s.nextInt();
            }
        }

        sort(dp, 0, m - 1);

        for (int i = 0; i < m; i++) {
            //flag == 1 说明已经有重复天数出现了，计算到底重复了多少天
            if (flag == 1) {
                if ( dp[i][0] <= day) {
                    count++;
                    continue;
                }else{
                    break;
                }
            }

            if (dp[i][0] <= temp) {
                //这里是出现重复天数的时候
                count++;
                flag = 1;
                day = dp[i][0];
            } else if (dp[i][0] > temp + 1) {
                //这里是缺失天数的时候
                flag = 1;
                day = temp + 1;
                count = 0;
                break;
            }

            if (dp[i][1] > maxDay) {
                maxDay = dp[i][1];
            }
            temp = dp[i][1];
        }

        //重复天数的情况
        if (flag == 1) {
            System.out.println(day + " " + count);
            return;
        }

        //最大天数小于n
        if (maxDay != n) {
            System.out.println(maxDay + 1 + " " + 0);
            return;
        }

        System.out.println("YES");
    }

    //将二维数组进行排序
    public static void sort(int[][] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int i = low, j = high, temp1, temp2;

        while (i < j) {
            while (i < j && nums[i][0] < nums[j][0]) {
                j--;
            }
            if (i < j) {
                temp1 = nums[i][0];
                temp2 = nums[i][1];
                nums[i][0] = nums[j][0];
                nums[i][1] = nums[j][1];
                nums[j][0] = temp1;
                nums[j][1] = temp2;
                i++;
            }
            while (i < j && nums[j][0] > nums[i][0]) {
                i++;
            }
            if (i < j) {
                temp1 = nums[i][0];
                temp2 = nums[i][1];
                nums[i][0] = nums[j][0];
                nums[i][1] = nums[j][1];
                nums[j][0] = temp1;
                nums[j][1] = temp2;
                j--;
            }
        }

        sort(nums, low, i - 1);
        sort(nums, i + 1, high);
    }
}
