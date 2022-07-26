package 周赛.m7d24;

public class No1 {
    /**
     *      第一个出现两次的字母
     */

    public char repeatedCharacter(String s) {
        char[] c = s.toCharArray();
        int[] count = new int[26];

        for (int i = 0; i < c.length; i++) {
            if(count[c[i]-'a'] != 0)
                return c[i];
            count[c[i]-'a']++;
        }

        return ' ';
    }
}
