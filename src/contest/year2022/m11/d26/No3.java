package contest.year2022.m11.d26;

public class No3 {
    public int bestClosingTime(String customers) {
        char[] chars = customers.toCharArray();
        int n = chars.length,val = 0;
        for (int i = 0; i < n; i++) {
            if(chars[i] == 'Y')
                val++;
        }
        int temp = val;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if(chars[i-1] == 'Y')
                temp--;
            else
                temp++;
            if (val > temp) {
                val = temp;
                res = i;
            }
        }
        return res;
    }
}
