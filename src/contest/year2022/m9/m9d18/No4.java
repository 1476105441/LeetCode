package contest.year2022.m9.m9d18;

public class No4 {
    /**
     *      字符串的前缀分数和
     */

    public int[] sumPrefixScores(String[] words) {
        Node root = new Node();
        for (String word : words) {
            char[] c = word.toCharArray();
            Node node = root;
            for (int i = 0; i < c.length; i++) {
                int loc = c[i] - 'a';
                if (node.next[loc] == null) {
                    node.next[loc] = new Node();
                }else
                    node.next[loc].count++;
                node = node.next[loc];
            }
        }
        int[] res = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            char[] c = words[i].toCharArray();

            Node node = root;
            for (int j = 0; j < c.length; j++) {
                int loc = c[j] - 'a';
                res[i] += node.next[loc].count;
                node = node.next[loc];
            }
        }

        return res;
    }
    class Node{
        Node[] next = new Node[26];
        int count = 1;
    }
}

