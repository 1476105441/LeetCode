package 周赛.第二周;

public class No2 {
    static int minRes = Integer.MAX_VALUE;
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int n = scanner.nextInt();

        int[][] nums = new int[n][n];
        int[] prime = new int[n-1],temp = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            temp[count++] = i;
            while (count < n) {
                for (int j = 0; j < n; j++) {
                    if (j != temp[count - 1]) {
                    }
                }
                count++;
            }
        }
    }


    //----------------------------------------------------------------------------------
    //想法：回溯解决
    //失败，超时了
    //count计数当前的点数量，temp存储点，prime存储价格
    public static void recursion(int[][] nums,int count,int[] prime,int[] temp){
        if (count == temp.length) {
            int min = Integer.MAX_VALUE,max = 0,mink = 0,maxk = 0,pri = 0;
            for (int i = 0; i < prime.length; i++) {
                if (prime[i] > max) {
                    max = prime[i];
                    maxk = i;
                }
                if (prime[i] < min) {
                    min = prime[i];
                    mink = i;
                }
                pri += prime[i];
            }

            pri = pri - prime[maxk];
            pri = pri + 2*prime[mink];
            if (pri < minRes) {
                minRes = pri;
            }

            return;
        }

        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < count; j++) {
                if (temp[j] == i) {
                    flag = false;
                    break;
                }
            }
            if (i != 0 && count != 0) {
                prime[count-1] = nums[temp[count-1]][i];
            }
            if (flag) {
                temp[count] = i;
                recursion(nums,count+1,prime,temp);
            }
        }
    }
}
