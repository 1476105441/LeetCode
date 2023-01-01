package contest.year2022.m12.d10;

public class No1 {
    public int maximumValue(String[] strs) {
        int res = 0,n = strs.length;
        char[][] chars = new char[n][];
        for (int i = 0; i < n; i++) {
            chars[i] = strs[i].toCharArray();
            boolean flag = true;
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] < '0' || chars[i][j] > '9') {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res = Math.max(res,Integer.parseInt(strs[i]));
            } else
                res = Math.max(res,chars[i].length);
        }
        return res;
    }
}
