package daily.year2024.m5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * No.207 课程表
 *
 * @author wjs 2024/5/26
 */
public class d26 {
    // 错误，思路有问题
    // 拓扑排序，判断是否存在环形
    /*public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] count = new int[numCourses];
        int m = prerequisites.length;
        List<Integer>[] edges = new List[numCourses];
        for (int[] pre : prerequisites) {
            int x = pre[0], y = pre[1];
            // 入度加1
            count[x]++;
            if (edges[y] == null) {
                edges[y] = new ArrayList<>();
            }
            edges[y].add(x);
            if (x == y) {
                return false;
            }
        }
        boolean[] set = new boolean[numCourses];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i=0;i < count.length;i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }
        if (queue.isEmpty()) {
            return false;
        }
        while(!queue.isEmpty()) {
            int i = queue.poll();
            if (set[i]) {
                return false;
            }
            set[i] = true;
            List<Integer> edge = edges[i];
            if (edge != null) {
                for (int next : edge) {
                    count[next]--;
                    if (count[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        return true;
    }*/

    // 并不是思路错了，而是没有处理所有情况
    // 拓扑排序，判断是否存在环形
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] count = new int[numCourses];
        int m = prerequisites.length;
        List<Integer>[] edges = new List[numCourses];
        for (int[] pre : prerequisites) {
            int x = pre[0], y = pre[1];
            // 入度加1
            count[x]++;
            if (edges[y] == null) {
                edges[y] = new ArrayList<>();
            }
            edges[y].add(x);
            if (x == y) {
                return false;
            }
        }
        boolean[] set = new boolean[numCourses];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i=0;i < count.length;i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }
        // 没有入度为0的节点，肯定是出现闭环了
        if (queue.isEmpty()) {
            return false;
        }
        int c = 0;
        while(!queue.isEmpty()) {
            int i = queue.poll();
            c++;
            if (set[i]) {
                return false;
            }
            set[i] = true;
            List<Integer> edge = edges[i];
            if (edge != null) {
                for (int next : edge) {
                    count[next]--;
                    if (count[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        return c == numCourses;
    }
}
