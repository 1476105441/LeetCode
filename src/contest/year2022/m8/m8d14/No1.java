package contest.year2022.m8.m8d14;

public class No1 {
    /**
     *      矩阵中的局部最大值
     */

    public static int[][] largestLocal(int[][] grid) {
        int n = grid.length,ri=0,rj=0;
        int[][] res = new int[n-2][n-2];

        for (int i = 0; i < n - 2; i++) {
            int j = 0;
            while(j < n-2){
                int c = 0,max = 0,tj = j;
                while(c < 3){
                    max = Math.max(grid[i][tj],max);
                    max = Math.max(grid[i+1][tj],max);
                    max = Math.max(grid[i+2][tj],max);
                    c++;
                    tj++;
                }
                if(rj == n-2){
                    rj = 0;
                    ri++;
                }
                res[ri][rj] = max;
                rj++;
                j++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        largestLocal(new int[][]{{9,9,8,1},{5,6,2,6},{8,2,6,4},{6,2,2,2}});
    }
}
