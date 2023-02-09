package contest.year2023.m2.d5;

public class No2 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int m = words.length;
        char[][] chars = new char[m][];
        int[] sum = new int[m+1];
        int count = 0;
        for (int i = 0; i < m; i++) {
            chars[i] = words[i].toCharArray();
            sum[i] = count;
            if (check(chars[i][0]) && check(chars[i][chars[i].length - 1])) {
                count++;
            }
        }
        sum[m] = count;
        int n = queries.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int l = queries[i][0],r = queries[i][1];
            res[i] = sum[r+1] - sum[l];
        }

        return res;
    }
    private boolean check(char c){
        return c == 'a' || c == 'e' || c =='i' || c == 'o' || c == 'u';
    }
}
