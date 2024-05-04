package daily.year2023.m1;

import java.util.HashMap;
import java.util.Map;

public class M1D3 {
    //              一周中的第几天
    //给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
    //输入为三个整数：day、month 和 year，分别表示日、月、年。
    //您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。

    //想法：计算天数，然后再对七求余数就是相对于1971年第一天的星期数的偏移量
    public String dayOfTheWeek(int day, int month, int year) {
        //res存放总的天数，x是年数差，y是偏移量
        int res = 0,x,y;
        //存储正常年份的每月天数
        int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
        //用于标记是否是闰年
        boolean flag = false;
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"Monday");
        map.put(2,"Tuesday");
        map.put(3,"Wednesday");
        map.put(4,"Thursday");
        map.put(5,"Friday");
        map.put(6,"Saturday");
        map.put(0,"Sunday");

        //1、计算年数差，是相对于包括1971年，所以计算时减去的是1970年
        x = (year - 1) - 1970;
        if (x == 1) {
            res += 365;
        } else if (x > 1) {
            //此计算公式为自己总结的，当年数差大于1时，肯定包括了1972这个闰年，而后的闰年
            // 都有规律，所以先将1971年的天数和1972年的天数加上
            res += 365 + 366 + (x - 2)/4 + (x - 2) * 365;
        }

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            flag = true;
        }

        //2、计算当前年的天数，先将当前月之前的月份的天数加起来
        for (int i = 0; i < month - 1; i++) {
            res += days[i];
        }
        if (flag && month > 2) {
            res++;
        }
        res += day;
        //由于计算的天数是包含了1971年1月1日，而我们需要的是相对于其的偏移量，所以要减去1
        res--;
        y = res % 7;
        y = (5 + y) % 7;

        return map.get(y);
    }
}
