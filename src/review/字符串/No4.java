package review.字符串;

public class No4 {
    /**
     *      有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     *
     * 提示:
     *     1 <= s.length, t.length <= 5 * 104
     *     s 和 t 仅包含小写字母
     *
     * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     */

    public boolean isAnagram(String s,String t){
        char[] sChar = s.toCharArray(),tChar = t.toCharArray();
        if (sChar.length != tChar.length) {
            return false;
        }
        int[] v1 = new int[123],v2 = new int[123];
        for (int i = 0; i < sChar.length; i++) {
            v1[sChar[i]]++;
            v2[tChar[i]]++;
        }

        for (int i = 97; i < 123; i++) {
            if (v1[i] != v2[i]) {
                return false;
            }
        }

        return true;
    }
}
