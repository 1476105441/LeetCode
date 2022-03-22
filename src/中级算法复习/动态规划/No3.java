package 中级算法复习.动态规划;

public class No3 {
    //              零钱兑换

    /*public int coinChange(int[] coins, int amount){
        int n = coins.length,res = -1;
        int[][] temp = new int[n+1][amount+1];
        temp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            temp[i][0] = 0;
        }
        for (int i = 0; i <= amount; i++) {
            temp[0][i] = 100000;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i-1] > j) {
                    temp[i][j] = temp[i-1][j];
                }else{
                    if (temp[i-1][j] < temp[i][j-coins[i-1]]+1) {
                        temp[i][j] = temp[i-1][j];
                    }else{
                        temp[i][j] = temp[i][j-coins[i-1]]+1;
                    }
                }
            }
        }

        if (temp[n][amount] < 100000) {
            res = temp[n][amount];
        }
        return res;
    }*/

    /**
     * 此题目不必使用二维数组，使用一维数组即可
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length, res = -1;
        int[] temp = new int[amount + 1];
        temp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            temp[i] = 100000;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= coins[j]) {
                    if (temp[i] > temp[i - coins[j]] + 1) {
                        temp[i] = temp[i - coins[j]] + 1;
                    }
                }
            }
        }

        if (temp[amount] < 100000) {
            res = temp[amount];
        }
        return res;
    }
}
