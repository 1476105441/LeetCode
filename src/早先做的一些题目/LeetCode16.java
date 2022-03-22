package 早先做的一些题目;

import java.util.Scanner;

public class LeetCode16 {

    public static void main(String[] args) {
        /*//测试快速排序能否通过
        java.util.Scanner s=new Scanner(System.in);
        int[] nums= new int[10];
        for(int i=0;i<10;i++){
            nums[i]=s.nextInt();
        }
        quickSort(nums,0,9);
        for(int i=0;i<10;i++){
            System.out.println(nums[i]);
        }*/
    }


    public int threeSumClosest(int[] nums, int target) {
        int i,j,k,sum=10000,n,temp;
        n=nums.length;
        //先进行排序
        quickSort(nums,0,n-1);
        //找和最接近的三个数
        for(i=0;i<n-2;i++){
            //避免重复
            if(i!=0&&nums[i]==nums[i-1])
                continue;
            for(j=i+1;j<n-1;j++){
                if(j!=i+1&&nums[j]==nums[j-1]) {
                    continue;
                }
                for(k=j+1;k<n;k++){
                    if(k!=j+1&&nums[k]==nums[k-1])
                        continue;
                    //temp的绝对值越小，就越接近target
                    temp=nums[i]+nums[j]+nums[k]-target;
                    if(temp<0)
                        temp*=-1;
                    if(temp<sum)
                        sum=temp;
                    //如果三数和已经大于target，直接退出循环即可
                    if(nums[i]+nums[j]+nums[k]-target>0)
                        break;
                }
            }
        }
        return sum;
    }


    public static void quickSort(int[] nums,int low, int high){
        int i,j,temp;
        temp=nums[low];
        i=low;
        j=high;
        while(i<j){
            while(i<j&&nums[j]>temp)
                j--;
            if(i<j){
                nums[i]=nums[j];
                i++;
            }
            while(i<j&&nums[i]<temp)
                i++;
            if(i<j){
                nums[j]=nums[i];
                j--;
            }
        }
        nums[i]=temp;
        //对左端进行扫描
        if(i>low)
            quickSort(nums,low,i-1);
        //对右端进行扫描
        if(j<high)
            quickSort(nums,j+1,high);
    }
}
