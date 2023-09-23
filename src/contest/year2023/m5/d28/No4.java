package contest.year2023.m5.d28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2713. 矩阵中严格递增的单元格数
 *
 * @author wjs 2023/9/21
 */
public class No4 {
    int m,n;
    int[][] vis,mat;
    List<Integer>[] sortRow,sortCol;
    public int maxIncreasingCells(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        vis = new int[m][n];
        this.mat = mat;
        sortRow = new List[m];
        sortCol = new List[n];
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            sortRow[i] = new ArrayList<>();
            List<Integer> integers = sortRow[i];
            for (int j = 0; j < n; j++) {
                integers.add(cnt);
                cnt++;
            }
            integers.sort((x, y) -> mat[x / n][x % n] - mat[y / n][y % n]);
        }
        for (int i = 0; i < n; i++) {
            cnt = i;
            sortCol[i] = new ArrayList<>();
            List<Integer> integers = sortCol[i];
            for (int j = 0; j < m; j++) {
                integers.add(cnt);
                cnt += n;
            }
            integers.sort((x, y) -> mat[x / n][x % n] - mat[y / n][y % n]);
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = dfs(i,j);
                res = Math.max(res,t);
            }
        }
        return res;
    }

    private int dfs(int i, int j) {
        if (vis[i][j] != 0) {
            return vis[i][j];
        }
        int val = 1;
        vis[i][j] = -1;
        List<Integer> row = sortRow[i],col = sortCol[j];
        int loc = findLoc(row,mat[i][j]);
        if (loc != -1) {
            for (int k = loc; k < n; k++) {
                int ni = row.get(k) / n;
                int nj = row.get(k) % n;
                int tmp = dfs(ni,nj) + 1;
                val = Math.max(val,tmp);
            }
        }
        loc = findLoc(col,mat[i][j]);
        if (loc != -1) {
            for (int k = loc; k < m; k++) {
                int ni = col.get(k) / n;
                int nj = col.get(k) % n;
                int tmp = dfs(ni,nj) + 1;
                val = Math.max(val,tmp);
            }
        }
        vis[i][j] = val;
        return vis[i][j];
    }

    private int findLoc(List<Integer> list, int val) {
        int x = list.size();
        int l = 0, r = x-1;
        int res = -1;
        while (l <= r) {
            int c = l + ((r-l) >> 1);
            int i = list.get(c) / n;
            int j = list.get(c) % n;
            if (mat[i][j] > val) {
                res = c;
                r = c-1;
            } else {
                l = c+1;
            }
        }
        return res;
    }
}
