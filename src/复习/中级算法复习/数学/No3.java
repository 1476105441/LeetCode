package 复习.中级算法复习.数学;

public class No3 {
    //          Excel表列序号

    public int titleToNumber(String columnTitle){
        char[] chars = columnTitle.toCharArray();
        int res = 0,length = chars.length - 1;

        for (int i = 0; i < chars.length; i++) {
            if (length != 0) {
                res += (chars[i] - 64) * Math.pow(26,length);
                length--;
            }else{
                res += chars[i] - 64;
            }
        }

        return res;
    }
}
