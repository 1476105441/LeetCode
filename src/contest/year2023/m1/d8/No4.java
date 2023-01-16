package contest.year2023.m1.d8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class No4 {
    //使用堆维护：
    // 1、左边空闲的员工
    // 2、左边卸货的员工
    // 3、右边空闲的员工
    // 4、右边装货的员工
    int[][] time;
    public int findCrossingTime(int n, int k, int[][] time) {
        int num = time.length;
        this.time = time;
        PriorityQueue<int[]> lq  = new PriorityQueue<>((x,y) -> x[0] - y[0]),rq = new PriorityQueue<>((x,y) -> x[0] - y[0]);
        int[] left = new int[num],right = new int[num];
        int l = 0,r = 0; //分别维护几个最小堆的结构
        for (int i = 0; i < num; i++) {
            left[l] = i;
            update(left,l);
            l++;
        }
        int nowTime = 0,lc = l;
        while (n > 0) {
            //检查当前的两个等待队列
            int[] head;
            boolean flag = lc == 0;
            while (!rq.isEmpty() && ((head = rq.peek())[0] <= nowTime || flag)) {
                if (flag) {
                    flag = false;
                    nowTime = head[0];
                }
                rq.poll();
                right[r] = head[1];
                update(right,r);
                r++;
            }
            flag = r == 0;
            while (!lq.isEmpty() && ((head = lq.peek())[0] <= nowTime || flag)) {
                if (flag) {
                    flag = false;
                    nowTime = head[0];
                }
                lq.poll();
                left[l] = head[1];
                update(left,l);
                l++;
            }
            //先检查右边有没有空闲的，优先过河
            if (r > 0) {
                int loc = right[0];
                right[0] = right[r - 1];
                r--;
                lc++;
                shift(right, 0, r);
                lq.offer(new int[]{nowTime + time[loc][2] + time[loc][3], loc});
                //跳到桥的下一个空闲时间
                nowTime += time[loc][2];
            } else {
                //每到右边一个，就将数量减一
                n--;
                //移除左边的堆顶元素
                int loc = left[0];
                left[0] = left[l-1];
                l--;
                lc--;
                shift(left,0,l);
                rq.offer(new int[]{nowTime + time[loc][0] + time[loc][1], loc});
                //跳到桥的下一个空闲时间
                nowTime += time[loc][0];
            }
        }
        //处理剩余数据
        while (r > 0 || !rq.isEmpty()) {
            int[] head;
            while (!rq.isEmpty() && ((head = rq.peek())[0] <= nowTime || r == 0)) {
                if (r == 0) {
                    nowTime = head[0];
                }
                rq.poll();
                right[r] = head[1];
                update(right,r);
                r++;
            }
            if (r > 0) {
                int loc = right[0];
                right[0] = right[r - 1];
                r--;
                shift(right, 0, r);
                lq.add(new int[]{nowTime + time[loc][2] + time[loc][3], loc});
                //跳到桥的下一个空闲时间
                nowTime += time[loc][2];
            }
        }
        return nowTime;
    }

    public static void main(String[] args) {
        No4 no4 = new No4();
        System.out.println(no4.findCrossingTime(1, 3, new int[][]{new int[]{1, 1, 2, 3}, new int[]{1, 1, 3, 1}, new int[]{1, 1, 4, 1}}));
    }

    //判断工人i是否效率高于工人j
    private boolean check(int i,int j){
        int sum1 = time[i][0] + time[i][2],sum2 = time[j][0] + time[j][2];
        return sum1 < sum2 || (sum1 == sum2 && i < j);
    }

    //最小堆的调整函数
    private void shift(int[] locs,int start,int n){
        int i = start,j = (i << 1) + 1;
        while (j < n) {
            if (j + 1 < n && check(locs[j], locs[j + 1])) {
                j++;
            }
            if (check(i,j)) {
                int temp = locs[i];
                locs[i] = locs[j];
                locs[j] = temp;
                i = j;
                j = (i << 1) + 1;
            } else {
                break;
            }
        }
    }

    //最小堆的更新函数
    private void update(int[] locs,int start){
        int i = start,p = (i-1) >> 1;
        while (i > 0) {
            if (check(locs[p],locs[i])) {
                int temp = locs[p];
                locs[p] = locs[i];
                locs[i] = temp;
                i = p;
                p = (i-1) >> 1;
            } else {
                break;
            }
        }
    }
}
