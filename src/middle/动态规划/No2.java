package middle.动态规划;

public class No2 {
    //              不同路径
    //一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    //机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    //问总共有多少条不同的路径？

    //当前元素的步骤数量只与左边的和上面的元素有关（相加）
    public int uniquePaths(int m, int n) {
        int[][] nums = new int[m][n];

        for (int i = 0; i < m; i++) {
            nums[i][0] = 1;
            for (int j = 1; j < n; j++) {
                if (i == 0) {
                    nums[i][j] = nums[i][j-1];
                }else{
                    nums[i][j] = nums[i][j-1] + nums[i-1][j];
                }
            }
        }

        return nums[m-1][n-1];
    }
}
