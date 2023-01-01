package practice;

import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] nums = new int[n];

        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        mergerSort(nums,temp,0,n-1);

        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }
    //归并排序需要什么参数？开始和截止，排序的数组和暂存的数组
    public static void mergerSort(int[] nums,int[] temp,int low,int high){
        if (low == high) {
            return;
        }

        int center = low + (high - low)/2;
        mergerSort(nums,temp,low,center);
        mergerSort(nums,temp,center+1,high);

        //对左右半边的数组（已经有序）进行合并
        int i = low,j = center + 1,k = low;

        while (i <= center && j <= high) {
            if (nums[i] > nums[j]) {
                temp[k++] = nums[j++];
            }else{
                temp[k++] = nums[i++];
            }
        }

        while (i <= center) {
            temp[k++] = nums[i++];
        }
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        //将临时数组中的元素放回原数组中
        for (int l = low; l <= high; l++) {
            nums[l] = temp[l];
        }
    }
}
