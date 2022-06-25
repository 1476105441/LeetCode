package 复习.数组;

import java.util.HashSet;
import java.util.Set;

public class No28 {
    /**
     *      最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 注意：
     *     对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     *     如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 提示：
     *     1 <= s.length, t.length <= 105
     *     s 和 t 由英文字母组成
     *
     * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
     */

    //滑动窗口，写的有点复杂
    /*public String minWindow(String s, String t){
        Set<Integer> set = new HashSet<>();
        int[] value = new int[58];
        boolean[] need = new boolean[58];

        char[] sChar = s.toCharArray(),tChar = t.toCharArray();
        int count = tChar.length,start=-1,end=-1;
        for (int i = 0; i < tChar.length; i++) {
            int loc = tChar[i]-'A';
            value[loc]++;
            need[loc] = true;
        }

        int left = -1;
        for (int i = 0; i < sChar.length && left < sChar.length; i++) {
            int loc = sChar[i]-'A';

            if (value[loc] > 0) {
                set.add(i);
                count--;
                value[loc]--;
                if (left == -1) {
                    left = i;
                }
            } else if (need[loc]) {
                set.add(i);
                value[loc]--;
            }
            if ((value[loc] < 0&&sChar[i] == sChar[left]) || count == 0) {
                //当前满足条件
                loc = sChar[left]-'A';
                if (count == 0) {
                    if (start == -1) {//第一次满足条件
                        start = left;
                        end = i;
                    } else if (i - left + 1 < end - start + 1) {
                        start = left;
                        end = i;
                    }
                    count++;
                }
                if (need[loc]) {
                    value[loc]++;
                }
                set.remove(left++);
                if (left < sChar.length) {
                    loc = sChar[left]-'A';
                }
                while (left < sChar.length && (!set.contains(left) || value[loc] < 0)) {
                    if (value[loc] < 0) {
                        set.remove(left);
                        value[loc]++;
                    }
                    left++;
                    if (left < sChar.length) {
                        loc = sChar[left]-'A';
                    }
                }
            }
        }

        if (start == -1) {
            return "";
        }
        return s.substring(start,end+1);
    }*/

    //先前的解法中还有缺陷，可以不使用哈希表来维护，速度
    //提升了一截，2ms，但还不是最快，这是因为我的算法还
    //要维护左边界的情况，判断消耗了时间
    //较多的判断和boolean类型数组都会影响时间
    public String minWindow(String s,String t){
        int[] value = new int[58],need = new int[58];

        char[] sChar = s.toCharArray(),tChar = t.toCharArray();
        int count = tChar.length,start=-1,end=-1,min=-1;
        for (int i = 0; i < tChar.length; i++) {
            int loc = tChar[i]-'A';
            value[loc]++;
            need[loc]++;
        }

        int left = -1;
        for (int i = 0; i < sChar.length && left < sChar.length; i++) {
            int loc = sChar[i]-'A';

            if (value[loc] > 0) {
                count--;
                if (left == -1) {
                    left = i;
                }
            }
            value[loc]--;
            if ((need[loc] > 0&&value[loc] < 0&&sChar[i] == sChar[left]) || count == 0) {
                //当前满足条件
                loc = sChar[left]-'A';
                if (count == 0) {
                    if (start == -1 || i - left < min) {//第一次满足条件
                        start = left;
                        end = i;
                        min = i - left;
                    }
                    count++;
                }
                value[loc]++;
                left++;
                if (left < sChar.length) {
                    loc = sChar[left]-'A';
                }
                while (left < sChar.length && (need[loc] == 0 || value[loc] < 0)) {
                    value[loc]++;
                    left++;
                    if (left < sChar.length) {
                        loc = sChar[left]-'A';
                    }
                }
            }
        }

        if (start == -1) {
            return "";
        }
        return s.substring(start,end+1);
    }

    //最快解法，
    /*public String minWindow(String s,String t){
        int[] value = new int[60],need = new int[60];
        char[] sChar = s.toCharArray(),tChar = t.toCharArray();
        int left=0,start=-1,end=-1,min=-1,count=tChar.length;

        for (int i = 0; i < count; i++) {
            int loc = tChar[i] - 'A';
            value[loc]++;
            need[loc]++;
        }

        for (int i = 0; i < sChar.length; i++) {
            int loc = sChar[i] - 'A';
            if (value[loc] > 0) {
                count--;
            }
            value[loc]--;

            //此时开始收缩滑动窗口
            while (count == 0) {
                loc = sChar[left] - 'A';
                if (need[loc] > 0 && value[loc] == 0) {
                    count++;
                    if (min == -1 || i - left < min) {
                        start = left;
                        end = i;
                        min = i - left;
                    }
                }
                value[loc]++;
                left++;
            }
        }

        if (end == -1) {
            return "";
        }
        return s.substring(start,end+1);
    }*/
}
