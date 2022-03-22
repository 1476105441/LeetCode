package 初级算法.数学;

import java.util.ArrayList;
import java.util.List;

public class No1 {
    //                  Fizz Buzz
    //给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
    //    answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
    //    answer[i] == "Fizz" 如果 i 是 3 的倍数。
    //    answer[i] == "Buzz" 如果 i 是 5 的倍数。
    //    answer[i] == i 如果上述条件全不满足。
    //提示：
    //    1 <= n <= 10^4

    //考验数学能力的题目，其实是判断一个数是否是3或5的倍数
    public List<String> fizzBuzz(int n) {
        List<String> resList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                resList.add("FizzBuzz");
            } else if (i % 3 == 0) {
                resList.add("Fizz");
            } else if (i % 5 == 0) {
                resList.add("Buzz");
            }else{
                resList.add(String.valueOf(i));
            }
        }

        return resList;
    }
}
