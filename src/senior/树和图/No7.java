package senior.树和图;

public class No7 {
    /**
     *          课程表 II
     * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     *     例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     *
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     *
     * 提示：
     *     1 <= numCourses <= 2000
     *     0 <= prerequisites.length <= numCourses * (numCourses - 1)
     *     prerequisites[i].length == 2
     *     0 <= ai, bi < numCourses
     *     ai != bi
     *     所有[ai, bi] 互不相同
     */

    //课程表的复杂版本，需要记录每个课程的执行顺序
    /*
    List<Integer> res;
    List<List<Integer>> edge;
    int[] visited;
    boolean flag;
    public int[] findOrder(int numCourses,int[][] prerequisites){
        flag = true;
        res = new ArrayList<>();
        edge = new ArrayList<>();
        visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for (int i = 0; i < numCourses && flag; i++) {
            dfs(i);
        }

        if (!flag) {
            return new int[0];
        }
        int n = res.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    public void dfs(int i){
        if (visited[i] == 2 || !flag) {
            return;
        }
        visited[i] = 1;

        List<Integer> list = edge.get(i);
        int n = list.size();
        for (int j = 0; j < n && flag; j++) {
            int temp = list.get(j);
            if (visited[temp] == 1) {
                flag = false;
                return;
            }
            dfs(temp);
        }

        res.add(i);
        visited[i] = 2;
    }*/

    //解法二：拓扑排序
    /*public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] count = new int[numCourses],res = new int[numCourses];
        List<List<Integer>> edge = new ArrayList<>();

        for(int i = 0;i < numCourses;i++){
            edge.add(new ArrayList<>());
        }

        for(int i = 0;i < prerequisites.length;i++){
            edge.get(prerequisites[i][1]).add(prerequisites[i][0]);
            count[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int l = 0;
        for(int i = 0;i < numCourses;i++){
            if(count[i] == 0){
                res[l++] = i;
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            Integer x = queue.poll();
            for(Integer y : edge.get(x)){
                if(--count[y] == 0){
                    res[l++] = y;
                    queue.offer(y);
                }
            }
        }

        if(l < numCourses){
            return new int[]{};
        }

        return res;
    }*/
}
