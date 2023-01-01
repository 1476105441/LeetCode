package contest.year2022.m11.d20;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    //t3
    //思路是拓扑排序+贪心，从叶子节点开始，遍历判断能否走到相邻的父节点上，能走到父节点上就尽量走到父节点上
    //但没有考虑到不仅可以走到相邻父节点上，还可以走到上边的父节点，所以这个解法应该是不成功的
    /*List<List<Integer>> edge; //存储边信息
    int[] dis; //计算每个节点和0点的距离
    boolean[] set; //记忆化搜索使用
    public long minimumFuelCost(int[][] roads, int seats) {
        long res = 0;
        int n = roads.length + 1;
        int[] count = new int[n]; //存储每个节点的度，度为1的节点就是叶子节点
        dis = new int[n];
        long[] dp = new long[n]; //存储每个节点有多少个乘客
        set = new boolean[n]; //记忆化使用
        edge = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
            dp[i] = 1;
        }
        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = roads[i][0],y = roads[i][1];
            count[x]++;
            count[y]++;
            edge.get(x).add(y);
            edge.get(y).add(x);
        }
        //先dfs计算出所有节点到根节点的距离
        dfs(0,0);
        //注意不能把0点加入进来
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n; i++) {
            if(count[i] == 1)
                queue.add(i);
        }
        while (!queue.isEmpty()) {
            Integer loc = queue.poll();
            boolean flag = false;
            set[loc] = true;
            for (Integer next : edge.get(loc)) {
                if (!set[next] && next != 0) {
                    if (seats-dp[next] >= dp[loc]) {
                        res++;
                        dp[next] += dp[loc];
                        flag = true;
                    }
                    if(--count[next] == 1)
                        queue.add(next);
                }
            }
            //没清空，要发车
            if (!flag) {
                res += dis[loc];
            }
        }
        return res;
    }
    public void dfs(int loc,int distance){
        dis[loc] = distance;
        set[loc] = true;

        for (Integer next : edge.get(loc)) {
            if(!set[next])
                dfs(next,distance+1);
        }

        set[loc] = false;
    }*/


    //注意，题目的意思可不是每个点只能发一辆车
    //想象成每个节点开始出发时都是乘坐一辆车的，到了父节点之后就可以合并到同一辆车上（车没坐满的话）
    List<List<Integer>> edges;
    long res;
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length+1;
        edges = new ArrayList<>(n);
        res = 0;
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            int x = roads[i][0],y = roads[i][1];
            edges.get(x).add(y);
            edges.get(y).add(x);
        }
        dfs(0,-1,seats);
        return res;
    }
    //返回值是返回当前子树的节点数
    public int dfs(int loc,int pre,int seats){
        int size = 1;

        for (int next : edges.get(loc)) {
            if (next != pre) {
                size += dfs(next,loc,seats);
            }
        }
        if (loc > 0) {
            res += (size + seats-1) / seats; //结果要向上取整
        }

        return size;
    }
    //感觉这个题目考的更多的是思维的转变，可以想象成从每个叶子节点出发，叶子节点的人开着一辆
    //车前往父节点，然后先停留片刻，等待父节点的所有叶子节点中的其他人都到达父节点之后，重新
    //整顿车队，尽量将每辆车都坐满人，单独开车的乘客可以坐到别的还有空位的车上，这样就可以节
    //省油量。
    //为什么这种思路可行？因为你的车一开到底计算出来的值和你一个城市一个城市的走计算出来的值
    //是一样的，其实直接开走也是相当于经过了这些城市
}
