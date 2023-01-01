package review.其他;

public class No10 {
    /**
     *      任务调度器
     */

    public static int leastInterval(char[] tasks, int n) {
        int l = tasks.length,max = 0,c = 0;
        int[] b = new int[26];
        for (int i = 0; i < l; i++) {
            int loc = tasks[i]-'A';
            b[loc]++;
            if (b[loc] > max) {
                max = b[loc];
                c = 1;
            }else if(b[loc] == max)
                c++;
        }

        int temp = (max-1) * (n+1)+c;
        if(l > temp)
            return l;
        return temp;
    }
}
