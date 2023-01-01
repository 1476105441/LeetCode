package contest.year2022.m9.m9d11;

public class No3 {
    /**
     *      将区间分为最少组数
     */

    //超时了
    /*public int minGroups(int[][] intervals) {
        int n = intervals.length,l = 0;
        int[] group = new int[100000];
        Arrays.sort(intervals,(x,y)->{
            if(x[0] == y[0])
                return x[1] - y[1];
            return x[0] - y[0];
        });

        group[l++] = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int j;
            for (j = 0; j < l; j++) {
                if (intervals[i][0] > group[j]) {
                    group[j] = intervals[i][1];
                    break;
                }
            }
            if (j == l) {
                group[l++] = intervals[i][1];
            }
        }

        return l;
    }*/

    //想一下怎么优化，暴力寻找，太慢了，800-900ms
    /*public int minGroups(int[][] intervals) {
        int n = intervals.length,l = 0,min;
        int[] group = new int[100000];
        Arrays.sort(intervals,(x,y)->{
            if(x[0] == y[0])
                return x[1] - y[1];
            return x[0] - y[0];
        });

        group[l++] = intervals[0][1];
        min = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int j;
            if (min >= intervals[i][0]) {
                group[l++] = intervals[i][1];
                min = Math.min(intervals[i][1],min);
                continue;
            }
            int temp = Integer.MAX_VALUE;
            for (j = 0; j < l; j++) {
                if (intervals[i][0] > group[j]) {
                    if (group[j] == min) {
                        temp = Math.min(temp,intervals[i][1]);
                        for (int k = j+1; k < l; k++) {
                            temp = Math.min(temp,group[k]);
                        }
                        min = temp;
                    }
                    group[j] = intervals[i][1];
                    break;
                }
                temp = Math.min(temp,group[j]);
            }
        }

        return l;
    }*/


    //使用堆，72ms
    /*public int minGroups(int[][] intervals) {
        int n = intervals.length,res = 1,l = 1;
        int[] heap = new int[n];
        Arrays.sort(intervals,(x,y)->{return x[0] - y[0];});
        heap[0] = intervals[0][1];
        for(int i = 1;i < n;i++){
            if(heap[0] < intervals[i][0]){
                heap[0] = intervals[i][1];
                shift(heap,0,l);
            }else{
                heap[l] = intervals[i][1];
                update(heap,l);
                l++;
            }
        }
        return l;
    }
    public void shift(int[] nums,int start,int n){
        int i = start,j = (i << 1)+1;
        while(j < n){
            if(j < n-1 && nums[j] > nums[j+1]){
                j++;
            }
            if(nums[i] > nums[j]){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = (i << 1) + 1;
            }else
                break;
        }
    }
    public void update(int[] nums,int start){
        int i = start,p = (i-1) >> 1;
        while(i > 0){
            if(nums[i] < nums[p]){
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }*/

    //差分数组解法，40ms
    public int minGroups(int[][] intervals) {
        int n = intervals.length,res=0;
        int[] diff = new int[1000002];
        for(int i = 0;i < n;i++){
            diff[intervals[i][0]]++;
            diff[intervals[i][1]+1]--;
        }
        int temp = 0;
        for(int i = 0;i < 1000001;i++){
            temp += diff[i];
            res = Math.max(temp,res);
        }
        return res;
    }
}
