package 复习.树和图;

import java.util.LinkedList;
import java.util.Queue;

public class No16 {
    /**
     *      朋友圈
     */

    //使用并查集解决
    /*int[] parent;
    public int findCircleNum(int[][] isConnected){
        int m = isConnected.length,n = isConnected[0].length,res = 0;
        parent = new int[m];

        for (int i = 0; i < m; i++) {
            res++;
            for (int j = i; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    parent[i] = i;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int loc1 = findParent(i),loc2 = findParent(j);
                    if (loc1 != loc2) {
                        parent[loc2] = loc1;
                        res--;
                    }
                }
            }
        }

        return res;
    }
    public int findParent(int i){
        if(parent[i] != i){
            return findParent(parent[i]);
        }
        return i;
    }*/
    
    //深度优先也可以解决，每次将访问过的点标记上
    /*int res;
    boolean[] flags;
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        flags = new boolean[n];
        res = n;

        for (int i = 0; i < n; i++) {
            flags[i] = true;
            dfs(isConnected,i);
        }
        return res;
    }
    public void dfs(int[][] nums,int loc){
        for (int i = 0; i < nums.length; i++) {
            if (loc != i && nums[loc][i] == 1 && !flags[i]) {
                res--;
                flags[i] = true;
                dfs(nums,i);
            }
        }
    }*/

    int res;
    boolean[] flags;
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        res = n;
        flags = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!flags[i]) {
                bfs(isConnected,i);
            }
        }

        return res;
    }
    public void bfs(int[][] nums,int i){
        flags[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 0; j < nums.length; j++) {
            if (!flags[j] && nums[i][j] == 1) {
                res--;
                flags[j] = true;
                queue.add(j);
            }
        }
        while (!queue.isEmpty()) {
            bfs(nums,queue.poll());
        }
    }
}
