package contest.year2022.m9.m9d3;

public class No2 {
    /**
     *      严格回文的数字
     */

    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; i++) {
            if(!examine(translate(i,n)))
                return false;
        }
        return true;
    }
    public boolean examine(String s){
        char[] c = s.toCharArray();
        int l = 0,r = c.length-1;
        while (l < r) {
            if(c[l] != c[r])
                return false;
            l++;
            r--;
        }
        return true;
    }

    public String translate(int n,int num){
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num%n);
            num /= n;
        }
        return sb.reverse().toString();
    }

}