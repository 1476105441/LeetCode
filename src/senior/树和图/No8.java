package senior.树和图;

import java.util.*;

public class No8 {
    /**
     *      矩阵中的最长递增路径
     * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     *
     * 提示：
     *     m == matrix.length
     *     n == matrix[i].length
     *     1 <= m, n <= 200
     *     0 <= matrix[i][j] <= 231 - 1
     */

    /**
     * 思路：深度优先遍历+剪枝
     *
     * 每个遍历过的节点都要存储它的最长路径值
     *
     * 开始：从没有遍历过的节点开始往四周遍历
     * 若是当前遍历的点已经遍历过了，直接返回
     * 它的值，若是没有遍历过则向其四周遍历，
     * 最终从四周返回值的最大数加一就是当前点
     * 的最长路径
     */

    //成功，15ms，比较慢
    /*Map<Integer,Integer> map;
    public int longestIncreasingPath(int[][] matrix) {
        map = new HashMap<>();
        int max = 0,loc = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int val = dfs(matrix,i,j,loc);
                if (max < val) {
                    max = val;
                }
                loc++;
            }
        }

        return max;
    }

    public int dfs(int[][] matrix,int row,int col,int loc){
        Integer val = map.get(loc);
        if (val != null) {
            return val;
        }

        int val1=0,val2=0,val3=0,val4=0;

        if (row > 0 && matrix[row][col] > matrix[row-1][col]) {
            val1 = dfs(matrix,row-1,col,loc-matrix[0].length);
        }
        if (col > 0 && matrix[row][col] > matrix[row][col-1]) {
            val2 = dfs(matrix,row,col-1,loc-1);
        }
        if (row < matrix.length-1 && matrix[row][col] > matrix[row+1][col]){
            val3 = dfs(matrix,row+1,col,loc+matrix[0].length);
        }
        if (col < matrix[0].length-1 && matrix[row][col] > matrix[row][col+1]){
            val4 = dfs(matrix,row,col+1,loc+1);
        }
        int max = max(val1,val2,val3,val4);
        map.put(loc,max+1);
        return max+1;
    }

    public int max(int v1,int v2,int v3,int v4){
        if (v1 > v2) {
            if (v1 > v3) {
                if (v1 > v4) {
                    return v1;
                }else {
                    return v4;
                }
            }else{
                if (v3 > v4) {
                    return v3;
                }else {
                    return v4;
                }
            }
        }else{
            if (v2 > v3) {
                if (v2 > v4) {
                    return v2;
                }else {
                    return v4;
                }
            }else{
                if (v3 > v4) {
                    return v3;
                }else {
                    return v4;
                }
            }
        }
    }*/

    //重写版本，7ms
    //dfs+记忆化
    /*int[][] dp,matrix;
    int m,n;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        int res = 0;
        dp = new int[m][n];

        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(dp[i][j] == 0){
                    dfs(i,j,-1);
                    if(dp[i][j] > res){
                        res = dp[i][j];
                    }
                }
            }
        }

        return res;
    }
    public int dfs(int i,int j,int pre){
        if(i < 0 || j < 0 || i >= m || j >= n || (pre != -1 && matrix[i][j] >= pre)){
            return 0;
        }
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int l,r,up,down,res;
        l = dfs(i,j-1,matrix[i][j]);
        r = dfs(i,j+1,matrix[i][j]);
        up = dfs(i-1,j,matrix[i][j]);
        down = dfs(i+1,j,matrix[i][j]);
        res = Math.max(l,r);
        res = Math.max(up,res);
        res = Math.max(down,res);
        dp[i][j] = res+1;

        return res+1;
    }*/

    //拓扑排序，20ms，比官方题解慢
    /*List<List<Integer>> edge;
    int[][] matrix;
    int[] count;
    int m,n;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int res = 0,l = m*n;
        this.matrix = matrix;
        count = new int[l];
        edge = new ArrayList<>();

        for(int i = 0;i < l;i++){
            edge.add(new ArrayList<>());
        }
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                test(i,j);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i < l;i++){
            if(count[i] == 0)
                queue.offer(i);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            while(size > 0){
                int x = queue.poll();
                for(Integer y : edge.get(x)){
                    if(--count[y] == 0){
                        queue.offer(y);
                    }
                }
                size--;
            }
        }
        return res;
    }
    public void test(int i,int j){
        int loc = i*n+j,tmp;
        if(i > 0 && matrix[i-1][j] < matrix[i][j]){
            tmp = loc - n;
            count[loc]++;
            edge.get(tmp).add(loc);
        }
        if(j > 0 && matrix[i][j-1] < matrix[i][j]){
            tmp = loc-1;
            count[loc]++;
            edge.get(tmp).add(loc);
        }
        if(i < m-1 && matrix[i+1][j] < matrix[i][j]){
            tmp = loc+n;
            count[loc]++;
            edge.get(tmp).add(loc);
        }
        if(j < n-1 && matrix[i][j+1] < matrix[i][j]){
            tmp = loc+1;
            count[loc]++;
            edge.get(tmp).add(loc);
        }
    }*/

    int[][] dirs = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length,res = 0;
        int[][] count = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                for(int[] dir : dirs){
                    int r = i+dir[0],c = j+dir[1];
                    if(r>=0 && r<m && c>=0 && c<n && matrix[r][c] < matrix[i][j]){
                        count[i][j]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(count[i][j] == 0){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            while(size > 0){
                int[] x = queue.poll();
                for(int[] dir : dirs){
                    int r = x[0]+dir[0],c = x[1]+dir[1];
                    if(r>=0 && r<m && c>=0 && c<n && matrix[r][c] > matrix[x[0]][x[1]]){
                        if(--count[r][c] == 0){
                            queue.offer(new int[]{r,c});
                        }
                    }
                }
                size--;
            }
        }

        return res;
    }
}
