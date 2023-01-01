package dataStructure;

public class Dijkastra {
    //计算源点到其他点的最短路径
    //在此算法中，用负数和0表示两点之间没有边
    public int[] dijkastra(int[][] edge,int source){
        int[] finish = new int[edge.length];
        int[] distance = new int[edge.length];
        int[] path = new int[edge.length];
        finish[source] = 1;
        for (int i = 0; i < edge.length; i++) {
            if (edge[source][i] > 0) {
                distance[i] = edge[source][i];
                path[i] = source;
            }else{
                distance[i] = Integer.MAX_VALUE;
                path[i] = -1;
            }
        }
        distance[source] = 0;

        int min,count = 1,temp=0;
        while (count < edge.length) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < edge.length; i++) {
                if (finish[i] == 0 && distance[i] < min) {
                    min = distance[i];
                    temp = i;
                }
            }

            //对于无连通图，这一步骤是必须的
            if (min == Integer.MAX_VALUE) {
                return distance;
            }
            finish[temp] = 1;

            for (int i = 0; i < edge.length; i++) {
                if (finish[i] == 0 && edge[temp][i] > 0 && distance[i] > distance[temp] + edge[temp][i]) {
                    distance[i] = distance[temp] + edge[temp][i];
                    path[i] = temp;
                }
            }
            count++;
        }
        return distance;
    }
}
