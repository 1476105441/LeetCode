package 中级算法.数组和字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No4 {
    //                  无重复字符的最长子串
    //给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

    //------------------------------------------------------------------------------------
    //                  50ms，太慢

    //  太慢的原因是：每次有重复的元素时就要将哈希表中的元素进行清空，然后再从出现重复的地方进
    //行插入哈希表，此过程消耗大量时间

    //试试动态规划能不能解决，但是好像不满足最优解原理？
    //应该是不能用动态规划吧
    //使用哈希表 + 双指针，哈希表中存储的是慢指针所指的元素最长子序列中的各个元素和对应的下标值
    //记录下标值是因为可以直接找到当前是哪两个元素重复了，直接让慢指针指到重复元素后面一位即可
    /*public int lengthOfLongestSubstring(String s){
        int max = 0;

        Map<Character,Integer> map = new HashMap<>();

        int slow,quick;
        char[] chars = s.toCharArray();
        Integer integer;

        for (slow = 0,quick = 0;quick < chars.length && slow < chars.length;quick++){
            //说明有重复元素了
            if ((integer = map.get(chars[quick])) != null) {
                //先记录是否是最大值
                if (max < map.size()) {
                    max = map.size();
                }

                //让slow指向重复元素的下一个
                slow = integer + 1;

                map.clear();
                for (int i = slow; i <= quick; i++) {
                    map.put(chars[i],i);
                }
            }else{
                map.put(chars[quick],quick);
            }
        }

        if(max < map.size()) {
            max = map.size();
        }

        return max;
    }*/
    //--------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------
    //优化优化？
    //                      滑动窗口
    /*public int lengthOfLongestSubstring(String s){
        int max = 0;

        Set<Character> set = new HashSet<>();

        int slow,quick;
        char[] chars = s.toCharArray();

        for (slow = 0,quick = 0;quick < chars.length && slow < chars.length;){
            //说明有重复元素了
            if (set.contains(chars[quick])) {
                //先记录是否是最大值
                if (max < set.size()) {
                    max = set.size();
                }
                //让slow下一个
                set.remove(chars[slow]);

                slow++;
            }else{
                set.add(chars[quick]);
                quick++;
            }
        }

        if(max < set.size()) {
            max = set.size();
        }

        return max;
    }*/
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    //                     最快版本的滑动窗口
    //此题还有一个最快的方法，就是类似动态规划的方法，这个方法是解决了我一开始没想到的问题

    //  其实此方法更加快的原因是不需要再对哈希表进行操作，因为哈希表中查找很快，但是删除元素
    //的操作却没有那么快，而此方法不需要将每个元素进行进行删除操作，只需要比较就可以了

    public int lengthOfLongestSubstring(String s){
        char[] chars = s.toCharArray();

        int res = 0;
        if (chars.length <= 1) {
            return chars.length;
        }

        //使用数组存储以i结尾的无重复最长子串长度
        int[] dp = new int[chars.length];
        dp[0] = 1;

        for (int i = 1; i < chars.length; i++) {
            dp[i] = dp[i-1] + 1;

            //这个长度要算上i这个元素，所以要加1
            int length = i - dp[i] + 1;
            while (length < i) {
                //在长度范围内，如果i之前的元素有重复的，则截断前面部分，更新最长长度
                if (chars[length] == chars[i]) {
                    dp[i] = i - length;//不包含length点的元素，所以不需要加一
                    break;
                }
                length++;
            }

            if (res < dp[i]) {
                res = dp[i];
            }
        }

        return res;
    }
}
