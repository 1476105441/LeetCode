package practice;

import java.util.Scanner;

public class AchieveTSP {
    //              状态压缩dp
    //使用二进制代表点集
    //假设源点是0
    //没有记录每个点之间的关系
    public int tsp(int[][] dis) {
        int n = dis.length;

        //由于源点不需要考虑进点集中，所以点集的个数是2的n-1次方(此处涉及到n个点组成不同的组合个数问题，一共可以组2的n次方个集合，包括空集)
        int m = (int) Math.pow(2, n - 1), min, temp;
        int[][] dp = new int[n][m];

        //初始化子问题
        for (int i = 0; i < n; i++) {
            dp[i][0] = dis[i][0];
        }

        //对于每个集合来求每个点经过当前集合中所有点到终点的距离
        //由于0是源点和终点，所以不必加上，最后处理时再加上即可
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //先判断当前点是否在点集之中
                //当前点的映射与集合相与之后结果为0则说明集合中没有该点
                if (((int) Math.pow(2, j - 1) & i) == 0) {
                    min = Integer.MAX_VALUE;
                    //将当前点与点集的问题转换成子问题
                    for (int k = 1; k < n; k++) {
                        //要求点k是存在于点集中的
                        if (((int) Math.pow(2, k - 1) & i) != 0) {
                            temp = dis[j][k] + dp[k][i - (int) Math.pow(2, k - 1)];
                            if (temp < min) {
                                min = temp;
                            }
                        }
                    }
                    //最小的值是当前问题的最优解
                    dp[j][i] = min;
                }
            }
        }

        min = Integer.MAX_VALUE;
        //求解最后一步
        for (int i = 1; i < n; i++) {
            temp = dis[0][i] + dp[i][m - 1 - (int) Math.pow(2, i - 1)];
            if (temp < min) {
                min = temp;
            }
        }
        dp[0][m-1] = min;

        return dp[0][m-1];
    }

    public static void main(String[] args) {
        AchieveTSP tsp = new AchieveTSP();

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[][] dis = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = scanner.nextInt();
            }
        }

        System.out.println(tsp.tsp(dis));
    }
}
