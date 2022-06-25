package test;

import java.sql.Time;
import java.sql.Timestamp;

public class TimeUtilTest {
    public static Time plus(String t1, Time t2){
        String[] split1 = t1.split(":");
        String[] split2 = t2.toString().split(":");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            char[] chars1 = split1[i].toCharArray();
            char[] chars2 = split2[i].toCharArray(),chars3;  //使用chars3临时存放相加之后的字符
            int loc1 = chars1.length-1,loc2 = chars2.length-1,loc3,c = 0;

            if (loc1 > loc2) {
                chars3 = chars1;
                loc3 = loc1;
            }else{
                loc3 = loc2;
                chars3 = chars2;
            }

            while (loc1 >= 0 && loc2 >= 0) {
                int temp = chars1[loc1] + chars2[loc2] +c - 2 * '0';
                if (temp > 10) {
                    c = 1;
                }else
                    c = 0;
                chars3[loc3--] = (char)(temp%10+'0');
                loc1--;
                loc2--;
            }

            //进行收尾工作
            while (loc1 >= 0) {
                int temp = chars1[loc1] + c - '0';
                if (temp > 10) {
                    c = 1;
                }else
                    c = 0;
                chars3[loc3--] = (char)(temp%10+'0');
                loc1--;
            }
            while (loc2 >= 0) {
                int temp = chars2[loc2] + c - '0';
                if (temp > 10) {
                    c = 1;
                }else
                    c = 0;
                chars3[loc3--] = (char)(temp%10+'0');
                loc2--;
            }
            if (c == 1) {
                sb.append(1);
            }
            for (int j = 0; j < chars3.length; j++) {
                sb.append(chars3[j]);
            }
            sb.append(':');
        }

        //删去最后一个多余的':'
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        return Time.valueOf(sb.toString());
    }
}
