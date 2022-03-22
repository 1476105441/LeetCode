package 中级算法复习.排序和搜索;

public class No3 {
    //          寻找数组中第k个最大元素

    public int findKthLargest(int[] nums, int k){
        int[] res = new int[k];

        for (int i = 0; i < nums.length; i++) {
            if (i <= k - 1) {
                res[i] = nums[i];
                if (i == k - 1) {
                    merge(res);
                }
            }else{
                if (nums[i] > res[0]) {
                    res[0] = nums[i];
                    shift(res,0,k);
                }
            }
        }

        return res[0];
    }

    public void shift(int[] nums,int n,int length){
        int i = n,j = 2 * i + 1,temp;

        while(j < length){
            if (j + 1 < length && nums[j] > nums[j+1]) {
                j++;
            }

            if (nums[i] > nums[j]) {
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

    public void merge(int[] nums){
        int n = nums.length;

        for (int i = (n-2)/2; i > -1; i--) {
            shift(nums,i,n);
        }
    }
}
