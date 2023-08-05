package contest.year2023.m5.d13;

/**
 * 2678.老人的数目
 *
 * @author wjs 2023/8/5
 */
public class No1 {
    public int countSeniors(String[] details) {
        int n = details.length;
        int res = 0;
        for(int i=0;i < n;i++) {
            char[] chars = details[i].toCharArray();
            if(chars[11] > '6' || (chars[11] == '6' && chars[12] != '0')) {
                res++;
            }
        }
        return res;
    }
}
