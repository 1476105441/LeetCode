package contest.year2022.m8.m8d21;

import java.util.Arrays;

public class No4 {
    /**
     *      找出数组的第 K 大和
     */

    //最大堆
    /*public long kSum(int[] nums, int k) {
        long sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                sum += nums[i];
            }else{
                nums[i] = -nums[i];
            }
        }

        quickSort(nums,0,n-1);
        long[][] heap = new long[2000][2];
        int s = 0;
        heap[s++] = new long[]{sum,0};
        while (--k > 0) {
            long[] t = heap[0];
            int i = (int) t[1];
            heap[0] = heap[s-1];
            s--;
            shift(heap,0,s);
            if (i < n) {
                heap[s] = new long[]{t[0]-nums[i],i+1};
                update(heap,s);
                s++;
                if (i > 0) {
                    heap[s] = new long[]{t[0]-nums[i]+nums[i-1],i+1};
                    update(heap,s);
                    s++;
                }
            }
        }

        return heap[0][0];
    }

    public void shift(long[][] nums,int start,int end){
        int i = start,j = (i << 1) + 1;
        while (j < end) {
            if(j < end-1 && nums[j][0] < nums[j+1][0])
                j++;

            if (nums[i][0] < nums[j][0]) {
                long[] temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = (i << 1) + 1;
            }else
                break;
        }
    }

    public void update(long[][] nums, int start) {
        int i = start,p = (i-1)>>1;
        while (i > 0) {
            if (nums[i][0] > nums[p][0]) {
                long[] temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }

    //快速排序
    public static void quickSort(int[] nums,int l,int r){
        if(l >= r)
            return;
        int i = l,j = r;
        while (i < j) {
            while(i < j && nums[i] < nums[j])
                j--;
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
                j--;
            }
        }
        quickSort(nums,l,i-1);
        quickSort(nums,i+1,r);
    }*/

    //二分查找
    //其实也就是用二分查找到第k-1小的元素和，然后减去
    //因为每减去一个元素和就是一个新的数组和，不减去任
    //何一个时是最大值，减去第k-1小的元素和就是题目要
    //求的结果
    int[] nums;
    int cnt,k;
    long limit;
    public void find(long s,int i){
        if(i == nums.length || s + nums[i] > limit || cnt >= k)
            return;
        cnt++;
        find(s+nums[i],i+1);
        find(s,i+1);
    }
    public long kSum(int[] nums, int k){
        this.nums = nums;
        this.k = k;
        long sum = 0,max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                sum += nums[i];
            }else{
                nums[i] = -nums[i];
            }
            max += nums[i];
        }
        Arrays.sort(nums);

        long l = 0,r = max;
        while (l < r) {
            cnt = 0;
            limit = l + ((r-l) >> 1);
            find(0,0);

            //这样写是不正确的，考虑当r和l只相
            //差1，而l是满足条件的值时，走下面
            //的语句，r和l将永远相差1
            /*if (cnt > k - 1) {
                r = limit-1;
            }else
                l = limit;*/

            //正确写法
            if (cnt >= k - 1) {
                r = limit;
            }else{
                l = limit+1;
            }
        }

        return sum - l;
    }
}
