package contest.year2023.m4.d30;

/**
 * 2660. 保龄球游戏的获胜者
 *
 * @author wjs 2023/7/7
 */
public class No1 {
    public int isWinner(int[] player1, int[] player2) {
        int n = player1.length;
        int v1 = 0, v2 = 0;
        int[] pre1 = new int[2], pre2 = new int[2];
        for(int i=0;i < n;i++) {
            v1 += check(pre1) ? 2*player1[i] : player1[i];
            v2 += check(pre2) ? 2*player2[i] : player2[i];
            pre1[0] = pre1[1];
            pre1[1] = player1[i];
            pre2[0] = pre2[1];
            pre2[1] = player2[i];
        }
        int res = 0;
        if(v1 > v2) {
            res = 1;
        } else if(v1 < v2) {
            res = 2;
        }
        return res;
    }
    private boolean check(int[] nums) {
        return nums[0] == 10 || nums[1] == 10;
    }
}
