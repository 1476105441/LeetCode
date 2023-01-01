package review.搜索和排序;

import java.util.ArrayList;
import java.util.List;

public class No8 {
    /**
     *      合并区间
     */

    //先排序，然后再判断是否合并
    public int[][] merge(int[][] intervals) {
        quicksort(intervals,0,intervals.length-1);
        List<int[]> list = new ArrayList<>();

        int pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            //包含的情况，也要合并
            if (intervals[i][1] <= intervals[pre][1]) {
                intervals[i][0] = intervals[pre][0];
                intervals[i][1] = intervals[pre][1];
            } else if (intervals[i][0] <= intervals[pre][1]) {
                intervals[i][0] = intervals[pre][0];
            }else{
                list.add(intervals[pre]);
            }
            pre++;
        }
        list.add(intervals[intervals.length-1]);

        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    public void quicksort(int[][] nums,int l,int r){
        if (l >= r) {
            return;
        }
        int i = l,j = r;
        while (i < j) {
            while (i < j && nums[i][0] < nums[j][0]) {
                j--;
            }
            if (i < j) {
                int[] tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
            }
            while (i < j && nums[i][0] < nums[j][0]) {
                i++;
            }
            if (i < j) {
                int[] tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j--;
            }
        }

        quicksort(nums,l,i-1);
        quicksort(nums,i+1,r);
    }
}
