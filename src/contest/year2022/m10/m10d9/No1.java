package contest.year2022.m10.m10d9;

public class No1 {
    /**
     *  处理用时最长的那个任务的员工
     */

    public int hardestWorker(int n, int[][] logs) {
        int count = 0,max = Integer.MIN_VALUE,res = 0;
        for (int i = 0; i < logs.length; i++) {
            int temp = logs[i][1] - count;
            if (temp > max ) {
                max = temp;
                res = logs[i][0];
            } else if (temp == max) {
                res = Math.min(res,logs[i][0]);
            }
            count = logs[i][1];
        }
        return res;
    }
}
