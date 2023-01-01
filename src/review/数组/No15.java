package review.数组;

public class No15 {
    /**
     *      无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 提示：
     *     0 <= s.length <= 5 * 104
     *     s 由英文字母、数字、符号和空格组成
     */

    //滑动窗口解决此问题，维护两个指针
    /*public int lengthOfLongestSubstring(String s){
        int low=0,high=0,max=0,p,value;

        char[] chars = s.toCharArray();

        if (chars.length != 0) {
            max = 1;
        }
        for (int i = 1; i < chars.length; i++) {
            p = low;
            while (p <= high) {
                if (chars[p] == chars[i]) {
                    low = p+1;
                }
                p++;
            }
            high = i;
            value = high-low+1;
            if (value > max) {
                max = value;
            }
        }

        return max;
    }*/

    //最快版本的滑动窗口，思路是遍历到当前元
    //素时，直接接着上一个元素的长度，然后从
    //这个序列的起始位置开始遍历，查看是否有
    //重复元素有重复元素则从重复元素下一个元
    //素开始计算长度，就是以当前元素为结尾的最长无重复元素序列
    public int lengthOfLongestSubstring(String s){
        char[] chars = s.toCharArray();
        int p,max = 0;
        int[] dp = new int[chars.length];
        if (chars.length != 0) {
            max = 1;
            dp[0] = 1;
        }

        for (int i = 1; i < chars.length; i++) {
            dp[i] = dp[i-1] + 1;
            p = i - dp[i] + 1;
            while (p < i) {
                if (chars[p] == chars[i]) {
                    dp[i] = i - p;
                    break;
                }
                p++;
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
