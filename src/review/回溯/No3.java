package review.回溯;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    /**
     *      电话号码的字母组合
     */

    char[][] chars;
    List<String> res;
    public List<String> letterCombinations(String digits){
        if (digits == "") {
            return new ArrayList<>();
        }
        chars = new char[][]{{},{},{'a','b','c'},{'d','e','f'},
                {'g','h','i'},{'j','k','l'},{'m','n','o'},
                {'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        res = new ArrayList<>();
        char[] ch = digits.toCharArray();
        int[] nums = new int[ch.length];
        for (int i = 0; i < ch.length; i++) {
            nums[i] = ch[i] - '0';
        }
        find(nums,new StringBuilder(),0);

        return res;
    }
    public void find(int[] nums,StringBuilder sb,int loc){
        if (loc == nums.length) {
            res.add(sb.toString());
            return;
        }

        char[] v = chars[nums[loc]];
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
            find(nums,sb,loc+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
