package senior.其他;

import java.util.*;

public class No1 {
    /**
     *      根据身高重建队列
     */

    //队列的组成要考虑多个因素
    //想法：将元素按照不同k值分组，组内先排序，然后组间选择合适位置插入
    //实现太过于复杂，放弃
    /*public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        TreeMap<Integer, List<Node>> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node(people[i][0],people[i][1]);
            List<Node> nodes = map.get(people[i][1]);
            if (nodes == null) {
                nodes = new ArrayList<>();
                map.put(people[i][1],nodes);
            }
            nodes.add(node);
            update(nodes,nodes.size()-1);
        }

        //给每个数组排序
        Set<Integer> set = map.keySet();
        for (Integer i : set){
            List<Node> nodes = map.get(i);
            int s = nodes.size();
            for (int j = s-1; j >= 0; j--) {
                Node temp = nodes.get(0);
                nodes.set(0,nodes.get(j));
                nodes.set(j,temp);
                shift(nodes,0,j);
            }
        }

        Node head = new Node(-1,-1);
        Integer key = map.firstKey();
        List<Node> nodes = map.get(key);

        while (!map.isEmpty()) {
            key = map.firstKey();
            nodes = map.get(key);

        }
        return null;
    }
    public void insert(Node head){

    }
    public void shift(List<Node> nodes,int start,int end){
        int i = start,j = (i << 1)+1;
        while (j < end) {
            if (j < end - 1 && nodes.get(j).h < nodes.get(j + 1).h) {
                j++;
            }
            if (nodes.get(i).h < nodes.get(j).h) {
                Node temp = nodes.get(i);
                nodes.set(i,nodes.get(j));
                nodes.set(j,temp);
                i = j;
                j = (i << 1)+1;
            }else
                break;
        }
    }
    public void update(List<Node> nodes,int start){
        int i = start,p = (i-1)>>1;
        while (i > 0) {
            if (nodes.get(i).h > nodes.get(p).h) {
                Node temp = nodes.get(i);
                nodes.set(i,nodes.get(p));
                nodes.set(p,temp);
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }
    class Node{
        public int h;
        public int k;
        public Node next;

        public Node(int h, int k) {
            this.h = h;
            this.k = k;
        }
    }*/

    //解法一：按低到高排序
    //核心思想：将数组元素按照高度从低到高排序，然后插入结果中，采用预留
    //空位置的方法留出比它大的后面的元素的位置，对于高度相同的元素，按照
    //k值从大到小排序，因为k值大的元素要提前预留出位置
    /*public int[][] reconstructQueue(int[][] people){
        int n = people.length;
        int[][] res = new int[n][];
        for (int i = 0; i < n; i++) {
            update(people,i);
        }
        for (int i = n-1; i >= 0; i--) {
            int[] temp = people[0];
            people[0] = people[i];
            people[i] = temp;
            shift(people,0,i);
        }


        for (int i = 0; i < n; i++) {
            int c = 0,j = 0;
            while (j < n) {
                if (res[j] == null) {
                    if (c == people[i][1]) {
                        res[j] = people[i];
                        break;
                    }else
                        c++;
                }
                j++;
            }
        }

        return res;
    }
    public int compare(int[] n1,int[] n2){
        if (n1[0] != n2[0]) {
            return n1[0]-n2[0];
        }
        return n2[1]-n1[1];
    }*/

    //公用的堆排序算法
    public static void shift(int[][] tmp,int start,int end){
        int i = start,j = (i<<1)+1;
        while (j < end) {
            if (j < end - 1 && compare(tmp[j], tmp[j + 1]) < 0) {
                j++;
            }
            if (compare(tmp[i],tmp[j]) < 0) {
                int[] temp = tmp[i];
                tmp[i] = tmp[j];
                tmp[j] = temp;
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public static void update(int[][] tmp,int start){
        int i = start,p = (i-1)>>1;
        while (i > 0) {
            if (compare(tmp[i],tmp[p]) > 0) {
                int[] temp = tmp[p];
                tmp[p] = tmp[i];
                tmp[i] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }
    public static void sort(int[][] people){
        int n = people.length;
        for (int i = 0; i < n; i++) {
            update(people,i);
        }
        for (int i = n-1; i >= 0; i--) {
            int[] temp = people[0];
            people[0] = people[i];
            people[i] = temp;
            shift(people,0,i);
        }
    }

    //解法二，从高到低排序
    public static int compare(int[] n1,int[] n2){
        if (n1[0] != n2[0]) {
            return n2[0]-n1[0];
        }
        return n1[1]-n2[1];
    }
    public int[][] reconstructQueue(int[][] people){
        int n = people.length;
        sort(people);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(people[i][1],people[i]);
        }

        return res.toArray(new int[n][]);
    }
}
