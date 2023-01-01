package contest.year2022.m10.m10d2;

public class No2 {
    /**
     *      沙漏的最大总和
     */

    public int maxSum(int[][] grid) {
        int m = grid.length,n = grid[0].length,res = 0;

        for (int i = 0; i < m-2; i++) {
            for (int j = 0; j < n-2; j++) {
                int temp = grid[i][j];
                temp += grid[i][j+1];
                temp += grid[i][j+2];
                temp += grid[i+1][j+1];
                temp += grid[i+2][j];
                temp += grid[i+2][j+1];
                temp += grid[i+2][j+2];
                res = Math.max(temp,res);
            }
        }

        return res;
    }
}
