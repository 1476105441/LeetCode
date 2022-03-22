package 中级算法复习.排序和搜索;

public class No6 {
    //       合并区间

    /**
     *  思想：先将数组中的每个区间按照起始区间的大小进行排序，
     *  得到一个顺序的数组，然后比较前一区间结尾元素和后一区间
     *  的开始元素，如果前者大于后者，则说明这两个区间可以合并，
     *  否则不能合并
     */

    /*public int[][] merge(int[][] intervals){
        int n = intervals.length,loc = 0;
        if (n == 1) {
            return intervals;
        }
        int[][] temp = new int[n][2];
        duiMerge(intervals);
        temp[loc] = intervals[0];

        for (int i = 1; i < n; i++) {
            //前一元素（区间）的结尾比后一元素（区间）的开始要大，两个区间需要合并
            if (temp[loc][1] >= intervals[i][0]) {
                if (intervals[i][1] > temp[loc][1]) {
                    temp[loc][1] = intervals[i][1];
                }
            }else{
                loc++;
                temp[loc] = intervals[i];
            }
        }

        int[][] res = new int[loc+1][2];
        for (int i = 0; i < loc + 1; i++) {
            res[i] = temp[i];
        }

        return res;
    }*/

    public int[][] merge(int[][] intervals){
        int n = intervals.length,loc = 0;
        if (n == 1) {
            return intervals;
        }
        duiMerge(intervals);

        for (int i = 1; i < n; i++) {
            //前一元素（区间）的结尾比后一元素（区间）的开始要大，两个区间需要合并
            if (intervals[loc][1] >= intervals[i][0]) {
                if (intervals[i][1] > intervals[loc][1]) {
                    intervals[loc][1] = intervals[i][1];
                }
            }else{
                loc++;
                intervals[loc] = intervals[i];
            }
        }

        int[][] res = new int[loc+1][2];
        for (int i = 0; i < loc + 1; i++) {
            res[i] = intervals[i];
        }

        return res;
    }

    public void shift(int[][] nums,int n,int length){
        int i = n,j = 2 * i + 1;
        int[] temp;

        while(j < length){
            if (j + 1 < length && nums[j][0] < nums[j + 1][0]) {
                j++;
            }

            if (nums[i][0] < nums[j][0]) {
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

    public void duiMerge(int[][] nums){
        int n = nums.length;
        int[] temp;

        for (int i = (n - 2) / 2; i > -1; i--) {
            shift(nums,i,n);
        }

        for (int i = n-1; i > -1; i--) {
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i);
        }
    }
}
