package contest.year2023.m3.d12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No4 {
    //失败
    /*public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        List<int[]> list = new ArrayList<>(n);
        list.add(tasks[0]);
        for(int i = 1;i < n;i++){
            int[] l = tasks[i];
            List<int[]> tmp = new ArrayList<>();
            for(int[] t : list){
                int tl = t[0],tr = t[1],tv = t[2];
                int s,e;
                //两个集合有交集
                if(tl >= l[0] && tl <= l[1]){
                    s = tl;
                    e = Math.min(tr,l[1]);
                    //取交集中元素的个数和集合中应该有的元素个数最小值
                    int c = Math.min(e-s+1,tv);
                    t[0] = s;
                    t[1] = e;
                    t[2] = c;
                    //如果还有剩下的元素，增加一个集合
                    if(c < tv){
                        tmp.add(new int[]{e+1,tr,tv-c});
                    }
                    //当前集合需要的个数
                    l[2] -= c;
                    //l[1] = s-1;
                } else if(l[0] >= tl && l[0] <= tr){
                    s = l[0];
                    e = Math.min(tr,l[1]);
                    int c = Math.min(e-s+1,tv);
                    t[0] = s;
                    t[1] = e;
                    t[2] = c;
                    if(c < tv){
                        tmp.add(new int[]{s,e-1,tv-c});
                    }
                    l[2] -= c;
                    //l[0] = e+1;
                }
                if(l[2] <= 0){
                    break;
                }
            }
            list.addAll(tmp);
            if(l[2] > 0){
                list.add(l);
            }
        }

        int res = 0;
        for(int[] t : list){
            //System.out.println(t[0] + " " + t[1]+" "+t[2]);
            res += t[2];
        }
        return res;
    }*/

    //解法一：贪心+暴力，19ms
    /*public int findMinimumTime(int[][] tasks) {
        int n = tasks.length,res = 0;
        Arrays.sort(tasks,(x,y) -> x[1] - y[1]);
        boolean[] run = new boolean[tasks[n-1][1]+1];
        for(int i = 0;i < n;i++){
            int start = tasks[i][0],end = tasks[i][1],d = tasks[i][2];
            for(int j = start;j <= end;j++){
                if(run[j]) d--;//如果区间中这部分已经存在了，让时间减少
            }
            for(int j = end;d > 0 && j >= start;j--){
                if(!run[j]){
                    run[j] = true;
                    d--;
                }
            }
        }
        for(int i = 0;i < run.length;i++){
            if(run[i]) res++;
        }
        return res;
    }*/

    //解法二：线段树（数组版），11ms
    /*public int findMinimumTime(int[][] tasks){
        int n = tasks.length;
        Arrays.sort(tasks, (x, y) -> x[1] - y[1]);
        int max = tasks[n-1][1];//最大时间
        cnt = new int[max * 4];//为什么是乘以4？
        add = new boolean[max * 4];
        for (int i = 0; i < n; i++) {
            int s = tasks[i][0],e = tasks[i][1],v = tasks[i][2];
            suffix = v - query(1,1,max,s,e);
            if (suffix > 0) {
                update(1,1,max,s,e);
            }
        }
        return cnt[1];
    }
    private int[] cnt;
    private boolean[] add;
    private int suffix;
    private void pushDown(int node,int l,int c,int r){
        if (add[node]) {
            action(node * 2, l, c);
            action(node * 2 + 1, c + 1, r);
            add[node] = false;
        }
    }
    private void action(int node,int l,int r){
        cnt[node] = r-l+1;
        add[node] = true;
    }
    private void update(int node,int l,int r,int s,int e){
        int size = r-l+1;
        //如果当前节点全部被使用了，直接返回
        if (cnt[node] == size) {
            return;
        }
        if (s <= l && r <= e && size - cnt[node] <= suffix) {
            suffix -= size - cnt[node];
            cnt[node] = size;
            add[node] = true;
            return;
        }
        int c = l + ((r - l) >> 1); //这里之前写错了，一直报错
        pushDown(node,l,c,r);
        //先修改右节点
        if(c < e){
            update(node * 2 + 1, c + 1, r, s, e);
        }
        //如果还需要修改，进入左节点修改
        if(suffix > 0 && c >= s){
            update(node * 2, l, c, s, e);
        }
        cnt[node] = cnt[node * 2] + cnt[node * 2 + 1];
    }
    private int query(int node,int l,int r,int s,int e){
        if (s <= l && r <= e) {
            return cnt[node];
        }
        int c = l + ((r - l) >> 1),res = 0;
        pushDown(node,l,c,r);
        if (c >= s) {
            res += query(node * 2, l, c, s, e);
        }
        if (c < e) {
            res += query(node * 2 + 1, c + 1, r, s, e);
        }
        return res;
    }*/

    //解法二：线段树重写版本，成功，11ms
    //线段树中存储区间中运行的时间，先查询当前区间中运行的时间
    //如果查询出来的时间小于需要的时间，则更新线段树（优先更新
    //右节点，贪心的思想）
    /*public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks,(x,y) -> x[1]-y[1]);
        int max = tasks[n-1][1];
        cnt = new int[max*4];
        add = new boolean[max*4];
        for (int i = 0; i < n; i++) {
            int s = tasks[i][0],e = tasks[i][1],v = tasks[i][2];
            sum = v - query(1,1,max,s,e);
            if(sum > 0) update(1,1,max,s,e);
        }
        return cnt[1];
    }

    private int[] cnt;//存放区间运行时间的个数
    private boolean[] add;//是否更新的标记
    private int sum;//记录当前还需要增加多少运行时间

    private void pushDown(int node,int l,int c,int r){
        if (add[node]) {
            count(node*2,l,c);
            count(node*2+1,c+1,r);
            add[node] = false;
        }
    }
    private void count(int node,int l,int r){
        cnt[node] = r-l+1;
        add[node] = true;
    }
    private void update(int node,int l,int r,int s,int e){
        int size = r - l + 1;
        if(cnt[node] == size) return;
        if (s <= l && r <= e && size - cnt[node] <= sum) {
            sum -= size - cnt[node];//减少需要的运行时间
            cnt[node] = size;
            add[node] = true;
            return;
        }
        int c = l + ((r-l) >> 1);
        pushDown(node,l,c,r);
        //优先更新右节点
        if (c < e) {
            update(node*2+1,c+1,r,s,e);
        }
        if (sum > 0 && c >= s) {
            update(node*2,l,c,s,e);
        }
        //更新当前节点的运行时间
        cnt[node] = cnt[node*2] + cnt[node*2+1];
    }
    private int query(int node,int l,int r,int s,int e){
        if (s <= l && r <= e) {
            return cnt[node];
        }
        int c = l + ((r-l) >> 1),res = 0;
        pushDown(node,l,c,r);
        if (c >= s) {
            res += query(node*2,l,c,s,e);
        }
        if (c < e) {
            res += query(node*2+1,c+1,r,s,e);
        }
        return res;
    }*/

    //解法三：单调栈思想：先排序，再使用栈存储运行的区间
    public int findMinimumTime(int[][] tasks) {
        int n = tasks.length,res = 0;
        Arrays.sort(tasks,(x,y) -> x[1]-y[1]);//仍然是按照右端点进行排序
        List<int[]> stack = new ArrayList<>();//存储三元组：左端点、右端点、累计值
        stack.add(new int[]{-2,-2,0});//添加特殊端点，方便处理
        for(int i = 0;i < n;i++){
            int start = tasks[i][0],end = tasks[i][1],val = tasks[i][2];
            int[] tmp = findIndex(stack,start);//找到左端点第一个小于等于start的区间
            val -= stack.get(stack.size()-1)[2] - tmp[2];
            if(start <= tmp[1]){
                val -= tmp[1]-start+1;
            }
            //不需要新增区间，跳过当前循环
            if(val <= 0) continue;
            //合并相交的集合
            while(end - stack.get(stack.size()-1)[1] <= val){
                tmp = stack.remove(stack.size()-1);
                val += tmp[1] - tmp[0] + 1;
            }
            stack.add(new int[]{end-val+1,end,stack.get(stack.size()-1)[2]+val});
        }
        return stack.get(stack.size()-1)[2];
    }
    //二分查找，找到第一个左端点小于等于target的元素
    private int[] findIndex(List<int[]> stack,int target){
        int l = 0,r = stack.size()-1,res = 0;
        while(l <= r){
            int c = l + ((r-l) >> 1);
            if(stack.get(c)[0] > target){
                r = c-1;
            } else {
                res = c;
                l = c+1;
            }
        }
        return stack.get(res);
    }





































}
