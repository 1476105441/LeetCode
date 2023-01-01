package review.树和图;

import java.util.*;

public class No17 {
    /**
     *      课程表
     */

    //建立图模型
    //6ms，效率非常之低
    /*Map<Integer,List<Integer>> edge;
    Map<Integer,Boolean> map;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        edge = new HashMap<>(numCourses);
        map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = edge.get(prerequisites[i][1]);
            if (list == null) {
                list = new ArrayList<>();
                edge.put(prerequisites[i][1],list);
                map.put(prerequisites[i][1],false);
            }
            list.add(prerequisites[i][0]);
            if (map.get(prerequisites[i][0]) == null) {
                map.put(prerequisites[i][0],false);
            }
        }

        Set<Integer> keys = edge.keySet();
        for (Integer i : keys){
            if(!find(i)){
                return false;
            }
        }
        return true;
    }

    public boolean find(int i){
        //当前节点没有边，所以是叶子节点
        if (edge.get(i) == null) {
            return true;
        }
        //get为ture，说明已经遍历过了
        if (map.get(i)) {
            return false;
        }

        map.put(i,true);
        List<Integer> list = edge.get(i);

        for(Integer j : list){
            if (!find(j)) {
                return false;
            }
        }

        edge.put(i,null);
        return true;
    }*/

    //改进一番，不使用hashMap，提升至4ms
    /*List<List<Integer>> edge;
    Set<Integer> set;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        edge = new ArrayList<>();
        set = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for (int i = 0;i < edge.size();i++){
            if(edge.get(i) != null && !edge.get(i).isEmpty() && !find(i)){
                return false;
            }
        }
        return true;
    }

    public boolean find(int i){
        if (edge.get(i) == null) {
            return true;
        }
        //get为ture，说明已经遍历过了
        if (set.contains(i)) {

            return false;
        }

        set.add(i);
        List<Integer> list = edge.get(i);

        for(Integer j : list){
            if (edge.get(j) != null &&!edge.get(j).isEmpty() && !find(j)) {
                return false;
            }
        }

        edge.set(i,null);
        return true;
    }*/

    //提升到3ms
    /*List<List<Integer>> edge;
    int[] set;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        edge = new ArrayList<>();
        set = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for (int i = 0;i < edge.size();i++){
            if(edge.get(i) != null && !edge.get(i).isEmpty() && !find(i)){
                return false;
            }
        }
        return true;
    }

    public boolean find(int i){
        if (edge.get(i) == null) {
            return true;
        }
        //get为ture，说明已经遍历过了
        if (set[i] == 1) {
            return false;
        }

        set[i] = 1;
        List<Integer> list = edge.get(i);

        for(Integer j : list){
            if (edge.get(j) != null &&!edge.get(j).isEmpty() && !find(j)) {
                return false;
            }
        }

        edge.set(i,null);
        return true;
    }*/

    //建立拓扑排序模型
    //拓扑排序就是在有向图中按照有向边进行排序
    //总是有这样的规律：有向边u->v中，v始终在
    //u之前
    /*List<List<Integer>> edge;
    int[] visited;
    boolean flag;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        edge = new ArrayList<>(numCourses);
        visited = new int[numCourses];
        flag = true;
        //首先建立图模型
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses && flag; i++) {
            dfs(i);
        }
        return flag;
    }

    public void dfs(int i){
        if (visited[i] == 1) {
            flag = false;
            return;
        } else if (visited[i] == 2) {
            return;
        }

        visited[i] = 1;
        List<Integer> list = edge.get(i);

        for (Integer j : list){
            dfs(j);
            if (!flag) {
                break;
            }
        }
        visited[i] = 2;
    }*/

    //使用广度优先遍历
    /*List<List<Integer>> edge;
    //用于统计出度的数组，如果出度为0，则在拓扑排序中可以先出来
    int[] count;
    public boolean canFinish(int numCourses,int[][] prerequisites) {
        edge = new ArrayList<>(numCourses);
        count = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][0]).add(prerequisites[i][1]);
            count[prerequisites[i][1]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            Integer i = queue.poll();
            List<Integer> list = edge.get(i);
            for (Integer j : list){
                count[j]--;
                if (count[j] == 0) {
                    queue.add(j);
                }
            }
        }

        return res == numCourses;
    }*/


    //再写一遍广度优先遍历
    List<List<Integer>> edge;
    int[] count;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        edge = new ArrayList<>(numCourses);
        count = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][0]).add(prerequisites[i][1]);
            count[prerequisites[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            res++;
            List<Integer> list = edge.get(node);
            for(Integer i : list){
                count[i]--;
                if (count[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return res == numCourses;
    }
}
