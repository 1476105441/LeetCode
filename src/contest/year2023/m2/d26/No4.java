package contest.year2023.m2.d26;

import java.util.ArrayDeque;
import java.util.Arrays;
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
    /*int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
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
    }*/


    /**
     * ***************************重写一遍************************************
     */
    //解法一：dijkstra算法，本质上是将数组看做是图模型，使用最小
    //生成树算法计算起点到其他节点之间的距离
    //private static int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
    /*int[][] dis;
    public int minimumTime(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }
        dis = new int[m][n];
        for(int i = 0;i < m;i++){
            Arrays.fill(dis[i],2147483647);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((x,y) -> x[0]-y[0]);
        queue.add(new int[]{0,0,0});
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int val = temp[0],i = temp[1],j = temp[2];
            if(i == m-1 && j == n-1){
                return val;
            }
            for(int[] q : dirs){
                int x = i + q[0],y = j + q[1];
                int nv = val + 1;
                if(x >= 0 && x < m && y >= 0 && y < n){
                    nv = Math.max(nv,grid[x][y]);
                    nv += (nv - x - y) % 2; //奇偶性要一致
                    if(nv < dis[x][y]){
                        dis[x][y] = nv;
                        queue.add(new int[]{nv,x,y});
                    }
                }
            }
        }
        return -1;
    }*/


    //解法二：二分查找
    static int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
    int[][] grid;
    public int minimumTime(int[][] grid) {
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }
        int m = grid.length,n = grid[0].length;
        int l = 0,r = 100001;
        this.grid = grid;
        while(l < r){
            int c = l + ((r-l) >> 1);
            if(check(c)){
                r = c;
            } else {
                l = c + 1;
            }
        }
        //System.out.println(l);
        //得要纠正答案的奇偶性
        l += Math.abs(l - m - n + 2) % 2;
        return l;
    }
    //这个版本289ms
    /*private boolean check(int val){
        int m = grid.length,n = grid[0].length;
        if(val < grid[m-1][n-1] || val == 0){
            return false;
        }
        boolean[][] set = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        val -= (val - m - n + 2) % 2;//变为同样奇偶性的值
        queue.add(new int[]{val,m-1,n-1});
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int v = temp[0],i = temp[1],j = temp[2];
            v--;//每一轮之后减少
            for(int[] q : dirs){
                int x = i + q[0],y = j + q[1],t = v;
                if(x >= 0 && x < m && y >= 0 && y < n && !set[x][y]){
                    t -= (t - x - y) % 2;//纠正奇偶性
                    if(t >= grid[x][y]){
                        if(x == 0 && y == 0){
                            return true;
                        }
                        set[x][y] = true;
                        queue.add(new int[]{t,x,y});
                    }
                }
            }
        }
        return false;
    }*/
    //这个版本261ms
    /*private boolean check(int val){
        int m = grid.length,n = grid[0].length;
        if(val < grid[m-1][n-1]){
            return false;
        }
        boolean[][] set = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{m-1,n-1});
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int[] temp = queue.poll();
                int i = temp[0],j = temp[1];
                for(int[] q : dirs){
                    int x = i + q[0],y = j + q[1],t = val - 1;
                    if(x >= 0 && x < m && y >= 0 && y < n && !set[x][y]){
                        t -= (t - x - y) % 2;//纠正奇偶性
                        if(t >= grid[x][y]){
                            if(x == 0 && y == 0){
                                return true;
                            }
                            set[x][y] = true;
                            queue.add(new int[]{x,y});
                        }
                    }
                }
                size--;
            }
            val--;
        }
        return false;
    }*/
    //这个版本267ms，不用频繁创建记忆数组了
    int[][] set;
    private boolean check(int val){
        int m = grid.length,n = grid[0].length;
        if(val < grid[m-1][n-1]){
            return false;
        }
        if(set == null){
            set = new int[m][n];
            for(int i = 0;i < m;i++){
                Arrays.fill(set[i],2147483647);
            }
        }
        int tmp = val;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{m-1,n-1});
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int[] temp = queue.poll();
                int i = temp[0],j = temp[1];
                for(int[] q : dirs){
                    int x = i + q[0],y = j + q[1],t = val - 1;
                    if(x >= 0 && x < m && y >= 0 && y < n && tmp != set[x][y] && t >= grid[x][y]){
                        if(x == 0 && y == 0){
                            return true;
                        }
                        set[x][y] = tmp;
                        queue.add(new int[]{x,y});
                    }
                }
                size--;
            }
            val--;
        }
        return false;
    }
}
