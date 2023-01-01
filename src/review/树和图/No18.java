package review.树和图;

import java.util.*;

public class No18 {
    /**
     *      课程表II
     */

    //在第前一题课程表的基础上，记录节点的顺序
    /*List<List<Integer>> edge;
    int[] visited;
    boolean flag;
    int[] stack;
    int sp;
    public int[] findOrder(int numCourses,int[][] prerequisites){
        edge = new ArrayList<>(numCourses);
        visited = new int[numCourses];
        stack = new int[numCourses];
        flag = true;
        sp = numCourses-1;
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (int i = 0; i < numCourses && flag; i++) {
            dfs(i);
        }
        if (!flag) {
            return new int[0];
        }

        int[]res = new int[numCourses];
        int i = 0;
        sp = 0;
        while (sp != numCourses) {
            res[i++] = stack[sp];
            sp++;
        }
        return res;
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
        for (Integer j : list) {
            dfs(j);
            if (!flag) {
                return;
            }
        }

        stack[sp--] = i;
        visited[i] = 2;
    }*/

    /*public int[] findOrder(int numCourses,int[][] prerequisites){
        List<List<Integer>> edge = new ArrayList<>(numCourses);
        int[] count = new int[numCourses];
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

        List<Integer> resList = new ArrayList<>(numCourses);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            resList.add(0,node);
            List<Integer> list = edge.get(node);
            for (Integer i : list){
                count[i]--;
                if (count[i] == 0) {
                    queue.add(i);
                }
            }
        }

        if (resList.size() != numCourses) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }*/


    //深度优先遍历优化
    List<List<Integer>> edge;
    int[] visited;
    boolean flag;
    //使用数组模拟堆栈，提升速度
    int[] stack;
    int sp;
    public int[] findOrder(int numCourses,int[][] prerequisites){
        edge = new ArrayList<>(numCourses);
        visited = new int[numCourses];
        stack = new int[numCourses];
        flag = true;
        sp = numCourses-1;
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        //此处使用增强for循环，速度更快
        for (int[] info : prerequisites) {
            edge.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && flag; i++) {
            if(visited[i] == 0)
                dfs(i);
        }
        if (!flag) {
            return new int[0];
        }

        return stack;
    }
    public void dfs(int i){
        visited[i] = 1;

        List<Integer> list = edge.get(i);
        for (Integer j : list) {
            if (visited[j] == 0) {
                dfs(j);
                if (!flag) {
                    return;
                }
            } else if (visited[j] == 1) {
                flag = false;
                return;
            }
        }

        stack[sp--] = i;
        visited[i] = 2;
    }
}
