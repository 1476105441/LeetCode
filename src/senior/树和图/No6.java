package senior.树和图;

import java.util.*;

public class No6 {
    /**
     *          课程表
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *     例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     * 提示：
     *     1 <= numCourses <= 105
     *     0 <= prerequisites.length <= 5000
     *     prerequisites[i].length == 2
     *     0 <= ai, bi < numCourses
     *     prerequisites[i] 中的所有课程对 互不相同
     */

    /**
     * 想法：建立图像模型，每个课程是一个点，所需要得先修课
     * 程是与之相连得点，若是在图中有一条回路，则说明无法完
     * 成当前所有课程的学习
     * 解法一： 建立图模型
     * 建立图模型，然后遍历寻找图中是否有回路，没有回路的话
     * 就代表可以完成的课表，有回路就是不能完成的课表
     */

    //7ms，速度略慢
    /*boolean flag;
    Map<Integer, List<Integer>> map;
    Set<Integer> isOk;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        flag = true;
        map = new HashMap<>();
        isOk = new HashSet<>();
        //建立图关系
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = map.get(prerequisites[i][0]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(prerequisites[i][0],list);
            }
            list.add(prerequisites[i][1]);
            isOk.add(prerequisites[i][0]);
        }

        //遍历查看图中是否有回路
        Set<Map.Entry<Integer,List<Integer>>> entry = map.entrySet();
        for(Map.Entry<Integer,List<Integer>> node : entry){
            if (isOk.contains(node.getKey())) {
                Set<Integer> set = new HashSet<>();
                set.add(node.getKey());
                dfs(set,node.getValue());
            }
            if (!flag) {
                break;
            }
        }

        return flag;
    }

    public void dfs(Set set,List<Integer> list){
        if (list == null || !flag) {
            return;
        }

        for (Integer i : list){
            if (isOk.contains(i)) {
                if (set.contains(i)) {
                    flag = false;
                    return;
                }
                set.add(i);
                List<Integer> list1 = map.get(i);
                dfs(set,list1);
                set.remove(i);
                isOk.remove(i);
            }
            if (!flag) {
                break;
            }
        }
    }*/

    //在上一题基础上做一些调整，提升了2ms，总耗时5ms
    /*boolean flag;
    List<List<Integer>> edge;
    Set<Integer> isOk;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        flag = true;
        edge = new ArrayList<>(numCourses);
        isOk = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        //建立图关系
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = edge.get(prerequisites[i][0]);
            list.add(prerequisites[i][1]);
            isOk.add(prerequisites[i][0]);
        }

        //遍历查看图中是否有回路
        for (int i = 0; i < edge.size(); i++) {
            if (isOk.contains(i)) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                dfs(set,edge.get(i));
            }
            if (!flag) {
                break;
            }
        }

        return flag;
    }

    public void dfs(Set set,List<Integer> list){
        if (list == null || !flag) {
            return;
        }

        for (Integer i : list){
            if (isOk.contains(i)) {
                if (set.contains(i)) {
                    flag = false;
                    return;
                }
                set.add(i);
                List<Integer> list1 = edge.get(i);
                dfs(set,list1);
                set.remove(i);
                isOk.remove(i);
            }
            if (!flag) {
                break;
            }
        }
    }*/

    //使用拓扑排序，其实和我的想法差不多，使用数组代替哈希表是提升速度的关键
    /*boolean res;
    List<List<Integer>> edge;
    int[] visited;
    public boolean canFinish(int numCourses,int[][] prerequisites){
        res = true;
        edge = new ArrayList<>();
        visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for (int i = 0; i < numCourses && res; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return res;
    }

    public void dfs(int i){
        //先标记为正在搜索状态
        visited[i] = 1;

        List<Integer> list = edge.get(i);
        int n = list.size();
        for (int j = 0; j < n && res; j++) {
            int temp = list.get(j);
            if(visited[temp] == 0){
                dfs(temp);
            } else if (visited[temp] == 1) {
                res = false;
                return;
            }
        }

        visited[i] = 2;
    }*/



    /**
     * 解法二：使用广度优先遍历
     * 让入度为0的点先入队列，然后出队列一个元素，放入
     * 结果集中，并且对这个元素的相邻边，也就是能进入这
     * 个元素的点的入度减一，如果入度变成了零，就加入队
     * 列之中，如此，直到队列为空，即没有入度为零的元素
     * 为止，这时再判断
     */
    boolean res;
    int[] count;//存放入度
    List<List<Integer>> point;//存放当前点的入点，也就是能够进入当前点的其他点
    public boolean canFinish(int numCourses,int[][] prerequisites){
        res = true;
        count = new int[numCourses];
        point = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            point.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            count[prerequisites[i][0]]++;
            point.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        //将入度为零的点入队列
        for (int i = 0; i < numCourses; i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            resList.add(poll);
            for(Integer i : point.get(poll)){
                count[i]--;
                if (count[i] == 0) {
                    queue.add(i);
                }
            }
        }

        if (resList.size() != numCourses) {
            res = false;
        }

        return res;
    }
}
