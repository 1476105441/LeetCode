package practice;

import java.util.Scanner;

public class RotateArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        reverse(nums,0,n-1,n);

        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    //反转数组
    //注意一个点：当你要改变一个值的时候，先想想会不会对后续的运行出现影响
    public static void reverse(int[] nums,int low,int high,int n){
        if (low == high) {
            return;
        }

        int center;
        center = low + (high - low)/2;

        reverse(nums,center+1,high,high - center);
        //当前区间长度为奇数时，中间的元素不需要调换
        if (n % 2 != 0) {
            reverse(nums,low,center-1,center-low);
        } else{
            reverse(nums,low,center,center - low + 1);
        }

        int i = low, j = center + 1,temp;

        while (i <= center && j <= high) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j++;
        }
    }
}
