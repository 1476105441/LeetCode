package contest.year2022.m9.m9d25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No4 {
    /**
     * 好路径的数目
     */

    //几经修改，最终还是超时
    /*int res;
    int[] set; //0表示当前未遍历过，1表示当前遍历过，2表示已经为起点遍历过
    List<List<Integer>> edge;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        res = n;
        set = new int[res];
        edge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            int x = edges[i][0], y = edges[i][1];
            edge.get(x).add(y);
            edge.get(y).add(x);
        }
        for (int i = 0; i < n; i++) {
            if (set[i] == 0) {
                set[i] = 1;
                for (Integer j : edge.get(i)) {
                    if(vals[j] <= vals[i])
                        dfs(vals, vals[i], j);
                }
                set[i] = 2;
            }
        }
        //System.out.println(set[1]);
        return res;
    }

    public void dfs(int[] vals, int startVal, int loc) {
        if (set[loc] == 1)
            return;
        if(vals[loc] == startVal && set[loc] != 2){
            //System.out.println(loc);
            res++;
        }
        int temp = set[loc];
        set[loc] = 1;
        for (Integer i : edge.get(loc)) {
            if(vals[i] <= startVal)
                dfs(vals,startVal,i);
        }
        set[loc] = temp;
    }*/

    //不会写，观看题解
    //43ms，基本上相当于照着题解写了一遍，还没有领悟到精髓
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length,res = n;
        father = new int[n];
        int[] size = new int[n];
        Integer[] id = new Integer[n];
        List<List<Integer>> list = new ArrayList<>(n);
        for(int i = 0;i < n;i++){
            father[i] = i;
            id[i] = i;
            size[i] = 1;
            list.add(new ArrayList<>());
        }
        for(int i = 0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            list.get(x).add(y);
            list.get(y).add(x);
        }

        //讲下标按照vals从小到大的顺序排序，从最小的值开始合并，这样才能保证每个相同的值都会被合并到
        Arrays.sort(id,(x, y)->vals[x] - vals[y]);

        for(int i = 0;i < n;i++){
            int x = find(id[i]);
            for(Integer j : list.get(id[i])){
                int y = find(j);
                if(y == x || vals[y] > vals[x]) continue;
                if(vals[y] == vals[x]){
                    res += size[y] * size[x];
                    size[x] += size[y];
                }
                father[y] = x;
            }
        }
        return res;
    }
    int[] father;
    //使用压缩法的并查集
    int find(int i){
        if(father[i] != i) father[i] = find(father[i]);
        return father[i];
    }
}
