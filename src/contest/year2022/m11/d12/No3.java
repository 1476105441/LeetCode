package contest.year2022.m11.d12;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    //首先，需要先建图
    //要先找到b的路线轨迹

    //150多ms，才击败了百分之10几
    /*public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        List<List<Integer>> e;
        int res = Integer.MIN_VALUE,n = amount.length;
        int[] temp = new int[n],set = new int[n];
        e = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            temp[i] = -1;
            e.add(new ArrayList<>());
        }
        //首先，建立图模型
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0],y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
        }
        //bfs找出bob的轨迹
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(bob);
        set[bob] = 1;
        boolean flag = true;
        while (!queue.isEmpty() && flag) {
            int size = queue.size();
            while (size > 0 && flag) {
                Integer i = queue.poll();
                for (Integer j : e.get(i)) {
                    if (set[j] == 0) {
                        temp[j] = i;
                        queue.add(j);
                        set[j] = 1;
                        if (j == 0) {
                            flag = false;
                            break;
                        }
                    }
                }
                size--;
            }
        }

        //回溯存储bob遍历的节点
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i != -1; ) {
            b.add(0,i);
            i = temp[i];
        }

        set = new int[n];//用于后续记忆化搜索，直接新建一个数组
        int[] set2 = new int[n];//用于标记bob走过的点
        int c = 1;
        set2[bob] = 1;
        set[0] = 1;
        Queue<int[]> queue1 = new ArrayDeque<>();
        if(bob == 0)
            queue1.add(new int[]{0,amount[0]/2});
        else
            queue1.add(new int[]{0,amount[0]});

        while (!queue1.isEmpty()) {
            int size = queue1.size();
            if(c < b.size())
                set2[b.get(c)] = 1;
            while (size > 0) {
                int[] node = queue1.poll();
                boolean f = true;
                for (Integer j : e.get(node[0])) {
                    if (set[j] == 0) {
                        f = false;
                        int[] now = new int[]{j,node[1]};
                        if(set2[j] == 0) {
                            now[1] = node[1]+amount[j];
                        } else if(c < b.size() && b.get(c).equals(j)) {
                            now[1] = node[1]+amount[j]/2;
                        }
                        queue1.add(now);
                        set[j] = 1;
                    }
                }
                if (f) {
                    res = Math.max(res,node[1]);
                }
                size--;
            }
            c++;
        }

        return res;
    }*/


    //使用数组两次bfs，直接优化到了47ms
    /*public static int mostProfitablePath(int[][] edges, int bob, int[] amount){
        int res = Integer.MIN_VALUE,n = amount.length;

        //1、构建图模型
        List<List<Integer>> e = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
        }
        //2、找到bob到0点的路径，并存储bob每一步应该走到哪个节点
        int c = 0;
        boolean flag = false;
        //set数组用于记忆化，temp数组用于存储到达当前结点的前驱结点
        int[] set = new int[n],temp = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(bob);
        while (!queue.isEmpty() && !flag) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int loc = queue.poll();
                if (loc == 0) {
                    flag = true;
                    break;
                }
                //广度优先遍历，添加当前节点的邻接点
                for (Integer j : e.get(loc)) {
                    if (set[j] == 0) {
                        //记录下当前结点的前驱结点
                        temp[j] = loc;
                        queue.add(j);
                        set[j] = 1;
                    }
                }
            }
            c++;
        }
        queue = null;
        //注意，我这里的计数c在bob已经走到0点时还是加了1，所以要减去一次
        c--;

        //action用于记录每一步时bob所在的节点
        int[] action = new int[c];
        for (int i = c-1,j = 0; i > -1; i--) {
            action[i] = temp[j];
            //走到前一个节点上
            j = temp[j];
        }

        //3、开始bfs走Alice的路线，计算到叶子节点的最大净得分
        Queue<int[]> queue1 = new ArrayDeque<>();
        queue1.add(new int[]{0,0});
        //重新记忆化
        set = new int[n];
        //又漏了这一步
        set[0] = 1;
        //a用于记录Alice当前走到哪一步了
        int a = 0;
        while (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue1.poll();
                //如果bob也刚好到当前节点，则花费减半
                if (a < c && action[a] == node[0]) {
                    node[1] += amount[action[a]] / 2;
                }else
                    node[1] += amount[node[0]];
                //flag判断当前节点是否有邻接节点可以遍历，如果没有，说明是叶子节点
                flag = false;
                for (Integer j : e.get(node[0])) {
                    if (set[j] == 0) {
                        set[j] = 1;
                        flag = true;
                        queue1.add(new int[]{j,node[1]});
                    }
                }
                if (!flag) {
                    res = Math.max(res,node[1]);
                }
            }
            //bob已经走过当前的节点了，把他在这一层的节点值清空
            if(a < c)
                amount[action[a]] = 0;
            a++;
        }

        return res;
    }*/


    //使用dfs，41ms
    /*List<List<Integer>> e;
    boolean[] set;
    int[] action,amount;
    int res;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount){
        int n = amount.length;
        res = Integer.MIN_VALUE;
        e = new ArrayList<>();
        set = new boolean[n];
        this.amount = amount;
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
        }
        bobDfs(bob,0);
        dfs(0,0,0);
        return res;
    }
    public boolean bobDfs(int loc,int c){
        if (loc == 0) {
            action = new int[c];
            return true;
        }
        if(set[loc])
            return false;

        set[loc] = true;
        boolean flag = false;
        for (Integer next : e.get(loc)) {
            if (bobDfs(next, c + 1)) {
                flag = true;
                action[c] = loc;
                break;
            }
        }
        //dfs完成之后再把标记消除掉，一遍后续Alice遍历时使用
        set[loc] = false;
        return flag;
    }
    public void dfs(int loc,int c,int val){
        set[loc] = true;
        //增加上当前节点的val
        if(c < action.length && loc == action[c])
            val += amount[loc] / 2;
        else
            val += amount[loc];
        int t = 0;
        if (c < action.length) {
            t = amount[action[c]];
            amount[action[c]] = 0;
        }
        boolean flag = false;
        for (Integer next : e.get(loc)) {
            if (!set[next]) {
                flag = true;
                dfs(next, c + 1, val);
            }
        }
        if(!flag)
            res = Math.max(res,val);
        if (c < action.length)
            amount[action[c]] = t;
    }*/


    //将存放边的集合换成数组，没有任何提升，说明瓶颈不在这里
    /*List<Integer>[] e;
    boolean[] set;
    int[] action,amount;
    int res;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount){
        int n = amount.length;
        res = Integer.MIN_VALUE;
        e = new List[n];
        set = new boolean[n];
        this.amount = amount;
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            e[x].add(y);
            e[y].add(x);
        }
        bobDfs(bob,0);
        dfs(0,0,0);
        return res;
    }
    public boolean bobDfs(int loc,int c){
        if (loc == 0) {
            action = new int[c];
            return true;
        }
        if(set[loc])
            return false;

        set[loc] = true;
        boolean flag = false;
        for (Integer next : e[loc]) {
            if (bobDfs(next, c + 1)) {
                flag = true;
                action[c] = loc;
                break;
            }
        }
        //dfs完成之后再把标记消除掉，一遍后续Alice遍历时使用
        set[loc] = false;
        return flag;
    }
    public void dfs(int loc,int c,int val){
        set[loc] = true;
        //增加上当前节点的val
        if(c < action.length && loc == action[c])
            val += amount[loc] / 2;
        else
            val += amount[loc];
        int t = 0;
        if (c < action.length) {
            t = amount[action[c]];
            amount[action[c]] = 0;
        }
        boolean flag = false;
        for (Integer next : e[loc]) {
            if (!set[next]) {
                flag = true;
                dfs(next, c + 1, val);
            }
        }
        if(!flag)
            res = Math.max(res,val);
        if (c < action.length)
            amount[action[c]] = t;
    }*/


    //再换一种思路，记录下bob经过每个节点的时间，当Alice遍历
    //时，判断当前时间和当前节点就可以判断增加节点值的规则
    List<List<Integer>> e;
    int[] bobTime,amount;
    boolean[] set;
    int res;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount){
        res = Integer.MIN_VALUE;
        int n = amount.length;
        e = new ArrayList<>();
        this.amount = amount;
        bobTime = new int[n];
        set = new boolean[n];

        for (int i = 0; i < n; i++) {
            bobTime[i] = n;
            e.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
        }
        dfsB(bob,0);

        dfsA(0,0,0);

        return res;
    }
    public boolean dfsB(int loc,int time){
        if (loc == 0) {
            return true;
        }
        set[loc] = true;
        for (Integer i : e.get(loc)) {
            if (!set[i]) {
                if (dfsB(i,time+1)) {
                    set[loc] = false;
                    bobTime[loc] = time;
                    return true;
                }
            }
        }
        set[loc] = false;
        return false;
    }
    public void dfsA(int loc,int time,int val){
        //更新当前节点的值
        if (time == bobTime[loc]) {
            val += amount[loc] / 2;
        } else if (time < bobTime[loc]) {
            val += amount[loc];
        }
        //叶子节点要对比res，哪个更大
        boolean flag = true;
        set[loc] = true;
        for (Integer i : e.get(loc)) {
            if (!set[i]) {
                flag = false;
                dfsA(i,time+1,val);
            }
        }
        if(flag) res = Math.max(res,val);
        set[loc] = false;
    }
}
