package contest.year2023.m5.d14;

/**
 * No2682.找出转圈游戏输家
 *
 * @author wjs 2023/8/7
 */
public class No1 {
    //模拟解法，1ms
    public int[] circularGameLosers(int n, int k) {
        int[] res = null;
        boolean[] set = new boolean[n+1];
        int loc = 0, cnt = 1;
        for(;;) {
            if(set[loc]) {
                break;
            }
            set[loc] = true;
            loc = (loc + cnt * k) % n;
            cnt++;
        }
        res = new int[n-cnt+1];
        int j = 0;
        for(int i=0;i < n;i++) {
            if(!set[i]) {
                res[j] = i+1;
                j++;
            }
        }
        return res;
    }
}
