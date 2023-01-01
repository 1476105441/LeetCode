package senior.设计问题;

public class No2 {
    /**
     *      实现 Trie (前缀树)
     */

    class Trie {

        TrieTree root;

        public Trie() {
            root = new TrieTree();
        }

        public void insert(String word) {
            char[] wc = word.toCharArray();
            TrieTree node = root;
            for (int i = 0; i < wc.length; i++) {
                int loc = wc[i] - 'a';
                if (node.trees[loc] == null) {
                    node.trees[loc] = new TrieTree();
                }
                node = node.trees[loc];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            char[] wc = word.toCharArray();
            TrieTree node = root;
            for (int i = 0; i < wc.length; i++) {
                int loc = wc[i] - 'a';
                if(node.trees[loc] == null)
                    return false;
                node = node.trees[loc];
            }

            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            char[] wc = prefix.toCharArray();
            TrieTree node = root;
            for (int i = 0; i < wc.length; i++) {
                int loc = wc[i] - 'a';
                if(node.trees[loc] == null)
                    return false;
                node = node.trees[loc];
            }
            return true;
        }
    }

    class TrieTree{
        TrieTree[] trees;
        boolean isEnd;

        public TrieTree() {
            trees = new TrieTree[26];
            isEnd = false;
        }
    }
}
