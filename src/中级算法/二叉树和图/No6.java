package 中级算法.二叉树和图;

import java.util.LinkedList;
import java.util.Queue;

public class No6 {
    //                  岛屿问题
    //给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    //岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    //此外，你可以假设该网格的四条边均被水包围。

    //-------------------------------------------------------------------------------
    //                  失败
    //想法：暴力解法，判断当前元素的左边和上面是否有陆地，都没有，则岛屿数量加一
    /*public int numIslands(char[][] grid) {
        int res = 0;

        int i ,j;
        for (i = 0;i < grid.length;i++){
            for (j = 0;j < grid[i].length;j++){
                if (grid[i][j] == '1') {
                    if((i == 0 || grid[i-1][j] == '0') && (j == 0 || grid[i][j - 1] == '0')){
                        res++;
                    }
                }
            }
        }
        return res;
    }*/
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    //                      深度优先遍历法
    /*public int numIslands(char[][] grid){
        int res = 0;

        int i,j,l,k;

        for(i = 0;i < grid.length;i++){
            for(j = 0;j < grid[i].length;j++){
                //当前元素未被标记且等于1时
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid,i,j);
                }
            }
        }

        return res;
    }

    //深度优先遍历图
    public void dfs(char[][] grid,int row,int col){
        grid[row][col] = '0';

        //上面的元素
        if (row != 0 && grid[row - 1][col] == '1') {
            dfs(grid,row - 1,col);
        }

        //左边的元素
        if (col != 0 && grid[row][col-1] == '1') {
            dfs(grid,row,col - 1);
        }

        //下面的元素
        if (row != grid.length - 1 && grid[row + 1][col] == '1') {
           dfs(grid,row + 1,col);
        }

        //右边的元素
        if (col != grid[row].length - 1 && grid[row][col + 1] == '1') {
            dfs(grid,row,col + 1);
        }
    }*/
    //------------------------------------------------------------------------------

    //------------------------------------------------------------------------------
    //                      广度优先遍历算法
    /*public int numIslands(char[][] grid){
        int res = 0;

        Queue<element> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '0';
                    queue.add(new element(i,j));
                    bfs(grid,queue);
                }
            }
        }

        return res;
    }

    //使用队列
    public void bfs(char[][] grid,Queue<element> queue){

        while (!queue.isEmpty()) {
            element e = queue.poll();
            int row = e.row;
            int col = e.col;

            //上面的元素
            if (row != 0 && grid[row - 1][col] == '1') {
                grid[row - 1][col] = '0';
                queue.add(new element(row-1,col));
            }

            //左边的元素
            if (col != 0 && grid[row][col-1] == '1') {
                grid[row][col-1] = '0';
                queue.add(new element(row,col-1));
            }

            //下面的元素
            if (row != grid.length - 1 && grid[row + 1][col] == '1') {
                grid[row+1][col] = '0';
                queue.add(new element(row+1,col));
            }

            //右边的元素
            if (col != grid[row].length - 1 && grid[row][col + 1] == '1') {
                grid[row][col+1] = '0';
                queue.add(new element(row,col + 1));
            }
        }
    }*/
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    //                      并查集
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        //首先要明白，这些函数在哪里使用？
        //find函数只有在union函数中使用，而union函数是在并集的时候使用的，所以这里
        //如果parent[i]等于i，说明当前这个i是集合的根
        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            //这里使用find函数
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                //在这里rank数组派上用场，将小的集合和并到大的集合中
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                //每合并两个元素，总的岛屿个数就要减1
                --count;
            }
        }

        public int getCount() {
            return count;
        }

    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                //找到为1的元素，再查找它的四周有没有为1的点，有的话就将它们设为一个集合
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    //周围有1时，使用union方法将其合并为一个集合
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }
}

class element{
    int row;
    int col;

    public element() {
    }

    public element(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
