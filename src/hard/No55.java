package hard;

public class No55 {
    //                   剑指 Offer 51. 数组中的逆序对
    //------------------------------------------------------------------------------
    //用归并排序解决
    int[] newNums;
    int num = 0;

    public void recursion(int low,  int high, int[] nums) {
        if (low == high) {
            return;
        }
        int center = (low + high) / 2;
        //左半部分
        recursion(low, center, nums);
        //右半部分
        recursion(center + 1,  high, nums);
        //归并
        merge(nums,newNums,low,center,high);
        //将临时数组中的排好序的元素放回原数组中
        for (int i = low; i <= high; i++) {
            nums[i] = newNums[i];
        }
    }

    public void merge(int[] nums,int[] newNums,int left,int center,int right){
        int i = left,k = left,j = center + 1;
        //左边的数组到center为止，而右边的数组从center+1到right为止
        while (i <= center && j <= right) {
            if (nums[i] <= nums[j]) {
                //前面数组的元素比后面数组前面部分的大，构成逆序
                num = num + j - center - 1;
                newNums[k++] = nums[i++];
            }else{
                newNums[k++] = nums[j++];
            }
        }
        //处理剩下的元素
        while (i <= center) {
            num = num + right - center;
            newNums[k++] = nums[i++];
        }
        while (j <= right) {
            newNums[k++] = nums[j++];
        }
    }
    public int reversePairs(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        newNums = new int[nums.length];
        recursion(0,nums.length-1,nums);
        return num;
    }
    //------------------------------------------------------------------------------
}
