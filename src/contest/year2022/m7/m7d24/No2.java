package contest.year2022.m7.m7d24;

import java.util.HashMap;
import java.util.Map;

public class No2 {
    /**
     *      相等行列对
     */

    public int equalPairs(int[][] grid) {
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(grid[i][j]).append(',');
            }
            map.put(sb.toString(),map.getOrDefault(sb.toString(),0)+1);
        }

        int res = 0;
        for (int i = 0; i < grid[0].length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < grid.length; j++) {
                sb.append(grid[j][i]).append(',');
            }
            Integer c = map.get(sb.toString());
            if(c != null)
                res += c;
        }

        return res;
    }

    class Node{
        boolean isEnd;
        Node[] next;
        public Node(){
            next = new Node[26];
            isEnd = false;
        }
    }
}
