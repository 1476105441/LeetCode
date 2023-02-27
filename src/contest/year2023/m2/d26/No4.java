package contest.year2023.m2.d26;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class No4 {
    //想法：先找出一条“最短”的路径
    //然后再按照这个路径走，失败
    /*int[][] preIndex;
    int[][] grid;
    boolean[][] set;
    public int minimumTime(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int time = 0;
        preIndex = new int[m*n][3];
        this.grid = grid;
        set = new boolean[m][n];
        if(grid[0][1] > grid[0][0] + 1 && grid[1][0] > grid[0][0] + 1){
            return -1;
        }
        for(int i = 0;i < m*n;i++){
            preIndex[i][0] = 1000000;
        }
        dfs(0,0,-1,-1,0);
        //System.out.println("执行到此处了1111");
        int i = m-1,j = n-1;
        while(i != -1){
            int loc = i * n + j;
            set[i][j] = true;
            i = preIndex[loc][1];
            j = preIndex[loc][2];
        }
        //System.out.println("执行到此处了2222");
        i = 0;
        j = 0;
        for(int k = 0;k < m;k++){
            for(int l = 0;l < n;l++){
                System.out.print(set[k][l] + "  ");
            }
            System.out.println();
        }
        while(!(i == m-1 && j == n-1)){
            set[i][j] = false;
            if(i > 0 && set[i-1][j]){
                while(time < grid[i-1][j] - 1){
                    time += 2;
                }
                time++;
                i--;
            }else if(j > 0 && set[i][j-1]){
                while(time < grid[i][j-1] - 1){
                    time += 2;
                }
                time++;
                j--;
            } else if(i < m-1 && set[i+1][j]){
                while(time < grid[i+1][j] - 1){
                    time += 2;
                }
                time++;
                i++;
            } else if(j < n-1 && set[i][j+1]){
                while(time < grid[i][j+1] - 1){
                    time += 2;
                }
                time++;
                j++;
            }
        }
        //System.out.println("执行到此处了3333");
        return time;
    }
    private void dfs(int i,int j,int pi,int pj,int max){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length){
            return;
        }
        if(set[i][j]){
            return;
        }
        set[i][j] = true;
        max = Math.max(max,grid[i][j]);
        int loc = i * grid.length + j;
        if(preIndex[loc][0] > max){
            preIndex[loc][0] = max;
            preIndex[loc][1] = pi;
            preIndex[loc][2] = pj;
            dfs(i,j+1,i,j,max);
            dfs(i+1,j,i,j,max);
            dfs(i,j-1,i,j,max);
            dfs(i-1,j,i,j,max);
        }
        set[i][j] = false;
    }*/

    //解法一：使用dijkstra构造最短路径，131ms
    /*private static int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
    public int minimumTime(int[][] grid) {
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }
        int m = grid.length,n = grid[0].length;
        int[][] dis = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                dis[i][j] = Integer.MAX_VALUE;
            }
        }
        dis[0][0] = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        queue.add(new int[]{0,0,0});
        for(;;){
            int[] temp = queue.poll();
            int val = temp[0],i = temp[1],j = temp[2];
            if(i == m-1 && j == n-1){
                return val;
            }
            for(int[] dir : dirs){
                int x = i + dir[0],y = j + dir[1];
                if(0 <= x && x < m && 0 <= y && y < n){
                    int nv = Math.max(val+1,grid[x][y]);
                    nv += (nv - x - y) % 2; //使时间和当前节点数值的奇偶性相同
                    if(nv < dis[x][y]){
                        dis[x][y] = nv;
                        queue.add(new int[]{nv,x,y});
                    }
                }
            }
        }
    }*/

    //解法二：二分，这个思路太牛逼了，332ms
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int[][] grid;
    public int minimumTime(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }
        this.grid = grid;
        int l = 0,r = 1000000,res = 0;
        while(l <= r){
            int c = l + ((r-l) >> 1);
            if(check(c)){
                res = c;
                r = c - 1;
            } else {
                l = c + 1;
            }
        }
        //最终结果要和结尾的奇偶性相同
        res += (res + m + n) % 2;
        return res;
    }
    private boolean check(int val){
        int m = grid.length,n = grid[0].length;
        if(val < grid[m-1][n-1]){
            return false;
        }
        boolean[][] set = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        set[m-1][n-1] = true;
        queue.add(new int[]{m-1,n-1});
        //走的过程中要使time减小
        int t = val-1;
        while(!queue.isEmpty()){
            int size = queue.size();
            while (size > 0) {
                int[] temp = queue.poll();
                int i = temp[0],j = temp[1];
                for(int[] dir : dirs){
                    int x = i + dir[0],y = j + dir[1];
                    if(0 <= x && x < m && 0 <= y && y < n && !set[x][y]){
                        if(grid[x][y] <= t){
                            if(x == 0 && y == 0){
                                return true;
                            }
                            queue.add(new int[]{x,y});
                            set[x][y] = true;
                        }
                    }
                }
                size--;
            }
            //bfs一轮之后将时间减小
            t--;
        }
        return false;
    }
}
