package contest.year2022.m8.m8d20;

public class No2 {
    /**
     *      二进制字符串重新安排顺序需要的时间
     */

    public int secondsToRemoveOccurrences(String s) {
        char[] c = s.toCharArray();
        int n = c.length,res = 0;
        boolean flag = true;
        while(flag){
            flag = false;
            for (int i = 0; i < n; i++) {
                if (c[i] == '0' && i < n-1 && c[i+1] == '1') {
                    c[i] = '1';
                    c[i+1] = '0';
                    i++;
                    flag = true;
                }
            }
            if(flag)
                res++;
        }
        return res;
    }
}
