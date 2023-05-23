package contest.year2023.m4.d15;

/**
 * 2642.设计可以求最短路径的图类
 *
 * @author wjs 2023/5/22
 */
public class No4 {

    //失败的写法，因为更新时忽略了一些边的情况
    //只考虑了从x出发和以y为终点的情况，但其他
    //包含了这两点的路径值没有被更新
    /*class Graph {
        long[][] vals;
        int n;

        public Graph(int n, int[][] edges) {
            vals = new long[n][n];
            this.n = n;
            for(int i=0;i < n;i++){
                Arrays.fill(vals[i],Integer.MAX_VALUE);
                vals[i][i] = 0;
            }
            int m = edges.length;
            for(int i=0;i < m;i++){
                int x = edges[i][0],y = edges[i][1],v = edges[i][2];
                update(x,y,v);
            }
        }

        private void update(int x,int y,long v){
            if(vals[x][y] >= v){
                vals[x][y] = v;
                for(int j=0;j < n;j++){
                    if(j != x && j != y){
                        vals[x][j] = Math.min(vals[x][j],vals[y][j] + v);
                        vals[j][y] = Math.min(vals[j][y],vals[j][x] + v);
                    }
                }
            }
        }

        public void addEdge(int[] edge) {
            update(edge[0],edge[1],edge[2]);
        }

        public int shortestPath(int node1, int node2) {
            return vals[node1][node2] >= Integer.MAX_VALUE ? -1 : (int)vals[node1][node2];
        }
    }*/

    //265ms，效率很低
    /*class Graph {
        long[][] vals;
        int n;

        public Graph(int n, int[][] edges) {
            vals = new long[n][n];
            this.n = n;
            for(int i=0;i < n;i++){
                Arrays.fill(vals[i],Integer.MAX_VALUE);
                vals[i][i] = 0;
            }
            int m = edges.length;
            for(int i=0;i < m;i++){
                int x = edges[i][0],y = edges[i][1],v = edges[i][2];
                update(x,y,v);
            }
        }

        private void update(int x,int y,long v){
            if(vals[x][y] >= v){
                vals[x][y] = v;
                for(int i=0;i < n;i++){
                    for(int j=0;j < n;j++){
                        vals[i][j] = Math.min(vals[i][j],vals[i][x] + v + vals[y][j]);
                    }
                }
            }
        }

        public void addEdge(int[] edge) {
            update(edge[0],edge[1],edge[2]);
        }

        public int shortestPath(int node1, int node2) {
            return vals[node1][node2] >= Integer.MAX_VALUE ? -1 : (int)vals[node1][node2];
        }
    }*/


    //65ms，效率高多了，主要原因在前面的写法时间复杂度是O(M*N^2)
    //而这种写法的时间复杂度是O(N^3)
    /*class Graph {
        int[][] vals;
        int n;

        public Graph(int n, int[][] edges) {
            vals = new int[n][n];
            this.n = n;
            for(int i=0;i < n;i++){
                Arrays.fill(vals[i],Integer.MAX_VALUE/3);
                vals[i][i] = 0;
            }
            int m = edges.length;
            for(int i=0;i < m;i++){
                int x = edges[i][0],y = edges[i][1],v = edges[i][2];
                vals[x][y] = Math.min(v,vals[x][y]);
            }
            for(int i=0;i < n;i++){
                for(int j=0;j < n;j++){
                    for(int k=0;k < n;k++){
                        vals[j][k] = Math.min(vals[j][k],vals[j][i]+vals[i][k]);
                    }
                }
            }
        }

        private void update(int x,int y,int v){
            if(vals[x][y] > v){
                vals[x][y] = v;
                for(int i=0;i < n;i++){
                    for(int j=0;j < n;j++){
                        vals[i][j] = Math.min(vals[i][j],vals[i][x] + v + vals[y][j]);
                    }
                }
            }
        }

        public void addEdge(int[] edge) {
            update(edge[0],edge[1],edge[2]);
        }

        public int shortestPath(int node1, int node2) {
            return vals[node1][node2] >= Integer.MAX_VALUE/3 ? -1 : vals[node1][node2];
        }
    }*/

    //另一种解法：Dijkstra算法，2000多ms，巨慢的效率
    /*class Graph {
        int[][] vals;
        int n;

        public Graph(int n, int[][] edges) {
            this.n = n;
            vals = new int[n][n];
            for(int i=0;i < n;i++){
                Arrays.fill(vals[i],Integer.MAX_VALUE/3);
                vals[i][i] = 0;
            }
            for(int i=0;i < edges.length;i++){
                int x = edges[i][0],y = edges[i][1],v = edges[i][2];
                vals[x][y] = v;
            }
            for(int i=0;i < n;i++){
                update(i);
            }
        }

        private void update(int loc) {
            int[] tmp = new int[n];
            boolean[] set = new boolean[n];
            set[loc] = true;
            for(int i=0;i < n;i++){
                tmp[i] = vals[loc][i];
            }
            int c = n-1;
            while(c > 0){
                int minV = Integer.MAX_VALUE/3,minL = loc;
                for(int i=0;i < n;i++){
                    if(!set[i] && minV > tmp[i]){
                        minV = tmp[i];
                        minL = i;
                    }
                }
                if(minL != loc){
                    set[minL] = true;
                    for(int i=0;i < n;i++){
                        if(!set[i] && tmp[i] > minV + vals[minL][i]){
                            tmp[i] = minV + vals[minL][i];
                        }
                    }
                }
                c--;
            }
            for(int i=0;i < n;i++){
                vals[loc][i] = Math.min(vals[loc][i],tmp[i]);
            }
        }

        public void addEdge(int[] edge) {
            int x = edge[0],y = edge[1],v = edge[2];
            vals[x][y] = v;
            for(int i=0;i < n;i++){
                update(i);
            }
        }

        public int shortestPath(int node1, int node2) {
            return vals[node1][node2] >= Integer.MAX_VALUE/3 ? -1 : vals[node1][node2];
        }
    }*/
}
