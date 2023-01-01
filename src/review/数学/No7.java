package review.数学;

public class No7 {
    /**
     *      Excel表列序号
     */

    //26进制
    public int titleToNumber(String columnTitle) {
        //c是进位
        int res = 0,c = 1;
        char[] sc = columnTitle.toCharArray();

        for (int i = sc.length-1; i >= 0; i--) {
            res += count(sc[i])*c;
            c *= 26;
        }

        return res;
    }
    public int count(char c){
        return c-'A'+1;
    }
}
