package contest.year2022.m9.m9d3;

public class No3 {
    /**
     *      被列覆盖的最多行数
     */

    //使用位运算，操作集合的思想
    /*Map<String,Integer> map;
    int res,cols,n,m;
    public int maximumRows(int[][] mat, int cols) {
        m = mat.length;
        n = mat[0].length;
        res = 0;
        this.cols = cols;
        map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(mat[i][j]);
            }
            map.put(sb.toString(),map.getOrDefault(sb.toString(),0)+1);
        }
        recursion(0,0,new StringBuilder());
        return res;
    }
    public void recursion(int loc,int c,StringBuilder sb){
        if(c == cols){
            Integer count = map.get(sb.toString());
            if(count!= null &&count > res)
                res = count;
        }
        if (loc == n) {
            return;
        }

        //选择为1

        sb.append(1);
        recursion(loc+1,c+1,sb);
        sb.deleteCharAt(sb.length()-1);
        //选择为0
        sb.append(0);
        recursion(loc+1,c,sb);
        sb.deleteCharAt(sb.length()-1);
    }*/

    //状态压缩位运算，失败，错误的写法
    /*public int maximumRows(int[][] mat, int cols) {
        int res = 0,m = mat.length,n = mat[0].length,k = 1<<14;
        int[] dp = new int[k];

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    sum += 1<<j;
                }
            }
            dp[sum]++;
        }
        int end = 1<<n;
        for (int i = 1; i < end; i++) {
            if(dp[i] == 0 || Long.bitCount(i) > cols)
                continue;
            int temp = i;
            while (temp != 0) {
                int bit = lowBit(temp);
                temp -= bit;
                dp[i] += dp[i-bit];
            }
            if(dp[i] > res)
                res = dp[i];
        }

        return res;
    }
    public int lowBit(int n){
        return n & (-n);
    }*/

    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length,n = mat[0].length,res = 0,end = 1 << n;
        if(cols >= n){
            return m;
        }
        for(int i = 1;i < end;i++){
            int sum = 0;
            if(Long.bitCount(i) > cols)
                continue;
            for(int j = 0;j < m;j++){
                boolean flag = false;
                for(int k = 0;k < n;k++){
                    if(mat[j][k] == 1 && ((i >> k) & 1) == 0){
                        flag = true;
                        break;
                    }
                }
                if(!flag)
                    sum++;
            }
            res = Math.max(sum,res);
        }
        return res;
    }
}
