package senior.树和图;

import java.util.*;

public class No1 {
    /*
     *              单词接龙
     *字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
     *
     *     每一对相邻的单词只差一个字母。
     *      对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
     *     sk == endWord
     *
     * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
     */

    /*
     * 想法：使用哈希表存放wordList中的每一个字符串，
     * 然后使用深度优先遍历每次改变一个字符，查看是否
     * 能够到达endWord
     */
    /*public int ladderLength(String beginWord, String endWord, List<String> wordList){
        if (beginWord.length() != endWord.length()) {
            return 0;
        }
        char[] begin = beginWord.toCharArray();
        char[] end = endWord.toCharArray();
        Set<String> set = new HashSet<>(wordList);
        return dfs(begin,end,set,0);
    }

    public int dfs(char[] begin, char[] end, Set<String> set,int count){
        StringBuilder sb;
        char temp;
        int n = begin.length,res = 0;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (begin[i] != end[i]) {
                temp = begin[i];
                begin[i] = end[i];
                sb = new StringBuilder();
                sb.append(begin);
                if (set.contains(sb.toString())) {
                    res = dfs(begin,end,set,count+1);
                    if (res != 0) {
                        flag = true;
                        break;
                    }
                }
                begin[i] = temp;
                flag = false;
            }
        }

        if (flag){
            if (res == 0) {
                return count;
            }else{
                return res;
            }
        }else{
            return 0;
        }
    }*/
    /*
      上述思路失败，因为并不是直接就会有路径到达字符串
    而是有可能走弯路，没考虑到这一点
    */

    //--------------------------------------------------------------------

    /*   方法一：优化建图+广度优先遍历
     * 将问题转化为图问题，每个字符串视为一
     * 个点，而仅有一个字符之差的连个字符串
     * 之间视为有一条边，该问题就变成了寻找
     * 最短路径问题
     */
    /*List<List<Integer>> edge = new ArrayList<>();
    //点集，Integer代表的是该点在边集中的下标
    Map<String,Integer> map = new HashMap<>();
    int num = 0;
    public int ladderLength(String beginWord,String endWord,List<String> wordList){

        //1、建立点集同时建立边集
        //将提供的字符串全部加入到点集中，同时要将其对应的模糊节点加入进去
        add(beginWord);
        for (String s : wordList) {
            add(s);
        }

        Integer beginId = map.get(beginWord),endId = map.get(endWord);
        if (endId == null) {
            return 0;
        }
        //使用数组进行存储每个位置对应的路径长度
        int[] dis = new int[num];
        dis[beginId] = 0;

        //2、开始寻找路径，使用队列进行广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(beginId);
        while (!queue.isEmpty()) {
            int loc = queue.poll();
            if (loc == endId) {
                return dis[endId] / 2 + 1;
            }
            List<Integer> list = edge.get(loc);
            for (int i : list){
                if (i != beginId && dis[i] == 0) {
                    queue.offer(i);
                    dis[i] = dis[loc]+1;
                }
            }
        }
        return 0;
    }

    //添加开始字符串以及提供的字符串的函数
    public void add(String s){
        int loc1 = num;
        map.put(s,num++);
        edge.add(new ArrayList<>());
        char[] chars = s.toCharArray();

        //添加模糊节点，和当前点到模糊节点的边
        for (int i = 0; i < chars.length; i++) {
            addLikePoint(chars,loc1,i);
        }
    }

    //添加模糊节点的函数
    public void addLikePoint(char[] chars,int loc1,int i){
        char temp = chars[i];
        Integer loc2;
        chars[i] = '*';
        String s1 = new String(chars);
        loc2 = map.get(s1);
        if (loc2 == null) {
            loc2 = num;
            map.put(s1.toString(),num++);
            edge.add(new ArrayList<>());
        }
        edge.get(loc2).add(loc1);
        edge.get(loc1).add(loc2);
        chars[i] = temp;
    }*/

    /*
     * 时间复杂度分析：设单词长度为c，建图
     * 过程中每遍历一个节点都要拓展c个模糊
     * 节点，总的建图时间复杂度为O(n*c)，
     * 在寻找路径时，总共有n*c个节点，每个
     * 节点最坏情况下都有c个选择可以走，所
     * 以最坏时间复杂度为O(n*c*c)
     */

    //----------------------------------------------------------------------------

    /*   方法二：双向广度优先搜索
     *总体与方法一类似，在搜索时进行优化，
     * 从头和尾同时开始广度优先遍历，当发
     * 现遍历过同一节点时，就停止遍历，这
     * 样缩短了寻找的时间
     */
    /*List<List<Integer>> edge = new ArrayList<>();
    //点集，Integer代表的是该点在边集中的下标
    Map<String,Integer> map = new HashMap<>();
    int num = 0;
    public int ladderLength(String beginWord,String endWord,List<String> wordList){

        //1、建立点集同时建立边集
        //将提供的字符串全部加入到点集中，同时要将其对应的模糊节点加入进去
        add(beginWord);
        for (String s : wordList) {
            add(s);
        }

        Integer beginId = map.get(beginWord),endId = map.get(endWord);
        if (endId == null) {
            return 0;
        }
        //使用数组进行存储每个位置对应的路径长度
        int[] dis1 = new int[num];
        int[] dis2 = new int[num];

        //2、开始寻找路径，使用队列进行广度优先遍历
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(beginId);
        queue2.offer(endId);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int loc1 = queue1.poll();
            int loc2 = queue2.poll();
            List<Integer> list1 = edge.get(loc1);
            List<Integer> list2 = edge.get(loc2);
            for (int i : list1){
                if (i != beginId && dis1[i] == 0) {
                    queue1.offer(i);
                    dis1[i] = dis1[loc1]+1;
                }
                if (dis2[i] != 0) {
                    return (dis1[i] + dis2[i]) / 2 + 1;
                }
            }
            for (int i : list2){
                if (i != endId && dis2[i] == 0) {
                    queue2.offer(i);
                    dis2[i] = dis2[loc2] + 1;
                }
                if (dis1[i] != 0) {
                    return (dis1[i] + dis2[i]) / 2 + 1;
                }
            }
        }
        return 0;
    }

    //添加开始字符串以及提供的字符串的函数
    public void add(String s){
        int loc1 = num;
        map.put(s,num++);
        edge.add(new ArrayList<>());
        char[] chars = s.toCharArray();

        //添加模糊节点，和当前点到模糊节点的边
        for (int i = 0; i < chars.length; i++) {
            addLikePoint(chars,loc1,i);
        }
    }

    //添加模糊节点的函数
    public void addLikePoint(char[] chars,int loc1,int i){
        char temp = chars[i];
        Integer loc2;
        chars[i] = '*';
        String s1 = new String(chars);
        loc2 = map.get(s1);
        if (loc2 == null) {
            loc2 = num;
            map.put(s1.toString(),num++);
            edge.add(new ArrayList<>());
        }
        edge.get(loc2).add(loc1);
        edge.get(loc1).add(loc2);
        chars[i] = temp;
    }*/


    //重新写一遍，过程异常痛苦
    /*Map<String,Integer> point;
    List<List<Integer>> edge;
    int l;
    public int ladderLength(String beginWord,String endWord,List<String> wordList){
        point = new HashMap<>();
        edge = new ArrayList<>();
        l = 0;

        for(String s : wordList){
            updateMap(s);
        }

        Integer start = point.get(beginWord);
        if(start == null){
            updateMap(beginWord);
            start = point.get(beginWord);
        }

        Integer end = point.get(endWord);
        if(end == null)
            return 0;

        int[] val = new int[l];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            Integer i = queue.poll();
            if(i.equals(end))
                return val[i]/2 + 1;
            List<Integer> list = edge.get(i);
            for(Integer j : list){
                if(!j.equals(start) && val[j] == 0){
                    val[j] = val[i]+1;
                    queue.offer(j);
                }
            }
        }
        return 0;
    }
    public void updateMap(String s){
        char[] sc = s.toCharArray();
        int loc = l;
        point.put(s,loc);
        l++;

        List<Integer> sl = new ArrayList<>();
        edge.add(sl);
        for(int i = 0;i < sc.length;i++){
            char temp = sc[i];
            sc[i] = '*';
            String t = new String(sc);
            Integer tLoc = point.get(t);
            if(tLoc == null){
                tLoc = l;
                point.put(t,tLoc);
                l++;
                edge.add(new ArrayList<>());
            }
            sl.add(tLoc);
            edge.get(tLoc).add(loc);
            sc[i] = temp;
        }
    }*/


    //解法二：双向广度优先遍历
    Map<String,Integer> map;
    List<List<Integer>> edge;
    int l;
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        map = new HashMap<>();
        edge = new ArrayList<>();
        l = 0;

        for(String s : wordList){
            updateMap(s);
        }

        Integer start = map.get(beginWord);
        if(start == null){
            updateMap(beginWord);
            start = map.get(beginWord);
        }
        Integer end = map.get(endWord);
        if(end == null){
            return 0;
        }

        Queue<Integer> sq = new LinkedList<>(),eq = new LinkedList<>();
        int[] sv = new int[l],ev = new int[l];
        sq.add(start);
        eq.add(end);
        while(!sq.isEmpty() && !eq.isEmpty()){
            Integer s = sq.poll(),e = eq.poll();
            List<Integer> sl = edge.get(s),el = edge.get(e);
            for(Integer j : sl){
                if(!j.equals(s) && sv[j] == 0){
                    if(ev[j] != 0){
                        return (sv[s]+ev[j]+1)/2+1;
                    }
                    sq.offer(j);
                    sv[j] = sv[s]+1;
                }
            }
            for(Integer j : el){
                if(!j.equals(e) && ev[j] == 0){
                    if(sv[j] != 0){
                        return (ev[e]+sv[j]+1)/2+1;
                    }
                    eq.offer(j);
                    ev[j] = ev[e]+1;
                }
            }
        }
        return 0;
    }
    public void updateMap(String s){
        char[] sc = s.toCharArray();
        int loc = l;
        map.put(s,l++);

        List<Integer> sl = new ArrayList<>();
        edge.add(sl);

        for(int i = 0;i < sc.length;i++){
            char temp = sc[i];
            sc[i] = '*';

            String t = new String(sc);
            Integer tLoc = map.get(t);
            if(tLoc == null){
                tLoc = l;
                map.put(t,l++);
                edge.add(new ArrayList<>());
            }
            sl.add(tLoc);
            edge.get(tLoc).add(loc);

            sc[i] = temp;
        }
    }
}
