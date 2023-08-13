package contest.year2023.m5.d21;

/**
 * 2698.求一个整数的惩罚数
 *
 * @author wjs 2023/8/13
 */
public class No3 {
    //暴力解法：190ms，效率有点差
    /*public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1;i <= n;i++) {
            int t = i * i;
            if (check(String.valueOf(t), 0, i)) {
                res += t;
            }
        }
        return res;
    }
    private boolean check(String s, int cnt, int val) {
        if(s == null || "".equals(s)) {
            return cnt == val;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < n;i++) {
            sb.append(chars[i]);
            int tmp = Integer.parseInt(sb.toString());
            if(tmp > val) {
                break;
            }
            if(cnt + tmp > val) {
                break;
            }
            if (check(s.substring(i + 1), cnt+tmp, val)) {
                return true;
            }
        }
        return false;
    }*/

    //可以使用预处理来优化，4ms
    static int[] totalCnt = new int[1001];

    static {
        int cnt = 0;
        for (int i=1;i <= 1000;i++) {
            int t = i*i;
            if(check(String.valueOf(t),0,i)) {
                cnt += t;
            }
            totalCnt[i] = cnt;
        }
    }

    private static boolean check(String s, int cnt, int val) {
        if(s == null || "".equals(s)) {
            return cnt == val;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < n;i++) {
            sb.append(chars[i]);
            int tmp = Integer.parseInt(sb.toString());
            if(tmp > val) {
                break;
            }
            if(cnt + tmp > val) {
                break;
            }
            if (check(s.substring(i + 1), cnt+tmp, val)) {
                return true;
            }
        }
        return false;
    }

    public int punishmentNumber(int n) {
        return totalCnt[n];
    }
}
