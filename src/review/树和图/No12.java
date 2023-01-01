package review.树和图;

import java.util.*;

public class No12 {
    /**
     *      单词接龙
     * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
     *
     *     每一对相邻的单词只差一个字母。
     *      对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
     *     sk == endWord
     *
     * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回
     * 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
     */

    //想法：建立图模型，每个单词是一个点，如果两个
    //单词之间只有一词之差则令这两点之间建立一条边
    /*int num;
    Map<String,Integer> map;
    List<List<Integer>> edge;
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        num = 0;
        map = new HashMap<>();
        edge = new ArrayList<>();
        //建立点集和边集
        link(beginWord);
        for (int i = 0; i < wordList.size(); i++) {
            link(wordList.get(i));
        }
        if (!map.containsKey(endWord)) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] flags = new boolean[num];
        int res = 0,endId = map.get(endWord);
        queue.add(map.get(beginWord));
        flags[0] = true;

        while (!queue.isEmpty()) {
            res++;
            int len = queue.size();
            while (len > 0) {
                int loc = queue.poll();
                if (loc == endId) {
                    return (res >> 1) + 1;
                }
                List<Integer> list = edge.get(loc);
                for (int i = 0; i < list.size(); i++) {
                    if (!flags[list.get(i)]) {
                        flags[list.get(i)] = true;
                        queue.offer(list.get(i));
                    }
                }
                len--;
            }
        }

        return 0;
    }

    public void link(String s){
        Integer loc = map.get(s);
        if (loc == null) {
            map.put(s,num);
            edge.add(new ArrayList<>());
            loc = num;
            num++;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            chars[i] = '*';
            String s1 = new String(chars);
            Integer loc2 = map.get(s1);
            if (loc2 == null) {
                map.put(s1,num);
                edge.add(new ArrayList<>());
                loc2 = num;
                num++;
            }
            edge.get(loc).add(loc2);
            edge.get(loc2).add(loc);
            chars[i] = temp;
        }
    }*/

    //优化建图+虚拟节点+广度优先遍历
    /*//点集，存储每个字符串和对应的下标
    Map<String,Integer> map;
    //边集合，其中用下标取出每个点对应的边集，其
    //中的数字就是和它有边的点的下标
    List<List<Integer>> edge;
    int num;
    boolean[] flags;
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        num = 0;
        map = new HashMap<>();
        edge = new ArrayList<>();
        link(beginWord);

        for (String s : wordList){
            link(s);
        }
        if (!map.containsKey(endWord)) {
            return 0;
        }

        flags = new boolean[map.size()];
        Queue<Integer> queue = new LinkedList<>();
        int res = 0,endId = map.get(endWord);
        queue.add(map.get(beginWord));

        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            while (size > 0) {
                Integer temp = queue.poll();
                if (temp == endId) {
                    return (res >> 1)+1;
                }
                List<Integer> list = edge.get(temp);
                for (int i = 0; i < list.size(); i++) {
                    if (!flags[list.get(i)]) {
                        flags[list.get(i)] = true;
                        queue.add(list.get(i));
                    }
                }
                size--;
            }
        }

        return 0;
    }

    public void link(String s){
        Integer loc = map.get(s);
        if (loc == null) {
            map.put(s,num);
            loc = num;
            edge.add(new ArrayList<>());
            num++;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            chars[i] = '*';
            String s2 = new String(chars);
            Integer loc2 = map.get(s2);
            if (loc2 == null) {
                loc2 = num;
                map.put(s2,loc2);
                edge.add(new ArrayList<>());
                num++;
            }
            chars[i] = temp;
            List<Integer> list1 = edge.get(loc),list2 = edge.get(loc2);
            list1.add(loc2);
            list2.add(loc);
        }
    }*/

    //优化，在上一方法的基础上进行双向的广度优先搜索
    Map<String,Integer> map;
    List<List<Integer>> edge;
    int num;
    int res;
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        map = new HashMap<>();
        edge = new ArrayList<>();
        num = 0;
        res = 0;
        link(beginWord);
        for(String s : wordList){
            link(s);
        }

        Integer endId = map.get(endWord),size = map.size();
        if (endId == null) {
            return 0;
        }
        boolean[] flag1 = new boolean[size],flag2 = new boolean[size];
        Queue<Integer> q1 = new LinkedList<>(),q2 = new LinkedList<>();
        q1.add(map.get(beginWord));
        q2.add(endId);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            res++;
            int size1 = q1.size(),size2 = q2.size();

            if (fun(size1, q1, flag1, flag2)) {
                return res;
            }
            res++;
            if (fun(size2, q2, flag2, flag1)) {
                return res;
            }
        }
        return 0;
    }

    public boolean fun(int size1,Queue<Integer> q1,boolean[] flag1,boolean[] flag2){
        while (size1 > 0) {
            Integer i1 = q1.poll();
            List<Integer> list = edge.get(i1);
            for (Integer i : list){
                if (!flag1[i]) {
                    if (flag2[i]) {
                        res = res >> 1 + 1;
                        return true;
                    }
                    flag1[i] = true;
                    q1.add(i);
                }
            }
            size1--;
        }
        return false;
    }

    public void link(String s){
        Integer loc = map.get(s);
        if (loc == null) {
            loc = num;
            map.put(s,num);
            num++;
            edge.add(new ArrayList<>());
        }

        char[] chars = s.toCharArray();
        List<Integer> list1 = edge.get(loc);
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            chars[i] = '*';
            String s2 = new String(chars);
            Integer loc2 = map.get(s2);
            if (loc2 == null) {
                map.put(s2,num);
                loc2 = num;
                num++;
                edge.add(new ArrayList<>());
            }
            List<Integer> list2 = edge.get(loc2);
            list2.add(loc);
            list1.add(loc2);
            chars[i] = temp;
        }
    }
}
