package junior.字符串;

public class No3 {
    //            字符串中的第一个唯一字符
    //  给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
    //
    //  提示：你可以假定该字符串只包含小写字母。

    /*
    //---------------------------------------------------------------------
    //100多ms，被吊大的死死地
    public static int firstUniqChar(String s) {
        //我的想法：双指针
        //状态数组，如果bp[i]是true的话，说明这个字符有重复的
        boolean[] bp = new boolean[s.length()];
        int point ;
        for (int i = 0; i < s.length(); i++) {
            if (bp[i]) {
                continue;
            }
            for (point = i+1; point < s.length();point++){
                if (bp[point]) {
                    continue;
                }
                if (s.charAt(i) == s.charAt(point)) {
                    bp[i] = true;
                    bp[point] = true;
                }
            }
            if(!bp[i]){
                return i;
            }
        }
        return -1;
    }
    //---------------------------------------------------------------------
    */

    /*
    //---------------------------------------------------------------------
    //两次遍历法，第一次记录该字符出现的次数，第二次遍历寻找出现一次的并返回
    //3ms，击败百分之98(最优解)
    public static int firstUniqChar(String s){
        //只有26个字母，所以数组只用26个就可以了
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            count[chars[i]-'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
    //---------------------------------------------------------------------
    */

    /*
    //---------------------------------------------------------------------
    //哈希表，与两次遍历类似
    //23ms，击败百分之60，虽然不是最优解，但也可以学习一下解题的思路
    public static int firstUniqChar(String s){
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //Map.getOrDefault()方法，从Map集合中获取value，如果集合中没有存入该key则使用defaultValue
            map.put(chars[i],map.getOrDefault(chars[i],0)+1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
    //---------------------------------------------------------------------
    */
}
