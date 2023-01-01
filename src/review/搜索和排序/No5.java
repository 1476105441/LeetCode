package review.搜索和排序;

public class No5 {
    /**
     *      数组中的第K个最大元素
     */

    //桶排序，不行，有负数的存在
    /*public int findKthLargest(int[] nums, int k) {
        int[] bucket = new int[10001];

        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        int count = 0,res = 0;
        for (int i = 10000; i > -1; i--) {
            count += bucket[i];
            if (count >= k) {
                res = i;
                break;
            }
        }

        return res;
    }*/

    //快速搜索（快速排序的特殊形式）
    //11ms
    /*int k;
    public int findKthLargest(int[] nums, int k){
        this.k = k;

        return nums[search(nums,0,nums.length-1)];
    }
    public int search(int[] nums,int start,int end){
        int i = start,j = end;
        while (i < j) {
            while (i < j && nums[i] < nums[j]) {
                j--;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] < nums[j]) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        if (nums.length - i == k) {
            return i;
        } else if (nums.length - i < k) {
            return search(nums,start,i-1);
        }else
            return search(nums,i+1,end);
    }*/

    //实际上并不需要完全排好序，只需要排到k个就可以了
    public int findKthLargest(int[] nums,int k){
        int n = nums.length,res = 0;

        for (int i = (n-2)/2; i >= 0; i--) {
            shift(nums,i,n);
        }

        for (int i = n-1; i >= 0; i--) {
            k--;
            if (k == 0) {
                res = nums[0];
                break;
            }else{
                int tmp = nums[0];
                nums[0] = nums[i];
                nums[i] = tmp;
                shift(nums,0,i);
            }
        }

        return res;
    }

    public void shift(int[] nums,int start,int end){
        int i = start,j = 2*i+1;
        while (j < end) {
            if (j < end - 1 && nums[j] < nums[j + 1]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }
}
