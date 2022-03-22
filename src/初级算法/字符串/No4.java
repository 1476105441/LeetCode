package 初级算法.字符串;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class No4 {
    //               有效的字母异位词
    //  给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    //注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
    //
    //提示:
    //    1 <= s.length, t.length <= 5 * 104
    //    s 和 t 仅包含小写字母
    //
    //进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn96us/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //---------------------------------------------------------------------------------
    //又是查重题，哈希表
    //20多ms，被吊打
    /*
    public static boolean isAnagram(String s, String t) {
        //如果是不等长的两个字符串，肯定不符合条件
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            sMap.put(sChar[i],sMap.getOrDefault(sChar[i],0)+1);
            tMap.put(tChar[i],tMap.getOrDefault(tChar[i],0)+1);
        }
        //遍历一个map对比是否为字母异位词
        Set<Map.Entry<Character,Integer>> entry = sMap.entrySet();
        for (Map.Entry<Character,Integer> node:entry) {
            //对比Map.Entry和tMap的数量是否相同
            if (!node.getValue().equals(tMap.get(node.getKey()))) {
                return false;
            }
        }
        return true;
    }
    */
    //---------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //对数组先进行排序，再比较(3ms，仍然不是最优解)
    /*
    public static boolean isAnagram(String s, String t){
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        //对两个字符串中的字符进行排序
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        return Arrays.equals(sChar, tChar);
    }
    */
    //---------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //优化我的hash表算法，把hash表换成一个二维数组(2ms,相当不错了，击败百分之89)
    /*public static boolean isAnagram(String s, String t){
        //如果是不等长的两个字符串，肯定不符合条件
        if (s.length() != t.length()) {
            return false;
        }
        //用数组代替hash表，26个字母和2个字符串，0是s的，1是t的
        int[][] nums = new int[26][2];
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            nums[sChar[i] - 'a'][0]++;
            nums[tChar[i] - 'a'][1]++;
        }
        for (int i = 0; i < 26; i++) {
            if (nums[i][1] != nums[i][0]){
                return false;
            }
        }
        return true;
    }*/
    //---------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //比较像我优化后算法的再优化？但是我优化后的算法占用的内存却跟这个差不多，
    // 用一个数组存放26个字母出现的次数，在s中出现一次就加一，在t中出现一次就减一（时间2ms）
    /*
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] map = new int[26];
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            //出现了一个新的字符
            if (++map[cs[i] - 'a'] == 1) {
                count++;
            }
            //消失了一个新的字符
            if (--map[ct[i] - 'a'] == 0) {
                count--;
            }
        }
        return count == 0;
    }*/
    //---------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------
    public static boolean isAnagram(String s, String t) {
        //如果是不等长的两个字符串，肯定不符合条件
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character,Integer> map = new HashMap<>();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (map.containsKey(sChar[i])) {
                map.put(sChar[i],map.get(sChar[i])+1);
            }else{
                map.put(sChar[i],1);
            }
            if (map.containsKey(tChar[i])) {
                map.put(tChar[i],map.get(tChar[i])-1);
            }else{
                map.put(tChar[i],-1);
            }
        }
        Set<Map.Entry<Character,Integer>> set = map.entrySet();
        for (Map.Entry<Character,Integer> node : set){
            if (!node.getValue().equals(0)){
                return false;
            }
        }
        return true;
    }
}
