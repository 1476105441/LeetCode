package oj周赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No5 {
    //旅行家问题：使用动态规划可解决
    //注意：此题不用回到原点
    //首先还要解决一个知识点：n个数任取多个数有多少种不同的组合，答案是：2的n次方
    //此题中出发点是0
    //最难以解决的问题：怎么表示对应的点集，并且判断点集中是否包含当前点？

    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);

        int n;
        n = s.nextInt();

        //距离数组
        int[][] dis = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = s.nextInt();
            }
        }

        //行代表哪个元素，列代表当前的点集合
        //此二维数组存储的是从当前城市出发，经过点集到达出发点的最短路径
        //此题为n-2是因为起始点和终点都已经确定了，所以只有n-2个点来构成点集
        int m = (int)Math.pow(2,n-2),min;
        //存储的是最短距离
        int[][] d = new int[n][m];
        //存储的是最短距离时对应的下一个点
        int[][] p = new int[n][m];

        for (int i = 1; i < n-1; i++) {
            //初始化，经过0个点到达终点的距离
            d[i][0] = dis[i][n-1];
        }

        //外层循环是点集，内层循环是每个点，意思是按照顺序，每个点对每个点集来求对应的
        // 最短距离（该点经过这个点集中的每个点到达出发点的最短距离），此处需要注意，按
        // 照顺序来求对应点集是可以行的通的，因为：001代表只有编号为1的点的点集，010代
        // 表只有编号为2的点的点集，011代表有编号为1和编号为2的点的点集，而在计算其余点
        // 与011的最短距离时只需要用到编号为1的点与010点集和编号为2的点与001点集，而这
        // 两结果在前面已经求出
        for (int j = 1; j < m-1; j++) {
            for (int i = 1; i < n-1; i++) {
                //Math.pow(2,i-1)代表当前点在点集中的位置（此描述不太准确），与j（点集）进行&运算之
                // 后，若是为0则说明当前点集j中不包含当前点i
                //不包含的原因：如果点集j包含当前点i，则在点集的二进制表示中，在i对应的位置（Math.pow(2,i-1)）
                // 上会是1，再进行&运算，结果就不会是0；所以为0时就说明当前点集j不包含i
                if (((int)Math.pow(2,i-1) & j) == 0) {
                    min = Integer.MAX_VALUE;

                    //使用动态规划计算当前点i经过当前点集j的所有点一次，再到终点的最短距离
                    //现在要寻找当前点集中所包含的点，因为动态规划方程：
                    // d[i][j] = cik + d[k][j-k]
                    for (int k = 1; k < n-1; k++) {
                        //不等于0时就是当前点集中包含k
                        if ((((int)Math.pow(2,k-1)) & j) != 0) {
                            //当前点集j将k对应的位置Math.pow(2,k-1)减掉即为不包含k的点集（j的子集）
                            if (dis[i][k] + d[k][j - (int)Math.pow(2,k-1)] < min) {
                                min = dis[i][k] + d[k][j - (int)Math.pow(2,k-1)];
                                //存储达到最短距离的上一个节点
                                p[i][j] = k;
                            }
                        }
                    }
                    d[i][j] = min;
                }
            }
        }

        min = Integer.MAX_VALUE;
        //最后一步：将起点与最短距离连接起来
        for (int i = 1; i < n-1; i++) {
            if (dis[0][i] + d[i][m - 1 -(int)Math.pow(2,i-1)] < min) {
                min = dis[0][i] + d[i][m - 1 -(int)Math.pow(2,i-1)];
                p[0][m-1] = i;
            }
        }
        d[0][m-1] = min;
        System.out.println(d[0][m-1]);
    }
}

