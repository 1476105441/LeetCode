package senior.树和图;

public class No5 {
    /**
     *          朋友圈
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     *
     * 提示：
     *     1 <= n <= 200
     *     n == isConnected.length
     *     n == isConnected[i].length
     *     isConnected[i][j] 为 1 或 0
     *     isConnected[i][i] == 1
     *     isConnected[i][j] == isConnected[j][i]
     */

    /**
     * 想法：采用并查集解决此问题？
     */
    int[] parent;
    public int findCircleNum(int[][] isConnected){
        int n = isConnected[0].length,res = n;
        parent = new int[n*n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int pj = findParent(j),pi = findParent(i);
                    if (pj != pi) {
                        parent[pj] = pi;
                        res--;
                    }
                }
            }
        }

        return res;
    }

    public int countLoc(int raw,int col,int num){
        return raw*num + col;
    }
    public int findParent(int i){
        if (i != parent[i]) {
            return findParent(parent[i]);
        }
        return i;
    }
}
