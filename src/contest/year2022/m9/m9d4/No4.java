package contest.year2022.m9.m9d4;

import java.util.Arrays;

public class No4 {
    /**
     *
     */


    /*public int mostBooked(int n, int[][] meetings) {
        //使用最小堆，排序规则：当结束时间相等时，序号小的在前面，结束时间不相等时，时间短的在前面
        TreeSet<int[]> treeSet = new TreeSet<>((x,y) ->{
            if (x[1] == y[1]) {
                return x[0]-y[0];
            }
            return x[1] - y[1];
        });
        int[] count = new int[n];

        //别忘了给二维数组排序
        Arrays.sort(meetings,(x,y)->{
            return x[0]-y[0];
        });

        for (int i = 0; i < meetings.length; i++) {

        }
        int res = 0;



        return res;
    }*/


    //不知道问题出在哪，有两个用例过不了
    //找到问题，原来是记录每个会议室的截止时间溢出了，使用long数组解决
    /*public int mostBooked(int n, int[][] meetings){
        //deadline存储会议室结束时间
        int res = 0,c = 0;
        long[] deadline = new long[n]; //记录每个会议室的截止时间，使用long类型，不然会溢出
        int[] count = new int[n]; //记录每个会议室的使用次数
        Arrays.sort(meetings,(x,y)->{
            return x[0] - y[0];
        });

        for (int i = 0; i < meetings.length; i++) {
            int j, loc = -1;
            long min = Long.MAX_VALUE;
            for (j = 0; j < n; j++) {
                //记录当前会议室中截止时间最小的，由于是从小往大遍历，所
                //以当截止时间相等时，会议号较小的会议室一定会先被记录
                if (min > deadline[j]) {
                    loc = j;
                    min = deadline[j];
                }
                //判断当前会议室的截止时间是否早于会议的开始时间，如果早
                //于，则使用当前会议室举行当前遍历到的会议
                if (deadline[j] <= meetings[i][0]) {
                    deadline[j] = meetings[i][1];
                    count[j]++;
                    break;
                }
            }
            //j == n 说明所有的会议室当前（会议开始时间）都有会议在举办，则
            if (j == n) {
                int span = meetings[i][1] - meetings[i][0];
                deadline[loc] += span;
                count[loc]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (c < count[i]) {
                res = i;
                c = count[i];
            }
        }

        return res;
    }*/
    public static void main(String[] args) {
        No4 no4 = new No4();
        no4.mostBooked(5,new int[][]{{40,47},{7,16},{27,38},{16,43},{38,40},{2,25}});
    }

    //使用双堆优化一下，两个最小堆，一个记录当前空闲的会议室号，另一个记录会议室的空闲时间和会议号
    public int mostBooked(int n, int[][] meetings){
        long[][] stack2 = new long[n][2];
        int l1 = n,l2 = 0,res = 0,c = 0;
        int[] count = new int[n],stack1 = new int[n];
        for (int i = 0; i < n; i++) {
            stack1[i] = i;
        }

        Arrays.sort(meetings,(x,y)->{return x[0]-y[0];});

        for (int i = 0; i < meetings.length; i++) {
            //先把结束时间小于当前会议开始时间的放到堆1中
            if (l2 > 0) {
                while (l2 > 0 && stack2[0][0] <= meetings[i][0]) {
                    stack1[l1] = (int) stack2[0][1];
                    update(stack1,l1++);
                    stack2[0][0] = stack2[l2-1][0];
                    stack2[0][1] = stack2[l2-1][1];
                    l2--;
                    shift2(stack2,0,l2);
                }
            }
            //如果堆1没空，直接取出来
            if (l1 > 0) {
                stack2[l2][1] = stack1[0];
                count[stack1[0]]++;
                stack2[l2][0] = meetings[i][1];
                update2(stack2,l2++);
                stack1[0] = stack1[l1-1];
                l1--;
                shift(stack1,0,l1);
            }else{
                stack2[0][0] += (meetings[i][1] - meetings[i][0]);
                count[(int) stack2[0][1]]++;
                shift2(stack2,0,l2);
            }
        }

        for (int i = 0; i < n; i++) {

            if (c < count[i]) {
                c = count[i];
                res = i;
            }
        }

        return res;
    }

    //空闲会议号的最小堆
    public void shift(int[] stack,int s,int n){
        int i = s,j = (i << 1)+1;
        while (j < n) {
            if(j < n-1 && stack[j]> stack[j+1])
                j++;
            if (stack[i] > stack[j]) {
                int temp = stack[i];
                stack[i] = stack[j];
                stack[j] = temp;
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public void update(int[] stack,int s){
        int i = s,p = (i-1) >> 1;
        while (i > 0) {
            if (stack[p] > stack[i]) {
                int temp = stack[i];
                stack[i] = stack[p];
                stack[p] = temp;
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }

    //******************存储会议室空闲时间的最小堆*****************************
    //判断loc1元素是否比loc2元素大
    public boolean examine(long[][] stack,int loc1,int loc2){
        if(stack[loc1][0] > stack[loc2][0])
            return true;
        else if (stack[loc1][0] == stack[loc2][0]) {
            return stack[loc1][1] > stack[loc2][1];
        }else
            return false;
    }
    public void shift2(long[][] stack,int s,int n){
        int i = s,j = (i << 1)+1;
        while(j < n){
            if(j < n-1 && examine(stack,j,j+1))
                j++;
            if (examine(stack,i,j)) {
                long[] temp = stack[i];
                stack[i] = stack[j];
                stack[j] = temp;
                i = j;
                j = (i << 1)+1;
            }else
                break;
        }
    }
    public void update2(long[][] stack,int s){
        int i = s,p = (i-1)>>1;
        while (i > 0) {
            if (examine(stack, p, i)) {
                long[] temp = stack[i];
                stack[i] = stack[p];
                stack[p] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }
}
