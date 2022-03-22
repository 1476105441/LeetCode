package 中级算法.数学;

import java.util.HashMap;
import java.util.Map;

public class No7 {
    //              分数到小数
    //给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
    //如果小数部分为循环小数，则将循环的部分括在括号内。
    //如果存在多个答案，只需返回 任意一个 。
    //对于所有给定的输入，保证 答案字符串的长度小于 104 。

    //想法：在进行除法之前使用哈希表记录当前被除数，然后采用整除得到当前
    //的结果，将结果使用字符串拼接对象存储，若是被除数比除数要小，则将被
    //除数乘于10

    public static String fractionToDecimal(int numerator,int denominator){
        StringBuilder sb = new StringBuilder();
        Map<Long,Integer> map = new HashMap<>();
        int count = 0;
        long x = numerator,y = denominator,consult;
        Integer temp;

        //首先判断除数与被除数符号是否一致，符号一致和不一致要用两种不同的情况来判断
        if ((x > 0 && y < 0) || (x < 0 && y > 0)) {
            sb.append('-');
        }
        //没有考虑到计算结果是否会溢出
        /*numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);*/
        x = Math.abs(x);
        y = Math.abs(y);

        //map.put(x,count);
        if (x < y) {
            sb.append(0);
            x *= 10;
        }else{
            consult = x / y;
            sb.append(consult);
            x = (x % y) * 10;
        }
        if (x != 0) {
            sb.append('.');
        }

        while (x != 0) {
            count = sb.length();
            //说明当前被除数已经出现过了，现在出现了重复的，说明可能出现了循环的情况
            if ((temp = map.get(x)) != null) {
                //这个方法太慢
                /*String res = "(" + sb.toString().substring(temp,count)+ ")";
                sb.delete(temp,count);
                sb.append(res);*/

                sb.insert(temp.intValue(), '(').append(')');
                break;
            }
            if (x < y) {
                map.put(x,count);
                sb.append(0);
                x *= 10;
            }else{
                consult = x / y;
                map.put(x,count);
                sb.append(consult);
                x = (x % y) * 10;
            }
        }

        return sb.toString();
    }

    /*public String fractionToDecimal(int numerator,int denominator){
        StringBuilder sb = new StringBuilder();
        long a = numerator, b = denominator;
        if (a > 0 && b < 0 || a < 0 && b > 0) sb.append('-');
        a = Math.abs(a);
        b = Math.abs(b);
        sb.append(a / b);
        if (a % b == 0) return sb.toString();
        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while ((a = (a % b) * 10) > 0 && !map.containsKey(a)) {
            map.put(a, sb.length());
            sb.append(a / b);
        }
        if (a == 0) return sb.toString();
        return sb.insert(map.get(a).intValue(), '(').append(')').toString();
    }*/

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(2147483647,37));
    }
}
