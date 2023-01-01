package review.数学;

import java.util.HashMap;
import java.util.Map;

public class No11 {
    /**
     *      分数到小数
     */

    //一般而言，结果有两部分：整数部分和小数部分
    //注意：要注意正负号和边界值问题（-2147483648）
    public String fractionToDecimal(int numerator, int denominator) {
        long x = Math.abs(numerator),y = Math.abs(denominator);
        boolean flag = (numerator>0&&denominator<0 || numerator<0&&denominator>0);

        if (denominator == -2147483648) {
            y = 2147483648L;
        }
        if (numerator == -2147483648) {
            x = 2147483648L;
        }

        Map<Long,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        if (x < y) {
            sb.append(0);
        }else{
            sb.append(x/y);
            x = x % y;
        }
        if(x != 0)
            sb.append('.');
        x *= 10;
        while (x > 0) {
            Integer temp = map.get(x);
            if (temp == null) {
                map.put(x,sb.length());
                if (x < y) {
                    sb.append(0);
                }
                else{
                    sb.append(x/y);
                    x = x % y;
                }
                x *= 10;
            }else{
                String s = sb.substring(temp, sb.length());
                sb.delete(temp,sb.length());
                sb.append('(').append(s).append(')');
                break;
            }
        }

        if(flag)
            return '-'+sb.toString();
        else
            return sb.toString();
    }
}
