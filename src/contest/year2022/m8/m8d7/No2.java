package contest.year2022.m8.m8d7;

import java.util.*;

public class No2 {
    /**
     *      受限条件下可到达节点的数目
     */

    int res = 1;
    Set<Integer> set = new HashSet<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    boolean[] visited;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        for (int i = 0; i < restricted.length; i++) {
            set.add(restricted[i]);
        }

        visited = new boolean[n];
        for (int i = 0; i < edges.length; i++) {
            List<Integer> list = map.get(edges[i][0]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(edges[i][0],list);
            }
            list.add(edges[i][1]);
            list = map.get(edges[i][1]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(edges[i][1],list);
            }
            list.add(edges[i][0]);
        }


        List<Integer> list = map.get(0);
        visited[0] = true;
        find(list);

        return res;
    }
    public void find(List<Integer> list){
        if(list == null)
            return;
        for (int i = 0; i < list.size(); i++) {
            if (!set.contains(list.get(i)) && !visited[list.get(i)]) {
                visited[list.get(i)] = true;
                res++;
                find(map.get(list.get(i)));
            }
        }
    }
}
