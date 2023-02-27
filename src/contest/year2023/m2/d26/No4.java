package contest.year2023.m2.d26;

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
}
