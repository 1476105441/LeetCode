package senior.数组和字符串;

public class No11 {
    /**
     *          最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 注意：
     *     对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     *     如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 提示：
     *     1 <= s.length, t.length <= 105
     *     s 和 t 由英文字母组成
     *
     * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
     */

    /**
     * 想法：
     *   使用StringBuilder存放字符串t，遍历s，使用消除的方法
     * 将StringBuilder中的字符消除，若StringBuilder长度为0，
     * 则找到一个字串，记录这个子串的长度，直到找到最小的字串
     */

    /**
     * 失败，思考的不全面
     */
    /*public String minWindow(String s, String t){
        StringBuilder sb = new StringBuilder(t);
        char[] chars = s.toCharArray();
        int start = -1,end = -1,r1 = -1,r2 = -1;

        for (int i = 0; i < chars.length; i++) {
            int loc = sb.indexOf(chars[i]+"");
            if(loc != -1){
                sb.deleteCharAt(loc);
                if (start == -1) {
                    start = i;
                }
                if (sb.isEmpty()) {
                    end = i;
                    if (r1 - r2 > start - end || r1 == -1) {
                        r1 = start;
                        r2 = end;
                    }
                    start = -1;
                    sb.append(t);
                }
            }
        }

        return s.substring(r1,r2+1);
    }*/
    /*public String minWindow(String s, String t) {
        int[] count = new int[60],compare = new int[60];
        char[] chars = s.toCharArray(), tChar = t.toCharArray();
        int total = tChar.length, left = 0, right = 0, min = -1, start = -1, end = -1;

        for (int i = 0; i < tChar.length; i++) {
            int loc = tChar[i] - 'A';
            count[loc]++;
            compare[loc]++;
        }

        while (right < chars.length) {
            if (total > 0) {
                int loc = chars[right] - 'A';
                if (count[loc] > 0) {
                    total--;
                }
                count[loc]--;
                right++;
            }
            while(total == 0){
                int loc = chars[left] - 'A';
                //左边界要移除的字符是结果中的字符
                if (compare[loc] > 0 && count[loc] == 0) {
                    if (min == -1 || right - left < min) {
                        start = left;
                        end = right;
                        min = right - left;
                    }
                    total++;
                }
                count[chars[left] - 'A']++;
                left++;
            }
        }

        if(start == -1){
            return "";
        }
        return s.substring(start,end);
    }*/
}
