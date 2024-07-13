package daily.year2024.m7;

import java.util.*;

/**
 * No.127
 *
 * @author wjs 2024/7/13
 */
public class d13 {
    //解法2：两边bfs
    static char[] letters;
    static {
        letters = new char[26];
        char a = 'a';
        for(int i=0;i < 26;i++) {
            letters[i] = (char)(a+i);
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        queue1.add(beginWord);
        queue2.add(endWord);
        visited1.add(beginWord);
        visited2.add(endWord);
        int res = 0;
        while(!queue1.isEmpty() || !queue2.isEmpty()) {
            int size = queue1.size();
            while(size > 0) {
                String word = queue1.poll();
                if(visited2.contains(word)) {
                    //判断是否是交错的情况，这种情况要多加1
                    if (queue2.contains(word)) {
                        return res * 2 + 1;
                    }
                    return res * 2;
                }
                for (int i = 0; i < word.length(); i++) {
                    for (char c : letters) {
                        String nextWord = replaceCharAt(word, i, c);
                        if (!visited1.contains(nextWord) && wordSet.contains(nextWord)) {
                            queue1.add(nextWord);
                            visited1.add(nextWord);
                        }
                    }
                }
                size--;
            }
            size = queue2.size();
            while(size > 0) {
                String word = queue2.poll();
                if(visited1.contains(word)) {
                    //判断是否是交错的情况，这种情况要多加1
                    //todo 重点在这
                    if (queue1.contains(word)) {
                        return (res+1) * 2;
                    }
                    return res * 2;
                }
                for (int i = 0; i < word.length(); i++) {
                    for (char c : letters) {
                        String nextWord = replaceCharAt(word, i, c);
                        if (!visited2.contains(nextWord) && wordSet.contains(nextWord)) {
                            queue2.add(nextWord);
                            visited2.add(nextWord);
                        }
                    }
                }
                size--;
            }
            res++;
        }
        return 0;
    }
    private String replaceCharAt(String word,int loc,char c) {
        char[] chars = word.toCharArray();
        chars[loc] = c;
        return new String(chars);
    }
}
