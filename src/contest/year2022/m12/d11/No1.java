package contest.year2022.m12.d11;

import java.util.Arrays;

public class No1 {
    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length,n = grid[0].length,res = 0;

        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
        }
        for (int i = n-1; i > -1; i--) {
            int max = 0;
            for (int j = 0; j < m; j++) {
                max = Math.max(max,grid[j][i]);
            }
            res += max;
        }

        return res;
    }
}
