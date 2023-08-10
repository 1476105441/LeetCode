package contest.year2023.m5.d21;

/**
 * 2697.字典序最小回文串
 *
 * @author wjs 2023/8/10
 */
public class No2 {
    public String makeSmallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int l = 0, r = n-1;
        while(l <= r) {
            if(chars[l] != chars[r]) {
                if(chars[l] > chars[r]) {
                    chars[l] = chars[r];
                } else {
                    chars[r] = chars[l];
                }
            }
            l++;
            r--;
        }
        return new String(chars);
    }
}
