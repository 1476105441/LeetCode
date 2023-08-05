package contest.year2023.m5.d7;

/**
 * 2673.使二叉树所有路径值相等
 *
 * @author wjs 2023/8/5
 */
public class No4 {
    int[] cost;
    int n,res;
    public int minIncrements(int n, int[] cost) {
        res = 0;
        this.n = n;
        this.cost = cost;
        dfs(1);
        return res;
    }

    private int dfs(int loc) {
        if(loc > n) {
            return 0;
        }
        int left = 2*loc, right = 2*loc + 1;
        int lv = dfs(left);
        int rv = dfs(right);
        if(lv != rv) {
            res += Math.abs(lv-rv);
        }
        return cost[loc-1] + Math.max(lv,rv);
    }
}
