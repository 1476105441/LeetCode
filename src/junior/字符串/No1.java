package junior.字符串;

public class No1 {
    //               反转字符串
    //  编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
    //不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    //你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhbqj/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //字符串是以数组形式给出的，相当于对数组进行修改操作，是否可以考虑双指针？
    public void reverseString(char[] s) {
        //采用双指针，一个指向数组头，一个指向数组尾
        int begin = 0,end = s.length-1;
        char temp;
        while (begin < end) {
            temp = s[begin];
            s[begin] = s[end];
            s[end] = temp;
            begin++;
            end--;
        }
    }
}
