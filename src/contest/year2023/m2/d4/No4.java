package contest.year2023.m2.d4;

public class No4 {
    //失败，比赛中没有写出来
    /*public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (i > 0) {
                    dp[i][j] += dp[i-1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j-1];
                }
            }
        }
        return dp[m-2][n-1] == 0 || dp[m-1][n-2] == 0;
    }*/

    //两个回溯，1ms
    /*int[][] set;
    boolean res;
    public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        res = false;
        set = new int[m][n];
        res = !dfs1(0,0,grid);
        set[0][0] = 0;
        dfs2(0,0,grid);
        return res;
    }
    private boolean dfs1(int i,int j,int[][] grid){
        if (i >= grid.length || j >= grid[0].length) {
            return false;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }
        if (grid[i][j] == 0) {
            return false;
        }
        set[i][j] = 1;
        //往下走
        boolean flag = dfs1(i + 1, j, grid);
        if (!flag) {
            flag = dfs1(i,j+1,grid);
        }
        if (!flag) {
            set[i][j] = 0;
        }
        return flag;
    }
    private boolean dfs2(int i,int j,int[][] grid){
        if (i >= grid.length || j >= grid[0].length) {
            return false;
        }
        //走到尽头了
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }
        if (grid[i][j] == 0) {
            return false;
        }
        if (set[i][j] == 1) {
            res = true;
            return true;
        }
        set[i][j] = 1;
        //往右走
        boolean flag = dfs2(i, j + 1, grid);
        if (!flag) {
            //往下走
            flag =  dfs2(i+1,j,grid);
        }
        return flag;
    }*/

    //不需要使用额外的数组存放访问状态，0ms
    public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        boolean res = !dfs(0, 0, grid);
        grid[0][0] = 1;
        res = res || !dfs(0,0,grid);
        return res;
    }
    public boolean dfs(int i,int j,int[][] grid){
        if (i >= grid.length || j >= grid[0].length) {
            return false;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }
        if (grid[i][j] == 0) {
            return false;
        }
        grid[i][j] = 0;
        boolean b = dfs(i + 1, j, grid);
        if (!b) {
            b = dfs(i,j+1,grid);
        }
        return b;
    }
}
