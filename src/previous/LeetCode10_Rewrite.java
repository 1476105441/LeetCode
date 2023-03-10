package previous;

public class LeetCode10_Rewrite {
    //????滮
    public static void main(String[] args) {
        String s = "12344";

        System.out.println(s.charAt(1));
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        //bp[i][j]???????ж?p???j?????????????s???i?????????????????飬???true??false
        boolean[][] bp = new boolean[m + 1][n + 1];
        //?????????????true???????е???????false
        bp[0][0] = true;

        //i??0????????????bp[0][j]????false
        //???bp?????i????????i?????????????????i?????????i-1
        for (int i = 0; i <= m; i++) {
            //j??1???????????????????
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    //???????鲻??????????'*'??????????????????λ????
                    //(????д???????????????)????????
                    bp[i][j] = bp[i][j - 2];
                    //????????
                    if (compare(s, i, p, j - 1)) {
                        //???p???j-2??????????s???i???????????????????????????????*??????????0??
                        bp[i][j] = bp[i][j] || bp[i - 1][j];
                    }
                } else {
                    if (compare(s, i, p, j - 1)) {
                        bp[i][j] = bp[i - 1][j - 1];
                    }
                }
            }
        }

        return bp[m][n];
    }

    public static boolean compare(String s, int i, String p, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return p.charAt(j - 1) == s.charAt(i - 1);
    }
}
