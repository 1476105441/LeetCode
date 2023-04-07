package contest.year2023.m3.d26;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class No4 {
    //在一个节点上，我需要知道当前能够收集哪些节点的金币
    //我还需要知道哪些节点上还有金币待收集
    //从哪个节点开始？开始了之后怎么走？

    //上面的思路从一开始就错了，思路错了自然解不出题目
    //层序遍历，失败写法
    /*int[] count,vals;
    List<List<Integer>> list;
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        count = new int[n];
        vals = new int[n];
        list = new ArrayList<>();
        for(int i=0;i < n;i++) list.add(new ArrayList<>());
        for(int i=0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            list.get(x).add(y);
            list.get(y).add(x);
            count[x]++;
            count[y]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i < n;i++){
            if(count[i] == 0) queue.add(i);
        }
        //第一步，去掉无用的叶子节点
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int loc = queue.poll();
                if(coins[loc] == 0){
                    for(Integer next : list.get(loc)){
                        count[next]--;
                        if(count[next] == 0) queue.add(next);
                    }
                }
                size--;
            }
        }
        //第二步，记录每个节点的权值
        for(int i=0;i < n;i++){
            if(count[i] == 0) queue.add(i);
        }
        int v = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int loc = queue.poll();
                vals[loc] = v;
                for(Integer next : list.get(loc)){
                    count[next]--;
                    if(count[next] == 0) queue.add(next);
                }
                size--;
            }
            v++;
        }
        int res = 0;
        //第三步，遍历边
        for(int i=0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            if(vals[x] >=2 && vals[y] >= 2) res += 2;
        }
        return res;
    }*/

    //成功，60ms
    //想要解开一个题目，就要找到题目的突破口
    //这道题目的突破口是从叶子节点开始，我们使用层序
    //遍历去掉没有金币的叶子节点，为什么？
    /*int[] count,vals;
    List<List<Integer>> list;
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        count = new int[n];
        vals = new int[n];
        list = new ArrayList<>();
        for(int i=0;i < n;i++) list.add(new ArrayList<>());
        for(int i=0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            list.get(x).add(y);
            list.get(y).add(x);
            count[x]++;
            count[y]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i < n;i++){
            if(count[i] == 1) queue.add(i);
        }
        //第一步，去掉无用的叶子节点
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int loc = queue.poll();
                if(coins[loc] == 0){
                    count[loc] = 0;//确保第二次遍历去掉当前叶子节点
                    for(Integer next : list.get(loc)){
                        count[next]--;
                        if(count[next] == 1) queue.add(next);
                    }
                }
                size--;
            }
        }
        //第二步，记录每个节点的权值
        for(int i=0;i < n;i++){
            if(count[i] == 1) queue.add(i);
        }
        int v = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int loc = queue.poll();
                vals[loc] = v;
                for(Integer next : list.get(loc)){
                    count[next]--;
                    if(count[next] == 1) queue.add(next);
                }
                size--;
            }
            v++;
        }
        int res = 0;
        //第三步，遍历边
        for(int i=0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            if(vals[x] >=2 && vals[y] >= 2) res += 2;
        }
        return res;
    }*/

    //逻辑改进版本，40ms
    //改进的思路是：只需要遍历没有金币的叶子节
    //点，更新距离其最近的非叶子金币节点的入度
    //为1，并且后续再筛选有金币的叶子节点出来即可
    int[] count,vals;
    List<Integer>[] list;
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        count = new int[n];
        vals = new int[n];
        list = new List[n];
        for(int i=0;i < n;i++) list[i] = new ArrayList<>();
        for(int i=0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            list[x].add(y);
            list[y].add(x);
            count[x]++;
            count[y]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i < n;i++){
            if(count[i] == 1 && coins[i] == 0) queue.add(i);
        }
        //遍历没有金币的叶子节点，更新叶子节点集合，原叶子节点不需要去掉
        while(!queue.isEmpty()){
            int loc = queue.poll();
            for(Integer next : list[loc]){
                count[next]--;
                if(count[next] == 1 && coins[next] == 0) queue.add(next);
            }
        }
        //第二步，记录每个节点的权值
        for(int i=0;i < n;i++){
            if(count[i] == 1 && coins[i] == 1) queue.add(i);
        }
        if(queue.size() <= 1) return 0;
        while(!queue.isEmpty()){
            int loc = queue.poll();
            for(Integer next : list[loc]){
                count[next]--;
                if(count[next] == 1) {
                    queue.add(next);
                    vals[next] = vals[loc]+1;
                }
            }
        }
        int res = 0;
        //第三步，遍历边
        for(int i=0;i < edges.length;i++){
            int x = edges[i][0],y = edges[i][1];
            if(vals[x] >=2 && vals[y] >= 2) res += 2;
        }
        return res;
    }
}
