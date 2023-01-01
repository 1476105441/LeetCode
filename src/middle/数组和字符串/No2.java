package middle.数组和字符串;

public class No2 {
    //                  矩阵置零
    //给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
    //
    //进阶：
    //    一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    //    一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    //    你能想出一个仅使用常量空间的解决方案吗？

    //-------------------------------------------------------------------------------
    //暂时只有O(m+n)的解决方法的思路：使用一个元素个数为m的数组存放要归零的行号，使用
    // 元素个数为n的数组存放要归零的列号，先遍历一遍二维数组进行记录，然后再进行归零操作
    /*
    public void setZeroes(int[][] matrix) {
        int[] col = new int[matrix[0].length];
        int[] row = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    col[j] = 1;
                    row[i] = 1;
                }
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < col.length; i++) {
            if (col[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }*/
    //-------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------
    //                  使用常数的额外空间解决本问题
    //可以使用第一行，第一列来存放要全部置为零的行和列，即将要全部置为零的行和列在第一行第一列中的元素置
    // 为零，最后再遍历进行置零操作
    //注意：要是第一行第一列中一开始有零的话要记录下来，最后置零的时候将第一行和第一列也置为零
    public void setZeroes(int[][] matrix) {
        boolean col = false, row = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        row = true;
                    }
                    if (j == 0) {
                        col = true;
                    }
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //将第一行和第一列中有零的列全部置为零
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (col) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
