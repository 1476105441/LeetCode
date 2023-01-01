package contest.year2022.m8.m8d28;

import java.util.*;

public class No4 {
    /**
     *      给定条件下构造矩阵
     */

    //拓扑排序
    /*TreeMap<Integer,List<Integer>> edge1,edge2;  //分别存放行和列的对应关系
    int[] set1,set2; //标记当前节点是否放入当拓扑序列中
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        set1 = new int[k];
        set2 = new int[k];
        int[] row = new int[k];
        int[][] res = new int[k][k];
        edge1 = new TreeMap<>((x,y)->{
            return edge1.get(x).size() -edge1.get(y).size();
        });
        edge2 = new TreeMap<>((x,y)->{
            return edge2.get(x).size() -edge2.get(y).size();
        });
        for (int i = 0; i < k; i++) {
            edge1.put(i,new ArrayList<>());
            edge2.put(i,new ArrayList<>());
        }

        for (int i = 0; i < rowConditions.length; i++) {
            for (int j = 0; j < rowConditions[i].length-1; j++) {
                edge1.get(rowConditions[i][j]).add(rowConditions[i][j+1]);
            }
        }

        for (int i = 0; i < colConditions.length; i++) {
            for (int j = 0; j < colConditions[i].length-1; j++) {
                edge2.get(colConditions[i][j]).add(colConditions[i][j+1]);
            }
        }

        int c = k-1;
        while (!edge1.isEmpty()) {
            Map.Entry<Integer, List<Integer>> entry = edge1.firstEntry();
            row[entry.getKey()] = c;
            c++;
            edge1.remove(entry.getKey());
        }

        c = k-1;
        while (!edge2.isEmpty()) {
            Map.Entry<Integer, List<Integer>> entry = edge2.firstEntry();
            res[row[entry.getKey()]][c] = entry.getKey();
            c++;
            edge2.remove(entry.getKey());
        }

        return res;
    }*/

    /*List<List<Integer>> edge1,edge2;  //分别存放行和列的对应关系
    int[] set1,set2; //标记当前节点是否放入当拓扑序列中
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        set1 = new int[k];
        set2 = new int[k];
        int[] row = new int[k],col = new int[k];
        int[][] res = new int[k][k];
        edge1 = new ArrayList<>();
        edge2 = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            edge1.add(new ArrayList<>());
            edge2.add(new ArrayList<>());
        }

        for (int i = 0; i < rowConditions.length; i++) {
            for (int j = 0; j < rowConditions[i].length-1; j++) {
                edge1.get(rowConditions[i][j]).add(rowConditions[i][j+1]);
            }
        }

        for (int i = 0; i < colConditions.length; i++) {
            for (int j = 0; j < colConditions[i].length-1; j++) {
                edge2.get(colConditions[i][j]).add(colConditions[i][j+1]);
            }
        }

        int c1 = k-1,c2 = k-1;

        for (int i = 0; i < k; i++) {
            if (edge1.get(i).size() == 0) {
                row[i] = c1;
                set1[i] = 1;
                c1--;
            }
            if (edge2.get(i).size() == 0) {
                col[i] = c2;
                set2[i] = 1;
                c2--;
            }
        }

        return res;
    }*/

    //要建立的拓扑排序是行从上往下，列从左往右的拓扑
    //拓扑排序的思想：
    //首先，使用一个数组统计每个节点的入边个
    //数，并且建立有向图模型，然后，将所有入
    //度为0的节点先加到结果集中，并且放到队
    //列中，然后取出队列首的元素，将所有和它
    //相关的节点的入度减一，判断入度是否为0
    //为0则加入队列和结果集中，如此直到队列
    //为0，所有的元素都添加进入了结果集中
    /*int l;
    public int[] sort(int k,int[][] edge){
        int[] res = new int[k],count = new int[k];
        l = 0;

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            int x = e[0]-1,y = e[1]-1;  //使用数字-1是为了从0开始，方便操作
            list.get(x).add(y);  //建立有向边x->y，便于后面的操作
            count[y]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == 0) {
                res[l++] = i;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer x = queue.poll();
            for (Integer y : list.get(x)) {
                if (--count[y] == 0) {
                    res[l++] = y;
                    queue.offer(y);
                }
            }
        }

        return res;
    }
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] row = sort(k,rowConditions);
        //如果拓扑排序中有环，则结果集的元素数量比k小，正常的话应该等于k
        //原因：如果有环的话，环中的元素的入度不可能减为0，所以将元素放入
        //结果集中的时候会忽略这些元素
        if(l < k)
            return new int[][]{};
        int[] col = sort(k,colConditions);
        if(l < k)
            return new int[][]{};

        int[][] res = new int[k][k];
        int[] pos = new int[k]; //pos数组存放列的值
        for (int i = 0; i < k; i++) {
            //将第i列的元素的列值置为i
            pos[col[i]] = i;
        }

        for (int i = 0; i < k; i++) {
            res[i][pos[row[i]]] = row[i]+1;
        }

        return res;
    }*/


    //为了巩固思维，重新写一遍
    public int[] translate(int k,int[][] e){
        l = 0;
        int[] count = new int[k],res = new int[k];
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < e.length; i++) {
            int x = e[i][0]-1,y = e[i][1]-1;
            edge.get(x).add(y);
            count[y]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == 0) {
                queue.add(i);
                res[l++] = i;
            }
        }

        while (!queue.isEmpty()) {
            Integer x = queue.poll();
            for (Integer y : edge.get(x)) {
                if (--count[y] == 0) {
                    res[l++] = y;
                    queue.offer(y);
                }
            }
        }

        return res;
    }
    int l;
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions){
        int [] row,col;
        row = translate(k,rowConditions);
        if(l < k)
            return new int[][]{};
        col = translate(k,colConditions);
        if(l < k)
            return new int[][]{};

        int[][] res = new int[k][k];
        int[] pos = new int[k];  //记录某一个数字属于哪一行

        for (int i = 0; i < k; i++) {
            pos[row[i]] = i;
        }

        for (int i = 0; i < k; i++) {
            res[pos[col[i]]][i] = col[i]+1;
        }

        return res;
    }
}
