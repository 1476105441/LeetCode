package 初级算法.字符串;

public class No7 {
    //                实现 strStr()
    //  实现 strStr() 函数。
    //给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
    //
    //说明：
    //  **当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    //对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
    //
    //提示：
    //    0 <= haystack.length, needle.length <= 5 * 104
    //    haystack 和 needle 仅由小写英文字符组成
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnr003/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //包含子字符串问题？kmp算法？是的没错，应该是最优解吧，3ms，题解里好像没有更优解了
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        char[] hayChars = haystack.toCharArray(),neChars = needle.toCharArray();
        //先计算next数组
        int[] next =new int[neChars.length];
        computerNext(next,neChars);
        int i = 0 , j = 0;
        while (i < hayChars.length && j< neChars.length) {
            if (hayChars[i] != neChars[j]) {
                j = next[j];
                if(j == -1){
                    i++;
                    j++;
                }
                continue;
            }
            i++;
            j++;
        }
        if (j == neChars.length) {
            return i - neChars.length;
        }else
            return -1;
    }

    //计算next数组
    public static void computerNext(int[] next,char[] chars){
        next[0] = -1;
        for (int i = 1; i < next.length; i++) {
            int k = next[i-1];
            while (k != -1) {
                if (chars[k] == chars[i-1]){
                    next[i] = k + 1;
                    break;
                }else{
                    k = next[k];
                }
                if(k == -1) {
                    next[i] = 0;
                }
            }
        }
    }

}
