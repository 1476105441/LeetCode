package contest.year2023.m4.d15;

/**
 * 2639. 查询网格图中每一列的宽度
 *
 * @author wjs 2023/5/18
 */
public class No1 {
    //暴力解法
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[] res = new int[n];
        for(int i=0; i < m; i++) {
            for(int j=0; j < n; j++) {
                res[j] = Math.max(res[j],countWidth(grid[i][j]));
            }
        }
        return res;
    }
    private int countWidth(int val){
        int width = val <= 0 ? 1 : 0;
        while(val != 0) {
            width++;
            val /= 10;
        }
        return width;
    }
}
