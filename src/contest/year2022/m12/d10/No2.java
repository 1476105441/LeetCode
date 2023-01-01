package contest.year2022.m12.d10;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    //深度优先遍历，在每个节点中选出最大的k个值
    /*List<Integer>[] lists;
    int[] vals;
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        this.vals = vals;
        int n = vals.length,res = Integer.MIN_VALUE;
        lists = new List[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>(k);
        }
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0],y = edges[i][1];
            execute(x,y,k);
            execute(y,x,k);
        }
        for (int i = 0; i < n; i++) {
            int temp = vals[i];
            for (Integer j : lists[i]) {
                if(temp < temp + vals[j])
                    temp += vals[j];
            }
            res = Math.max(res,temp);
        }
        return res;
    }
    public void execute(int a,int b,int k){
        if (lists[a].size() < k) {
            lists[a].add(b);
            update(lists[a],lists[a].size()-1);
        } else if (vals[b] > vals[lists[a].get(0)]) {
            lists[a].set(0,b);
            shift(lists[a],0,k);
        }
    }
    public void shift(List<Integer> temp,int loc ,int n){
        int i = loc,j = (i << 1) + 1;
        while (j < n) {
            if(j + 1 < n && vals[temp.get(j)] > vals[temp.get(j+1)])
                j++;
            if (vals[temp.get(i)] > vals[temp.get(j)]) {
                int tmp = temp.get(i);
                temp.set(i,temp.get(j));
                temp.set(j,tmp);
                i = j;
                j = (i << 1)+1;
            } else {
                break;
            }
        }
    }
    public void update(List<Integer> temp,int loc){
        int i = loc,p = (i-1) >> 1;
        while (i > 0) {
            if (vals[temp.get(i)] < vals[temp.get(p)]) {
                int tmp = temp.get(i);
                temp.set(i,temp.get(p));
                temp.set(p,tmp);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }*/

    //使用堆，比赛时可以通过，加个特判也还是能够通过
    /*List<List<Integer>> edge;
    int[] vals;
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        edge = new ArrayList<>();
        this.vals = vals;
        int n = vals.length,res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0],y = edges[i][1];
            edge.get(x).add(y);
            edge.get(y).add(x);
        }
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>(k);
            int temp = vals[i];
            for (Integer j : edge.get(i)) {
                if (list.size() < k) {
                    list.add(j);
                    update(list,list.size()-1);
                }else if(k > 0 && vals[list.get(0)] < vals[j]){
                    list.set(0,j);
                    shift(list,0,k);
                }
            }
            for (Integer j : list) {
                if (temp + vals[j] > temp) {
                    temp += vals[j];
                }
            }
            res = Math.max(res,temp);
        }
        return res;
    }

    public void shift(List<Integer> temp,int loc ,int n){
        int i = loc,j = (i << 1) + 1;
        while (j < n) {
            if(j + 1 < n && vals[temp.get(j)] > vals[temp.get(j+1)])
                j++;
            if (vals[temp.get(i)] > vals[temp.get(j)]) {
                int tmp = temp.get(i);
                temp.set(i,temp.get(j));
                temp.set(j,tmp);
                i = j;
                j = (i << 1)+1;
            } else {
                break;
            }
        }
    }
    public void update(List<Integer> temp,int loc){
        int i = loc,p = (i-1) >> 1;
        while (i > 0) {
            if (vals[temp.get(i)] < vals[temp.get(p)]) {
                int tmp = temp.get(i);
                temp.set(i,temp.get(p));
                temp.set(p,tmp);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }*/

    //使用jdk提供的集合类，未知错误，看了半天没看出来哪里错了
    /*public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0],y = edges[i][1];
            edge.get(x).add(y);
            edge.get(y).add(x);
        }
        int res = Integer.MIN_VALUE;
        TreeSet<Integer> treeSet;
        for (int i = 0; i < n; i++) {
            int temp = vals[i],c = 0;
            treeSet = new TreeSet<>((x,y)-> vals[x] - vals[y]);
            //构造最大的前k个节点
            for (Integer j : edge.get(i)) {
                int l;
                if (c < k) {
                    treeSet.add(j);
                    c++;
                } else if (k > 0 && vals[(l = treeSet.first())] < vals[j]) {
                    treeSet.add(j);
                    treeSet.remove(l);
                }
            }
            for (Integer j : treeSet) {
                if(0 < vals[j])
                    temp += vals[j];
            }
            res = Math.max(res,temp);
        }
        return res;
    }*/


    //使用排序反而更快，为什么？50多ms
    /*public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0],y = edges[i][1];
            if(vals[y] > 0)
                edge.get(x).add(y);
            if(vals[x] > 0)
                edge.get(y).add(x);
        }
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = vals[i];
            List<Integer> list = edge.get(i);
            list.sort((x,y)->vals[y]-vals[x]);
            for (int j = 0; j < Math.min(k,list.size()); j++) {
                if(vals[list.get(j)] < 0)
                    break;
                temp += vals[list.get(j)];
            }
            res = Math.max(res,temp);
        }

        return res;
    }*/

    //最快版本，39ms
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0],y = edges[i][1];
            if(vals[y] > 0)
                edge.get(x).add(vals[y]);
            if(vals[x] > 0)
                edge.get(y).add(vals[x]);
        }
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = vals[i];
            List<Integer> list = edge.get(i);
            list.sort((x,y)->y-x);
            for (int j = 0; j < Math.min(k,list.size()); j++) {
                temp += list.get(j);
            }
            res = Math.max(res,temp);
        }

        return res;
    }
}
