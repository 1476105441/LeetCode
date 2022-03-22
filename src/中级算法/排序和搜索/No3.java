package 中级算法.排序和搜索;

public class No3 {
    //                  数组中的第K个最大元素
    //给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    //请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

    //----------------------------------------------------------------------
    //                      堆排序：4ms（偏慢）
    //此题就是考察排序？
    //使用堆排序试试
    /*public int findKthLargest(int[] nums, int k) {
        int n = nums.length,temp;
        //最开始将数组调整为最大堆
        for (int i = (n - 2) / 2; i > -1; i--) {
            shift(nums,i,n);
        }

        //将堆顶与当前最后一个元素调换
        for (int i = n-1; i > 0; i--) {
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i);
        }

        return nums[n-k];
    }

    //堆排序的调整函数
    public void shift(int[] nums,int loc,int n){
        int i = loc,j = 2 * i + 1,temp;

        while (j < n) {
            if (j + 1 < n && nums[j + 1] > nums[j]) {
                j++;
            }

            if (nums[j] > nums[i]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
    }*/
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    //                      快速排序：48ms（离谱）
    //可能是出现了最坏情况
    //快速排序试试呢
    /*public int findKthLargest(int[] nums, int k){
        quickSort(nums,0,nums.length-1);

        return nums[nums.length-k];
    }

    public void quickSort(int[] nums,int low,int high){
        if (low >= high) {
            return;
        }

        int i = low,j = high,temp;

        while (i < j) {
            while (i < j && nums[i] < nums[j]) {
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] < nums[j]) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        quickSort(nums,low,i-1);
        quickSort(nums,i+1,high);
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //思考一下：怎样优化堆排序？
    //没必要将所有的元素都排序完，每次构造成最大堆时，堆顶就是当前的最大元素
    //只需要调换k-1次，此时的最大堆堆顶就是要寻找的第k大的元素
    public int findKthLargest(int[] nums, int k) {
        //为避免数组中只有一个元素时返回值错误的情况，将返回结果res置为第一个元素的值
        int n = nums.length,temp,res = 0;
        //最开始将数组调整为最大堆
        for (int i = (n - 2) / 2; i > -1; i--) {
            shift(nums,i,n);
        }

        //将堆顶与当前最后一个元素调换
        for (int i = n-1; i >= 0; i--) {
            if (k <= 1) {
                res = nums[0];
                break;
            }
            k--;
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i);
        }

        return res;
    }

    //堆排序的调整函数
    public void shift(int[] nums,int loc,int n){
        int i = loc,j = 2 * i + 1,temp;

        while (j < n) {
            if (j + 1 < n && nums[j + 1] > nums[j]) {
                j++;
            }

            if (nums[j] > nums[i]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
    }
}
