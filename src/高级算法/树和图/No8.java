package 高级算法.树和图;

import java.util.HashMap;
import java.util.Map;

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
}
