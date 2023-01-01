package contest.year2022.m8.m8d28;

public class No2 {
    /**
     *      从字符串中移除星号
     */

    public String removeStars(String s) {
        char[] c = s.toCharArray();
        int n = c.length,count = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = n-1; i >= 0; i--) {
            if(c[i] == '*')
                count++;
            else if (count > 0) {
                count--;
            }else{
                sb.append(c[i]);
            }
        }

        return sb.reverse().toString();
    }
}
