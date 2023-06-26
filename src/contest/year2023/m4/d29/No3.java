package contest.year2023.m4.d29;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 网格图中鱼的最大数目
 * @author wjs 2023/6/26
 */
public class No3 {

    //解法一：暴力+广度优先遍历，7ms
    /*static int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public int findMaxFish(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] set = new boolean[m][n];
        int res = 0;
        for(int i=0;i < m;i++) {
            for(int j=0;j < n;j++) {
                if(grid[i][j] > 0 && !set[i][j]) {
                    set[i][j] = true;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i,j});
                    int v = 0;
                    while(!queue.isEmpty()) {
                        int[] tmp = queue.poll();
                        int x = tmp[0], y = tmp[1];
                        v += grid[x][y];
                        for(int[] dir : dirs) {
                            int nx = x + dir[0], ny = y + dir[1];
                            if(nx >= 0 && ny >= 0 && nx < m && ny < n && !set[nx][ny] && grid[nx][ny] > 0) {
                                queue.add(new int[]{nx,ny});
                                set[nx][ny] = true;
                            }
                        }
                    }
                    res = Math.max(res,v);
                }
            }
        }

        return res;
    }*/
}
