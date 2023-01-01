package contest.year2022.m8.m8d21;

public class No2 {
    /**
     *      最大回文数字
     */

    //统计数字的个数？
    public String largestPalindromic(String num) {
        char[] c = num.toCharArray();
        int n = c.length,max = -1;
        int[] bucket = new int[10];

        for (int i = 0; i < n; i++) {
            bucket[c[i]-'0']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (bucket[i] >= 2) {
                bucket[i] -= 2;
                sb.append(i);
            }
            if (bucket[i] >= 1 && max == -1) {
                max = i;
            }
        }

        //除了0之外没有成对的元素
        if (sb.length() < 1) {
            return String.valueOf(max);
        }
        if (sb.charAt(0) == '0') {
            return max == -1 ? "0" : String.valueOf(max);
        }
        int s = sb.length()-1;
        if (max != -1) {
            sb.append(max);
        }
        while (s > -1) {
            sb.append(sb.charAt(s));
            s--;
        }

        return sb.toString();
    }

    /*public String largestPalindromic(String num) {
        char[] c = num.toCharArray();
        int n = c.length,max = -1;
        int[] bucket = new int[10];
        String res = "";

        for (int i = 0; i < n; i++) {
            bucket[c[i]-'0']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (bucket[i] >= 2) {
                bucket[i] -= 2;
                sb.append(i);
            }
            if (bucket[i] >= 1 && max == -1) {
                max = i;
            }
        }

        //除了0之外没有成对的元素
        if (sb.length() < 1 || sb.charAt(0) == '0') {
            return String.valueOf(max);
        }
        res = sb.toString();
        if (max != -1) {
            res += max;
        }
        res += sb.reverse();

        return res;
    }*/
}
