package 中级算法.排序和搜索;

public class No5 {
    //                  合并区间
    //以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
    // 并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

    //按照第一个元素来排序，再合并
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        quickSort(intervals,0,n-1);

        int i = 0,j = 1,length = n;

        boolean[] isOk = new boolean[n];

        while (j <= n-1) {

            if (intervals[i][1] >= intervals[j][0]) {
                if (intervals[j][0] > intervals[i][0]) {
                    intervals[j][0] = intervals[i][0];
                }
                if (intervals[j][1] < intervals[i][1]) {
                    intervals[j][1] = intervals[i][1];
                }
                isOk[i] = true;
                length--;
            }

            j++;
            i++;
        }

        int[][] res = new int[length][2];

        i = 0;
        for (int k = 0; k < n && i < length; k++) {
            if (!isOk[k]) {
                res[i++] = intervals[k];
            }
        }

        return res;
    }

    public void quickSort(int[][] nums,int low,int high){
        if (low >= high) {
            return;
        }

        int i = low,j = high;
        int[] temp;

        while (i < j) {
            while (i < j && nums[i][0] < nums[j][0]) {
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i][0] < nums[j][0]) {
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
    }
}
