package 复习.字符串;

public class No9 {
    /**
     *      最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串“”。
     *
     * 提示：
     *     1 <= strs.length <= 200
     *     0 <= strs[i].length <= 200
     *     strs[i] 仅由小写英文字母组成
     */

    //纵向查找
    /*public String longestCommonPrefix(String[] strs){
        char[][] chars = new char[strs.length][];
        int min = 201;

        for (int i = 0; i < strs.length; i++) {
            chars[i] = strs[i].toCharArray();
            if (min > chars[i].length) {
                min = chars[i].length;
            }
        }

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < min) {
            char c = chars[0][i];
            for (int j = 1; j < chars.length; j++) {
                if (chars[j][i] != c) {
                    return sb.toString();
                }
            }
            sb.append(c);
            i++;
        }

        return sb.toString();
    }*/

    //横向查找
    /*public String longestCommonPrefix(String[] strs){
        int min = 201;
        char[][] chars = new char[strs.length][];
        StringBuilder prefix = new StringBuilder();
        char[] point = null;

        for (int i = 0; i < strs.length; i++) {
            chars[i] = strs[i].toCharArray();
            if (min > chars[i].length) {
                min = chars[i].length;
                point = chars[i];
            }
        }

        if (min == 0) {
            return "";
        }
        prefix.append(point);

        for (int i = 0; i < strs.length; i++) {
            int length = prefix.length();
            for (int j = 0; j < length; j++) {
                if (prefix.charAt(j) != chars[i][j]) {
                    prefix.delete(j,length);
                    break;
                }
            }
        }

        return prefix.toString();
    }*/

    //升级版横向查找
    public String longestCommonPrefix(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = findCommonPrefix(prefix,strs[i]);
            if ("".equals(prefix)) {
                break;
            }
        }

        return prefix;
    }

    public String findCommonPrefix(String s1,String s2){
        char[] c1 = s1.toCharArray(),c2 = s2.toCharArray();
        int len = Math.min(c1.length,c2.length),loc = 0;

        for (; loc < len; loc++) {
            if (c1[loc] != c2[loc]) {
                break;
            }
        }

        return s1.substring(0,loc);
    }
}
