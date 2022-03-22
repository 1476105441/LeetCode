package 练习;

import java.util.Scanner;

public class Coin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println("假币为第" + coin(nums,0,n-1,n) + "个硬币");
    }

    //假币问题
    public static int coin(int[] nums,int low,int high,int n){
        int num1,num2,num3,i;

        //递归出口，只有一个元素时，一定就是假币
        if (n == 1) {
            return low+1;
        } else if (n % 3 == 0) {
            num1 = n/3;
            num2 = n/3;
        }else{
            num1 = n/3 + 1;
            num2 = n/3 + 1;
        }
        num3 = n-num1-num2;

        //计算每一组的重量
        int weigh1 = 0,weigh2 = 0;
        for (i = 0; i < num1; i++) {
            weigh1 += nums[low+i];
        }
        for (i = num1;i < num1 + num2;i++) {
            weigh2 += nums[low+i];
        }

        if (weigh1 < weigh2) {
            return coin(nums,low,low + num1 - 1,num1);
        } else if (weigh1 > weigh2) {
            return coin(nums,low + num1,low + num1 + num2 - 1,num2);
        }else{
            return coin(nums,low + num1 + num2,high,num3);
        }
    }
}
