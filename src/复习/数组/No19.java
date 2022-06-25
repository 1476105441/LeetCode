package 复习.数组;

import java.util.ArrayList;
import java.util.List;

public class No19 {
    /**
     *      螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     * 提示：
     *     m == matrix.length
     *     n == matrix[i].length
     *     1 <= m, n <= 10
     *     -100 <= matrix[i][j] <= 100
     */

    //上下左右四个边界
    public List<Integer> spiralOrder(int[][] matrix){
        int m = matrix.length,n = matrix[0].length,left = 0,right = n - 1,up = 1,down = m - 1;
        int count = m*n,i = 0,j = 0;
        List<Integer> res = new ArrayList<>();

        while (true) {
            while (j < right) {
                res.add(matrix[i][j]);
                count--;
                j++;
            }
            right--;
            if (count == 1) {
                res.add(matrix[i][j]);
                break;
            }
            while (i < down) {
                res.add(matrix[i][j]);
                i++;
                count--;
            }
            down--;
            if (count == 1) {
                res.add(matrix[i][j]);
                break;
            }
            while (j > left) {
                res.add(matrix[i][j]);
                j--;
                count--;
            }
            left++;
            if (count == 1) {
                res.add(matrix[i][j]);
                break;
            }
            while (i > up) {
                res.add(matrix[i][j]);
                count--;
                i--;
            }
            up++;
            if (count == 1) {
                res.add(matrix[i][j]);
                break;
            }
        }

        return res;
    }
}
