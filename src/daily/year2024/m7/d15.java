package daily.year2024.m7;

public class d15 {
    class WordDictionary {

        Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            char[] chars = word.toCharArray();
            Node cur = root;
            for(int i=0;i < chars.length;i++) {
                int idx = chars[i] - 'a';
                if(cur.child[idx] == null) {
                    cur.child[idx] = new Node();
                }
                cur = cur.child[idx];
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();
            return recursion(root,chars,0,chars.length);
        }

        private boolean recursion(Node cur,char[] chars,int i,int n) {
            if(i == n) {
                return cur.isEnd;
            }
            if(chars[i] == '.') {
                //可以走当前任何一个分支
                for(Node next : cur.child) {
                    if(next != null) {
                        boolean f = recursion(next,chars,i+1,n);
                        if(f) {
                            return true;
                        }
                    }
                }
                //任意一个分支都没有匹配成功
                return false;
            }
            int idx = chars[i] - 'a';
            if(cur.child[idx] == null) {
                return false;
            }
            return recursion(cur.child[idx],chars,i+1,n);
        }
    }
    class Node {
        Node[] child;
        boolean isEnd;
        public Node() {
            child = new Node[26];
            isEnd = false;
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
