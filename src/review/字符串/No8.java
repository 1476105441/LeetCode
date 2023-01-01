package review.字符串;

public class No8 {
    /**
     *      外观数列
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     *
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     *     countAndSay(1) = "1"
     *     countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     *
     * 前五项如下：
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     *
     * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。
     * 然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每
     * 组中的字符数量用数字替换，再将所有描述组连接起来。
     *
     * 提示：
     *     1 <= n <= 30
     */

    //迭代法，速度要比递归慢？
    /*public String countAndSay(int n){
        String pre = "1";
        int count;
        char c;

        for (int i = 1; i < n; i++) {
            StringBuilder now = new StringBuilder();
            char[] preChar = pre.toCharArray();
            count = 1;
            c = preChar[0];
            for (int j = 1; j < preChar.length; j++) {
                if (preChar[j] == c) {
                    count++;
                }else{
                    now.append(count);
                    now.append(c);
                    c = preChar[j];
                    count = 1;
                }
            }
            now.append(count);
            now.append(c);
            pre = now.toString();
        }

        return pre;
    }*/


    //递归写法
    String string;
    public String countAndSay(int n){
        recursion(n);
        return string;
    }

    public void recursion(int n){
        if (n == 1) {
            string = "1";
            return;
        }

        recursion(n-1);

        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char c = chars[0];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                count++;
            }else{
                sb.append(count);
                sb.append(c);
                c = chars[i];
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);

        string = sb.toString();
    }
}
