package 复习.数学;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No4 {
    /**
     *      罗马数字转整数
     */

    //常规解法
    /*public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int res = 0,pre = map.get(chars[0]);

        for (int i = 1; i < chars.length; i++) {
            int temp = map.get(chars[i]);
            if(pre == 0){
                pre = temp;
                continue;
            }
            if(temp > pre) {
                res += (temp - pre);
                pre = 0;
            }
            else{
                res += pre;
                pre = temp;
            }
        }
        res += pre;

        return res;
    }*/
    static Map<Character,Integer> map;
    static{
        map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
    }

    //逆向思维，不从左往右遍历，而是反过来
    public static int romanToInt(String s){
        char[] c = s.toCharArray();
        int max = 1,res = 0;

        for (int i = c.length-1; i >= 0; i--) {
            int temp = map.get(c[i]);
            if (temp >= max) {
                res += temp;
                max = temp;
            }else{
                res -= temp;
            }
        }

        return res;
    }
}
