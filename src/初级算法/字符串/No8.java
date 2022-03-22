package 初级算法.字符串;

public class No8 {
    //                  外观数列
    //给定一个正整数 n ，输出外观数列的第 n 项。
    //「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
    //你可以将其视作是由递归公式定义的数字字符串序列：
    //    countAndSay(1) = "1"
    //    countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
    //

    //-------------------------------------------------------------------------------------
    //递归，时间1ms，击败百分之98，但是内存消耗的有点多
    /*
    String string;
    public String countAndSay(int n) {
        function(n);
        return string;
    }
    //递归
    public void function(int n){
        if (n == 1) {
            string = "1";
            return;
        }
        function(n-1);
        char[] chars = string.toCharArray();
        //最多有chars.length个不同的数字
        int[] count = new int[chars.length];
        char[] newChar = new char[chars.length];
        //记录string中有几个不同的连续数字
        int point = 0;
        newChar[point] = chars[0];
        count[point]++;
        for (int i = 1; i < chars.length; i++) {
            //与前一个字符不相等的话，让point指向下一个元素，表示为不同的数字
            if (chars[i] != chars[i-1]) {
                point++;
                newChar[point] = chars[i];
            }
            count[point]++;
        }
        //记录完成，将结果生成为新的字符串
        StringBuilder builder = new StringBuilder(2*point);
        for (int i = 0; i <= point; i++) {
            builder.append(count[i]);
            builder.append(newChar[i]);
        }
        //更新string
        string = builder.toString();
    }*/
    //-------------------------------------------------------------------------------------


    //优化后的，只用一个StringBuilder来存放字符串，大大节省空间
    public String string;

    public String countAndSay(int n) {
        function(n);
        return string;
    }
    
    //递归
    public void function(int n){
        if (n == 1) {
            string = "1";
            return;
        }
        function(n-1);
        char[] chars = string.toCharArray();
        StringBuilder builder = new StringBuilder(chars.length);
        char c = chars[0];
        //记录string中有几个不同的连续数字
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            //与前一个字符不相等的话，将数字加入builder，前一字符加入builder
            if (chars[i] == c) {
                count++;
            }else {
                builder.append(count);
                builder.append(c);
                c = chars[i];
                count = 1;
            }
        }
        //将最后一次额外处理
        builder.append(count);
        builder.append(c);
        //更新string
        string = builder.toString();
    }
}
