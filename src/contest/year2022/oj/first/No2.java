package contest.year2022.oj.first;

public class No2 {
    //使区间里1的数目发生改变
    //超时
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(),res = 1;
        int[] nums = new int[n];
        //第一个是增加
        int[][] dp1 = new int[n][2],dp2 = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        if (nums[0] == 0) {
            dp1[0][0] = 1;
            dp1[0][1] = 1;
            dp2[0][0] = 0;
            dp2[0][1] = 0;
        }else{
            dp1[0][0] = 0;
            dp1[0][1] = 0;
            dp2[0][0] = 1;
            dp2[0][1] = 1;
        }

        int max1 = 0,max2 = 0;
        max1 = dp1[0][0];
        max2 = dp2[0][0];
        //在每一个元素，你可以选择接上前面的，也可以选择重新开始
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                dp1[i][0] = 1;
                dp1[i][1] = Math.max(dp1[i-1][0],dp1[i-1][1] )+1;
                dp2[i][0] = 0;
                dp2[i][1] = Math.max(dp2[i-1][0],dp2[i-1][1])-1;
            }else{
                dp1[i][0] = 0;
                dp1[i][1] = Math.max(dp1[i-1][0],dp1[i-1][1] )-1;
                dp2[i][0] = 1;
                dp2[i][1] = Math.max(dp2[i-1][0],dp2[i-1][1])+1;
            }
            max1 = Math.max(max1,dp1[i][0]);
            max1 = Math.max(max1,dp1[i][1]);
            max2 = Math.max(max2,dp2[i][0]);
            max2 = Math.max(max2,dp2[i][1]);
        }
        res = max1 + max2 +1;
        System.out.println(res);
    }*/

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(),res = 1,c1 = 0,c2 = 0,max1 = 0,max2 = 0;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int pre = nums[0];
        if (nums[0] == 1) {
            c1++;
        }else
            c2++;
        for (int i = 1; i < n; i++) {
            if (nums[i] == pre) {
                if(nums[i] == 1)
                    c1++;
                else
                    c2++;
            }else{
                if(pre == 1){
                    max1 = Math.max(max1,c1);
                    pre = 0;
                    c1 = 0;
                    c2 = 1;
                }else{
                    max2 = Math.max(max2,c2);
                    pre = 1;
                    c2 = 0;
                    c1 = 1;
                }
            }
        }
        max1 = Math.max(max1,c1);
        max2 = Math.max(max2,c2);

        res += max1+max2;
        System.out.println(res);
    }*/

    public static void main(String[] args) {
        System.out.println("hello world");

        int[] temp = new int[50];
    }
}
