package 练习;

import java.util.Scanner;

public class DuiSort {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        //首先初始化最大堆
        for (int i = (n-2)/2; i >= 0; i--) {
            shift(nums,i,n);
        }

        int temp;
        for (int i = n-1; i > 0; i--) {
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(nums[i]);
        }
    }

    //堆排序的调整函数
    public static void shift(int[] nums,int root,int n){
        int i = root,j = 2*i+1,temp;
        while (j < n) {
            temp = nums[i];
            //选择左右子树中较大的
            if (j + 1 < n && nums[j+1] > nums[j]) {
                j++;
            }

            //比较根节点和左右子树中大的
            if (temp > nums[j]) {
                break;
            }else{
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }
        }
    }
}
