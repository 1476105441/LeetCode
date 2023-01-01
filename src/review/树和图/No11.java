package review.树和图;

import java.util.LinkedList;
import java.util.Queue;

public class No11 {
    /**
     *      岛屿数量
     */

    //使用并查集
    /*int[] parent;
    int count;
    public int numIslands(char[][] grid){
        int m = grid.length,n = grid[0].length;
        parent = new int[m*n];
        count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    parent[i * n + j] = i * n + j;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    int index = i*grid[0].length+j;
                    if(i > 1 && grid[i-1][j] == '1'){
                        merge(grid,index,((i-1)*grid[0].length+j));
                    }

                    if(j > 1 && grid[i][j-1] == '1'){
                        merge(grid,index,i*grid[0].length+j-1);
                    }

                    if(i < grid.length-1 && grid[i+1][j] == '1'){
                        merge(grid,index,(i+1)*grid[0].length+j);
                    }

                    if(j < grid[0].length-1 && grid[i][j+1] == '1'){
                        merge(grid,index,i*grid[0].length+j+1);
                    }
                }
            }
        }
        return count;
    }

    public void merge(char[][] grid,int x,int y){
        int rootx = getParent(x),rooty = getParent(y);
        //细节之处，将前面的置为后面的能节省很多时间
        //这是因为后面的是新的节点，设置为这个查找的
        //时间比较少
        if (rootx != rooty) {
            parent[rootx] = y;
            count--;
        }
    }

    public int getParent(int i){
        if (i != parent[i]) {
            return getParent(parent[i]);
        }
        return i;
    }*/

    //这题使用并查集是多此一举了
    /*public int numIslands(char[][] grid){
        int m = grid.length,n = grid[0].length;
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    merge(grid,i,j);
                }
            }
        }
        return res;
    }
    public void merge(char[][] grid,int row,int col){
        grid[row][col] = '*';

        if(row >= 1 && grid[row-1][col] == '1'){
            merge(grid,row-1,col);
        }

        if(col >= 1 && grid[row][col-1] == '1'){
            merge(grid,row,col-1);
        }

        if(row < grid.length-1 && grid[row+1][col] == '1'){
            merge(grid,row+1,col);
        }

        if(col < grid[0].length-1 && grid[row][col+1] == '1'){
            merge(grid,row,col+1);
        }
    }*/

    //广度优先遍历
    int count;
    Queue<int[]> queue;
    public int numIslands(char[][] grid){
        queue = new LinkedList<>();
        count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    queue.add(new int[]{i,j});
                    count++;
                    bfs(grid);
                }
            }
        }

        return count;
    }

    public void bfs(char[][] grid){
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int i = temp[0],j = temp[1];

            if (i > 0 && grid[i - 1][j] == '1') {
                grid[i-1][j] = '0';
                queue.add(new int[]{i-1,j});
            }

            if (j > 0 && grid[i][j - 1] == '1') {
                grid[i][j-1] = '0';
                queue.add(new int[]{i,j-1});
            }

            if (i < grid.length-1 && grid[i+1][j] == '1') {
                grid[i+1][j] = '0';
                queue.add(new int[]{i+1,j});
            }

            if (j < grid[0].length-1 && grid[i][j+1] == '1') {
                grid[i][j+1] = '0';
                queue.add(new int[]{i,j+1});
            }
        }
    }
}
