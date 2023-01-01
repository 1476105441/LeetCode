package contest.year2022.team;

public class No4 {
    int[] count;
    int n;
    {
        char[] c = "helloleetcode".toCharArray();
        n = c.length;
        count = new int[26];
        for (int i = 0; i < n; i++) {
            count[c[i]-'a']++;
        }
    }
    public int Leetcode(String[] words) {
        int m = words.length,res,l = 1;
        char[][] c = new char[m][];
        Node[] nodes = new Node[m];

        for (int i = 0; i < m; i++) {
            c[i] = words[i].toCharArray();
            nodes[i] = new Node();
            nodes[i].r = c[i].length-1;
            nodes[i].len = c[i].length;
        }

        while (n > 0) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < l; j++) {
                    res = 1;
                }
            }
            l++;
        }
        return 1;
    }

    class Node{
        int l,r,len;
    }
}
