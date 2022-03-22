package 高级算法.回溯算法;

//前缀树
public class Trie {
    String word;
    Trie[] next;
    boolean isEnd;
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }

    public void insert(String word) {
        char[] chars = word.toCharArray();
        Trie node = this;
        for (int i = 0; i < chars.length; i++) {
            int loc = chars[i]-'a';
            if (node.next[loc] == null) {
                node.next[loc] = new Trie();
            }
            node = node.next[loc];
        }
        node.isEnd = true;
        node.word = word;
    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Trie node = this;
        for (int i = 0; i < chars.length; i++) {
            int loc = chars[i]-'a';
            if (node.next[loc] == null) {
                return false;
            }
            node = node.next[loc];
        }

        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Trie node = this;
        for (int i = 0; i < chars.length; i++) {
            int loc = chars[i]-'a';
            if (node.next[loc] == null) {
                return false;
            }
            node = node.next[loc];
        }
        return true;
    }
}
