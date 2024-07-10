package daily.year2024.m7;

import java.util.*;

/**
 * No.127
 *
 * @author wjs 2024/7/10
 */
public class d10 {
    //解法1：bfs
    //想法：这个题目和上一个题目应该没什么不一样？是否有什么坑呢？
    /*public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        char[] letters = new char[26];
        char c = 97;
        for(int i=0;i < 26;i++) {
            letters[i] = (char)(c+i);
        }
        queue.add(beginWord);
        int res = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                String word = queue.poll();
                if(word.equals(endWord)) {
                    return res;
                }
                for(int i=0;i < word.length();i++) {
                    for(char letter : letters) {
                        String next = replaceCharAt(word,i,letter);
                        if(!visited.contains(next) && wordSet.contains(next)) {
                            visited.add(next);
                            queue.add(next);
                        }
                    }
                }
                size--;
            }
            res++;
        }
        return 0;
    }
    private String replaceCharAt(String word,int idx,char c) {
        char[] chars = word.toCharArray();
        chars[idx] = c;
        return new String(chars);
    }*/

    //解法2：另一个思路，建图再进行bfs
}
