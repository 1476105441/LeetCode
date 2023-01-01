package middle.回溯算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No1 {
    //                  电话号码的字母组合
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    //--------------------------------------------------------------------------------
    //                      成功：5ms，非常慢
    //想法：哈希表加回溯
    /*Map<Character,String> map;
    {
        map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
    }

    List<String> res;
    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();

        recursion(0,digits.toCharArray(),"");

        return res;
    }

    //一个指针o指向原字符数组（数字的）当前下标，另一个指针r指向哈希表取出来的对应字符数组的下标
    public void recursion(int o,char[] oChar,String s){

        if (o > oChar.length - 1) {
            return;
        }

        char[] rChar;

        rChar = map.get(oChar[o]).toCharArray();


        for (int i = 0; i < rChar.length; i++) {
            char ch = rChar[i];
            String temp = s + ch;
            recursion(o + 1,oChar,temp);
            if (o == oChar.length - 1) {
                res.add(temp);
            }
        }
    }*/
    //--------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------
    //                  改用StringBuilder，时间没变化，空间节省了
    /*Map<Character,String> map;
    {
        map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
    }

    List<String> res;
    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();

        recursion(0,digits.toCharArray(),"");

        return res;
    }

    //一个指针o指向原字符数组（数字的）当前下标，另一个指针r指向哈希表取出来的对应字符数组的下标
    public void recursion(int o,char[] oChar,String s){

        if (o > oChar.length - 1) {
            return;
        }

        char[] rChar;

        rChar = map.get(oChar[o]).toCharArray();


        for (int i = 0; i < rChar.length; i++) {
            char ch = rChar[i];
            StringBuilder temp = new StringBuilder(s + ch);
            recursion(o + 1,oChar,temp.toString());

            if (o == oChar.length - 1) {
                res.add(temp.toString());
            }
        }
    }*/
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //                  0ms，最优解
    //使用StringBuilder，每次添加完，将当前的字符删除掉
    Map<Character,String> map;
    {
        map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
    }

    List<String> res;
    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();

        recursion(0,digits.toCharArray(),new StringBuilder());

        return res;
    }

    //一个指针o指向原字符数组（数字的）当前下标，另一个指针r指向哈希表取出来的对应字符数组的下标
    public void recursion(int o,char[] oChar,StringBuilder s){

        if (o > oChar.length - 1) {
            return;
        }

        char[] rChar;

        rChar = map.get(oChar[o]).toCharArray();


        for (int i = 0; i < rChar.length; i++) {
            char ch = rChar[i];
            s.append(ch);
            recursion(o + 1,oChar,s);
            if (o == oChar.length - 1) {
                res.add(s.toString());
            }

            //将当前的字符删除掉
            s.deleteCharAt(o);
        }
    }
}
