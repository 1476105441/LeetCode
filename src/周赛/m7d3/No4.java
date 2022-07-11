package 周赛.m7d3;

public class No4 {
    /**
     *      网格图中递增路径的数目
     */

    //记忆化搜索，只有都失败了才返回
    //很暴力，但超时
    /*int res;
    boolean[][] flags;
    public int countPaths(int[][] grid){
        res = 0;
        flags = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                find(grid,i,j,-1);
            }
        }

        return res;
    }
    public void find(int[][] grid,int row,int col,int pre){
        //越界了就返回
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1) {
            return;
        }
        //当前元素的值小于前一元素也返回
        if (flags[row][col] || grid[row][col] <= pre) {
            return;
        }

        //将当前元素标记为已搜索过
        flags[row][col] = true;
        find(grid,row-1,col,grid[row][col]);
        find(grid,row,col-1,grid[row][col]);
        find(grid,row+1,col,grid[row][col]);
        find(grid,row,col+1,grid[row][col]);
        flags[row][col] = false;
        res %= 1000000007;
        res++;
    }*/


    int res;
    long[][] flags;
    public int countPaths(int[][] grid){
        res = 0;
        flags = new long[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                find(grid,i,j,-1);
            }
        }

        return res;
    }
    public long find(int[][] grid,int row,int col,int pre){
        //越界了就返回
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1) {
            return 0;
        }
        //当前元素的值小于前一元素也返回
        if (grid[row][col] <= pre) {
            return 0;
        }

        if (flags[row][col] > 0) {
            return flags[row][col];
        }
        long c = 1;

        c = (c+find(grid,row-1,col,grid[row][col]))%1000000007;
        c = (c+find(grid,row,col-1,grid[row][col]))%1000000007;
        c = (c+find(grid,row+1,col,grid[row][col]))%1000000007;
        c = (c+find(grid,row,col+1,grid[row][col]))%1000000007;

        flags[row][col] = c;
        res += c;
        res %= 1000000007;

        return flags[row][col];
    }
}
