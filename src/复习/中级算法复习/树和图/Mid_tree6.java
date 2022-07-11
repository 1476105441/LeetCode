package 复习.中级算法复习.树和图;

public class Mid_tree6 {
    //              岛屿数量

    //  想法：遍历二维数组，以每个元素为中心向四周扩散，相邻且是陆地的标记为
    //岛屿，采用剪枝策略减少重复的执行
    //      成功，2ms，效率还行
    /*public int numIslands(char[][] grid){
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '*';
                    recursion(grid,i,j);
                }
            }
        }

        return res;
    }

    //以当前元素为中心，向四周扩散
    public void recursion(char[][] grid,int i,int j){
        if (i > 0 && grid[i-1][j] == '1') {
            grid[i-1][j] = '*';
            recursion(grid,i-1,j);
        }
        if (j > 0 && grid[i][j-1] == '1') {
            grid[i][j-1] = '*';
            recursion(grid,i,j-1);
        }
        if (i < grid.length-1 && grid[i+1][j] == '1') {
            grid[i+1][j] = '*';
            recursion(grid,i+1,j);
        }
        if (j < grid[0].length-1 && grid[i][j+1] == '1') {
            grid[i][j+1] = '*';
            recursion(grid,i,j+1);
        }
    }*/

    //采用并查集解决此问题
}
