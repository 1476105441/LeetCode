package junior.数学;

import java.util.HashMap;
import java.util.Map;

public class No4 {
    //                  罗马数字转整数
    //例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
    //
    //通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
    //
    //    I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
    //    X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
    //    C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
    //
    //给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

    //想法：使用哈希表存储罗马数字与数字的转换

    Map<Character,Integer> map = new HashMap<>();

    //实例代码块，实例化对象时才会执行
    {
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
    }

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();

        int res = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I' && i + 1 < chars.length) {

                if (chars[i + 1] == 'X') {
                    res += map.get('X') - map.get('I');
                    i++;
                }else if (chars[i + 1] == 'V') {
                    res += map.get('V') - map.get('I');
                    i++;
                }else{
                    res += map.get('I');
                }

            }else if (chars[i] == 'X' && i + 1 < chars.length) {

                if (chars[i + 1] == 'L') {
                    res += map.get('L') - map.get('X');
                    i++;
                }else if (chars[i + 1] == 'C') {
                    res += map.get('C') - map.get('X');
                    i++;
                }else{
                    res += map.get('X');
                }

            }else if (chars[i] == 'C' && i + 1 < chars.length) {

                if (chars[i + 1] == 'D') {
                    res += map.get('D') - map.get('C');
                    i++;
                }else if (chars[i + 1] == 'M') {
                    res += map.get('M') - map.get('C');
                    i++;
                }else{
                    res += map.get('C');
                }

            }else {
                res += map.get(chars[i]);
            }
        }

        return res;
    }
}
