package review.字符串;

public class No7 {
    /**
     *      实现strStr()
     * 实现 strStr() 函数。
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     *
     * 说明：
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     *
     * 提示：
     *     1 <= haystack.length, needle.length <= 104
     *     haystack 和 needle 仅由小写英文字符组成
     */

    //字符串匹配问题，循序渐进，使用kmp算法，动态规划计算出next数组
    /*public int strStr(String haystack, String needle){
        if("".equals(needle)){
            return 0;
        }
        char[] chars1 = haystack.toCharArray(),chars2 = needle.toCharArray();
        int[] next = new int[chars2.length];

        //计算next数组
        next[0] = -1;
        for (int i = 1; i < next.length; i++) {
            int k = next[i-1];
            while (k != -1) {
                if (chars2[k] == chars2[i - 1]) {
                    next[i] = k+1;
                    break;
                }else{
                    k = next[k];
                }
            }
            if (k == -1) {
                next[i] = 0;
            }
        }

        int i = 0,j = 0;
        while (i < chars1.length && j < chars2.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            }else{
                j = next[j];
            }
            if (j == -1) {
                i++;
                j++;
            }
        }

        if (j == chars2.length) {
            return i-chars2.length;
        }
        return -1;
    }*/

    //kmp算法竟然忘了怎么写了，重新写一遍
    public int strStr(String haystack, String needle){
        char[] hc = haystack.toCharArray(),nc = needle.toCharArray();
        int n = nc.length,m = hc.length,k = -1;

        //计算next数组，也就是匹配失败后回溯到哪里
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 1; i < n; i++) {
            while (k != -1 && nc[k] != nc[i-1]) {
                k = next[k];
            }
            if (k == -1) {
                next[i] = 0;
                k = 0;
            }else
                next[i] = ++k;
        }

        int i = 0,j = 0;
        while (i < m && j < n) {
            if (hc[i] == nc[j]) {
                i++;
                j++;
            }else if(next[j] == -1)
                i++;
            else
                j = next[j];
        }
        if(j == n)
            return i - n;
        return -1;
    }
}
