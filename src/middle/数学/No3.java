package middle.数学;

public class No3 {
    //              Excel表列序号
    //给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。

    //-------------------------------------------------------------------------

    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        return recursion(chars,0);
    }

    public int recursion(char[] chars,int begin){
        int length;
        length = chars.length - begin;
        if (length == 1) {
            return chars[begin] - 'A' + 1;
        }

        return (int)((chars[begin] - 'A' + 1) * Math.pow(26,length-1)) + recursion(chars,begin+1);
    }
    //---------------------------------------------------------------------------
}
