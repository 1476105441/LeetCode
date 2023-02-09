package algorithm.dp;

public class No32 {
    //最长有效括号

    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length,res = 0;
        int l = 0,c = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                l++;
            } else {
                if (l == 0) {
                    res = Math.max(res, c);
                    c = 0;
                } else {
                    c++;
                    l--;
                }
            }
        }
        res = Math.max(res,c);
        return res << 1;
    }
}
