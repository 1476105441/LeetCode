package 复习.中级算法复习.数学;

import java.util.HashMap;
import java.util.Map;

public class No7 {
    //              分数到小数

    /**
     * 思想：除第一次除法之外，小数点之后的每次除法之前，先
     * 检查哈希表中是否有该被除数，若是已经存在该被除数，说
     * 明开始循环了，直接取出哈希表中的value（就是该被除数
     * 第一次进行相除结果的位置），然后截取这部分，给它加上
     * 括号，再放回StringBuilder中，退出循环。若是不存在
     * 当前被除数，再将被除数保存在哈希表中，执行下一次除法
     * 循环
     * 注意事项：除法运算，注意正负号的问题，以及一些特殊情况
     */
    public String fractionToDecimal(int numerator,int denominator){
        Map<Long,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        long temp1 = denominator,temp2 = numerator;
        int count = 0;
        boolean flag = (numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0);

        temp1 = temp2 / temp1;

        //两数异号，且第一个除不了，要加上负号
        if (flag && temp1 == 0) {
            sb.append("-");
        }

        sb.append(temp1).append(".");

        //由于第一次除法可能不止一位数，而且还可能负号以
        //及"."号，所以直接加上stringBuilder的长度
        count += sb.length();
        temp2 = (numerator % denominator) * 10;

        //被除数为0，说明整除了，需要把前面加的点去掉
        if (temp2 == 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        //将除数与被除数都变成负数，以便后续的处理
        if (denominator > 0) {
            denominator *= -1;
        }
        if (temp2 > 0) {
            temp2 *= -1;
        }

        while (temp2 != 0) {
            //检查被除数是否已经存在
            if (map.containsKey(temp2)) {
                int loc = map.get(temp2);
                String s = sb.substring(loc);
                sb.delete(loc,sb.length());
                sb.append("(").append(s).append(")");
                break;
            }
            //被除数比除数的绝对值要小，则当前位的除法结果为0
            if (temp2 > denominator) {
                map.put(temp2,count);
                temp2 *= 10;
                sb.append(0);
                count++;
                continue;
            }
            map.put(temp2,count++);
            temp1 = temp2 / denominator;
            temp2 = (temp2 % denominator) * 10;
            sb.append(temp1);
        }

        return sb.toString();
    }
}
