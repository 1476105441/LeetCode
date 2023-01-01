package review.树和图;

import java.util.LinkedList;
import java.util.Queue;

public class No19 {
    /**
     *      矩阵中的最长递增路径
     */
    /*int[][] val;
    int[][] visited;
    public int longestIncreasingPath(int[][] matrix){
        val = new int[matrix.length][matrix[0].length];
        visited = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (val[i][j] == 0) {
                    int temp = dfs(matrix,i,j,Integer.MAX_VALUE+1L);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }

    public int dfs(int[][] matrix,int i,int j,long pre){
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || pre <= matrix[i][j] || visited[i][j] == 1) {
            return 0;
        }
        if (val[i][j] != 0) {
            return val[i][j];
        }
        visited[i][j] = 1;
        int v1,v2,v3,v4;
        v1 = dfs(matrix,i-1,j,matrix[i][j]);
        v2 = dfs(matrix,i,j-1,matrix[i][j]);
        v3 = dfs(matrix,i+1,j,matrix[i][j]);
        v4 = dfs(matrix,i,j+1,matrix[i][j]);

        v1 = Math.max(v1,v2);
        v3 = Math.max(v3,v4);
        val[i][j] = Math.max(v1,v3)+1;
        visited[i][j] = 0;
        return val[i][j];
    }*/

    //使用拓扑排序(广度优先遍历)
    public int longestIncreasingPath(int[][] matrix){
        int rowNums = matrix.length,colNums = matrix[0].length;
        int[][] count = new int[rowNums][colNums];
        int[][] bound = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};

        for (int i = 0; i < rowNums; i++) {
            for (int j = 0; j < colNums; j++) {
                for (int[] temp : bound){
                    int newR = i + temp[0],newC = j + temp[1];
                    if (newR >= 0 && newR < rowNums && newC >= 0 && newC < colNums && matrix[newR][newC] > matrix[i][j]) {
                        count[i][j]++;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rowNums; i++) {
            for (int j = 0; j < colNums; j++) {
                if (count[i][j] == 0) {
                    queue.add(new int[]{i,j});
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            while (size > 0) {
                int[] node = queue.poll();
                for (int[] temp : bound) {
                    int newR = node[0] + temp[0],newC = node[1]+temp[1];
                    if(newR >= 0 && newR < rowNums && newC >= 0 && newC < colNums && matrix[newR][newC] < matrix[node[0]][node[1]]){
                        count[newR][newC]--;
                        if (count[newR][newC] == 0) {
                            queue.add(new int[]{newR,newC});
                        }
                    }
                }
                size--;
            }
        }

        return res;
    }
}
