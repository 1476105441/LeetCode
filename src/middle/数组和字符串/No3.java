package middle.数组和字符串;

import java.util.*;

public class No3 {
    //                      字母异位词分组
    //  给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    //字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。

    //----------------------------------------------------------------------------
    //                      欠缺思考了，有可能不同的字母组合会导致和相同
    /*public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add(strs[0]);
        res.add(list1);

        if (strs.length == 1) {
            return res;
        }

        //第一个存放字符串的每个字符之和，第二个存放在res中的下标
        Map<Integer,Integer> map = new HashMap<>();
        char[] chars1 = strs[0].toCharArray();
        int sum = 0,num = 0;
        boolean flag;
        for (int i = 0; i < chars1.length; i++) {
            sum += chars1[i];
        }
        map.put(sum,num++);

        for (int i = 1; i < strs.length; i++) {
            sum = 0;
            flag = false;
            char[] chars = strs[i].toCharArray();

            //计算字符串所有字母之和
            for (int j = 0; j < chars.length; j++) {
                sum += chars[j];
            }

            //遍历map函数
            Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
            for (Map.Entry<Integer,Integer> node: entrySet) {
                if (node.getKey() == sum){
                    res.get(node.getValue()).add(strs[i]);
                    flag = true;
                }
            }

            if (!flag) {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                res.add(list);
                map.put(sum,num++);
            }
        }

        return res;
    }*/
    //------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------
    //                      每个字符串对应一个哈希表
    //                      方向错误
    /*public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> res = new ArrayList<>();

        Map<Character,Integer>[] maps = new Map[strs.length];
        int[] nums = new int[strs.length];

        maps[0] = new HashMap<>();
        for (int i = 0; i < strs[0].length(); i++) {
            maps[0].put(strs[0].charAt(i),maps[0].getOrDefault(strs[0].charAt(i) ,0) + 1);
        }

        List<String> list1 = new ArrayList<>();
        list1.add(strs[0]);
        res.add(list1);
        nums[0] = 0;

        for (int i = 1; i < strs.length; i++) {

        }

        return res;
    }*/
    //---------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------
    //                  使用哈希表：成功版
    //之前的用法错误了，现在这样用：哈希表中的key是将每个字符串进行排序之后得到的字符串，value是List集合，存
    // 放符合条件的字符串。最后再将哈希表中的value取出来放到结果集合中
    /*public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> res = new ArrayList<>();

        Map<String,List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();

            Arrays.sort(chars);
            String s = new String(chars);
            List<String> list;

            if (i == 0 || (list = map.get(s)) == null) {
                list = new ArrayList<>();
                list.add(strs[i]);
                map.put(s,list);
            }else{
                list.add(strs[i]);
            }
        }

        //取出
        for (List<String> list : map.values()) {
            res.add(list);
        }

        return res;
    }*/
    //------------------------------------------------------------------------------


    //------------------------------------------------------------------------------
    //                      使用哈希表：另一种形式
    //使用记录每个字母出现次数的方式作为哈希表的key
    public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> res = new ArrayList<>();

        Map<String,List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();

            //用于记录26个字母的数组
            char[] chars1 = new char[26];

            for (int j = 0; j < chars.length; j++) {
                chars1[chars[j] - 'a']++;
            }

            String s = new String(chars1);
            List<String> list;

            if (i == 0 || (list = map.get(s)) == null) {
                list = new ArrayList<>();
                list.add(strs[i]);
                map.put(s,list);
            }else{
                list.add(strs[i]);
            }
        }

        //取出
        for (List<String> list : map.values()) {
            res.add(list);
        }

        return res;
    }
}
