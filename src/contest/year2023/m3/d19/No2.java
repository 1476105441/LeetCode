package contest.year2023.m3.d19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No2 {
    public boolean checkValidGrid(int[][] grid) {
        if(grid[0][0] != 0) return false;
        int n = grid.length,m = n*n;
        List<int[]> list = new ArrayList<>(m);
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                list.add(new int[]{i,j});
            }
        }
        Collections.sort(list,(x, y) -> grid[x[0]][x[1]] - grid[y[0]][y[1]]);
        for(int i=0;i < m-1;i++){
            int j = i+1;
            if(!check(list.get(i),list.get(j),grid)) return false;
        }
        return true;
    }
    private boolean check(int[] begin,int[] end,int[][] grid){
        int x = Math.abs(begin[0] - end[0]),y = Math.abs(begin[1] - end[1]);
        return (x == 2 && y == 1) || (x == 1 && y == 2);
    }
}
