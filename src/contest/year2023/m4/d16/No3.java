package contest.year2023.m4.d16;

/**
 * 2645. 构造有效字符串的最少插入数
 *
 * @author wjs 2023/5/27
 */
public class No3 {
    public int addMinimum(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int res = 0,index = 0;
        char[] template = {'a','b','c'};
        for(int i=0;i < n;i++) {
            while(chars[i] != template[index]) {
                res++;
                index = (index+1) % 3;
            }
            index = (index+1) % 3;
        }
        //正常流程全部走完时index应该为0
        if(index != 0) {
            res += 3-index;
        }
        return res;
    }
}
