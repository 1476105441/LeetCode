package 复习.字符串;

public class No3 {
    /**
     *      字符串中的第一个唯一字符
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。、
     *
     * 提示:
     *     1 <= s.length <= 105
     *     s 只包含小写字母
     */

    public int firstUniqChar(String s){
        char[] chars = s.toCharArray();
        int[] value = new int[123];
        for (int i = 0; i < chars.length; i++) {
            value[chars[i]]++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (value[chars[i]] == 1) {
                return i;
            }
        }

        return -1;
    }
}
