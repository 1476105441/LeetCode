package contest.year2022.m11.d26;

public class No2 {
    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] res = new int[m][n];
        int[] rows = new int[m],cols = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cols[j]++;
                    rows[i]++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int row = 2 * rows[i] - m;
            for (int j = 0; j < n; j++) {
                int col = 2 * cols[j] - n;
                res[i][j] = row + col;
            }
        }

        return res;
    }
}
