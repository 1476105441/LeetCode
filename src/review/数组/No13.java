package review.数组;

public class No13 {
    /**
     *      矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     *
     * 提示：
     *     m == matrix.length
     *     n == matrix[0].length
     *     1 <= m, n <= 200
     *     -231 <= matrix[i][j] <= 231 - 1
     *
     * 进阶：
     *     一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     *     一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     *     你能想出一个仅使用常量空间的解决方案吗？
     */

    //使用第一行和第一列进行存放归零的标识，使用
    //额外的两个变量标识第一行和第一列是否需要归零
    public void setZeroes(int[][] matrix){
        boolean rowFlag = false,colFlag = false;

        //首先判断第一行和第一列是否需要归零
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowFlag = true;
                break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[0][j] != 0 || matrix[i][0] != 0) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < matrix.length;j++){
                    matrix[j][i] = 0;
                }
            }
        }

        if (rowFlag) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        if (colFlag) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
