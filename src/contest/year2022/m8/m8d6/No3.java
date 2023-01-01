package contest.year2022.m8.m8d6;

import java.util.HashMap;
import java.util.Map;

public class No3 {
    /**
     *      任务调度器 II
     */

    public static long taskSchedulerII(int[] tasks, int space) {
        Map<Integer,Long> map = new HashMap<>();
        int n = tasks.length;
        long res = 0;

        for (int i = 0; i < n; i++) {
            Long type = map.get(tasks[i]);
            res++;
            if(type != null){
                long tmp = res - type;
                if (tmp <= space) {
                    res += space-tmp+1;
                }
            }
            map.put(tasks[i],res);
        }

        return res;
    }
}
