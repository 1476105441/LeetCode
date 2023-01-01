package contest.year2022.m7.m7d3;

public class No1 {
    /**
     *      解密消息
     */

    //使用哈希表和数组
    public static String decodeMessage(String key,String message){
        char[] map,set;
        set = new char[26];
        map = new char[126];
        char c = 'a';
        char[] keys = key.toCharArray();
        for (int i = 0; i < keys.length && c <= 'z'; i++) {
            if (keys[i] != ' ' && set[keys[i]-'a'] == 0) {
                map[keys[i]] = c;
                c++;
                set[keys[i]-'a'] = 1;
            }
        }

        char[] m = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length; i++) {
            if (m[i] == ' ') {
                sb.append(m[i]);
            }else
                sb.append(map[m[i]]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeMessage("eljuxhpwnyrdgtqkviszcfmabo", "zwx hnfx lqantp mnoeius ycgk vcnjrdb"));
    }
}
