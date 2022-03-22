package 每日一题;

public class M1D5 {
    //              替换所有的问号
    //给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
    //注意：你 不能 修改非 '?' 字符。
    //题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
    //在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。

    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        char front,rear;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                if (i != 0) {
                    front = chars[i-1];
                }else{
                    front = '*';
                }

                if (i + 1 < chars.length) {
                    rear = chars[i+1];
                }else{
                    rear = '*';
                }

                chars[i] = 'a';
                while (chars[i] == front || chars[i] == rear) {
                    chars[i]++;
                }
            }
        }

        return new String(chars);
    }
}
