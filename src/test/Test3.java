package test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试
 *
 * @author wjs 2023/10/16
 */
public class Test3 {
    public static int climbStairs(int n) {
        int pre1 = 1,pre2 = 2;
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        for(int i=3;i <= n;i++){
            int cnt = pre1+pre2;
            pre1 = pre2;
            pre2 = cnt;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.size();
        return pre2;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(1));
//        ConcurrentHashMap
    }
}
