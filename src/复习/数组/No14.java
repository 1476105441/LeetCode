package 复习.数组;

import java.util.*;

public class No14 {
    /**
     *      字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     *
     * 提示：
     *     1 <= strs.length <= 104
     *     0 <= strs[i].length <= 100
     *     strs[i] 仅包含小写字母
     */

    //两种方法：
    // 1、每个字符串中的字母进行排序，再存入哈希表中
    // 2、统计每个字符串中字母出现的次数，以出现次数序列作为标识，存入哈希表中
    /*public List<List<String>> groupAnagrams(String[] strs){
        Map<String,List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            char[] nums = new char[26];
            for (int j = 0; j < chars.length; j++) {
                nums[chars[j]-'a']++;
            }
            String sb = new String(nums);
            List<String> list = map.get(sb);
            if (list == null) {
                list = new ArrayList<>();
                list.add(strs[i]);
                map.put(sb,list);
            }else{
                list.add(strs[i]);
            }
        }

        Iterator<Map.Entry<String,List<String>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            res.add(iterator.next().getValue());
        }

        return res;
    }*/

    public List<List<String>> groupAnagrams(String[] strs){
        Map<String,List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            //merge(chars,chars.length);
            Arrays.sort(chars);
            String s = new String(chars);
            List<String> list = map.get(s);
            if (list == null) {
                list = new ArrayList<>();
                list.add(strs[i]);
                map.put(s,list);
            }else{
                list.add(strs[i]);
            }
        }

        Iterator<Map.Entry<String,List<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            res.add(it.next().getValue());
        }

        return res;
    }

    //堆排序
    public void shift(char[] chars,int start,int end){
        int i = start,j = 2*i+1;
        char temp;
        while (j < end) {
            if (j < end - 1 && chars[j] < chars[j + 1]) {
                j++;
            }
            if (chars[i] < chars[j]) {
                temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }

    public void merge(char[] chars,int n){
        char temp;
        for (int i = (n-2)/2; i >= 0; i--) {
            shift(chars,i,n);
        }

        for (int i = n-1; i > 0; i--) {
            temp = chars[0];
            chars[0] = chars[i];
            chars[i] = temp;
            shift(chars,0,i);
        }
    }
}
