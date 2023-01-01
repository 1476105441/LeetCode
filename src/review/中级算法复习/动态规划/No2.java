package review.中级算法复习.动态规划;

public class No2 {
    //              不同路径

    public int uniquePaths(int m,int n){
        int[][] nums = new int[m][n];
        nums[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0) {
                    nums[i][j] += nums[i-1][j];
                }
                if (j != 0) {
                    nums[i][j] += nums[i][j-1];
                }
            }
        }

        return nums[m-1][n-1];
    }
}
