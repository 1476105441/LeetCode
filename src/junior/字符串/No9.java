package junior.字符串;

public class No9 {
    //                   最长公共前缀
    //  编写一个函数来查找字符串数组中的最长公共前缀。
    //如果不存在公共前缀，返回空字符串 ""。
    //
    //提示：
    //    1 <= strs.length <= 200
    //    0 <= strs[i].length <= 200
    //    strs[i] 仅由小写英文字母组成
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //------------------------------------------------------------------------
    //暴力解法，击败百分之73，空间效率倒是挺高的
    /*
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        //nowPoint是遍历每个字符串用的
        int nowPoint = -1;

        boolean tag = true;

        char res;

        while (tag) {
            nowPoint++;

            if (nowPoint < strs[0].length()) {
                res = strs[0].charAt(nowPoint);
            }else{
                break;
            }

            for (int i = 1; i < strs.length; i++) {
                //nowPoint超过了当前字符串的长度
                if (nowPoint >= strs[i].length()) {
                    tag = false;
                    break;
                }
                if (strs[i].charAt(nowPoint) != res) {
                    tag =false;
                }
            }

        }
        if (nowPoint == 0) {
            return "";
        }
        return strs[0].substring(0,nowPoint);
    }*/
    //-------------------------------------------------------------------

    //大神的解法，0ms，击败百分之百
    public String longestCommonPrefix(String[] strs){
        //边界条件判断
        if (strs == null || strs.length == 0)
            return "";
        //默认第一个字符串是他们的公共前缀
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            //不断的截取，pre不是公共前缀就截取
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }
}
