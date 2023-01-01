package senior.动态规划;

public class No3 {
    /**
     *      完全平方数
     */

    //当前数n的完全平方数由与n最近的完全平方数转移而来
    /*List<Integer> container;
    {
        container = new ArrayList<>();
        for (int i = 1; i * i <= 100000000; i++) {
            container.add(i*i);
        }
    }
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        //先二分查找到离n最近（小于n）的完全平方数的下标
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            int next = find(i);
            while (true) {
                dp[i] = dp[i-container.get(next)]+1;
                if (i == container.get(next) || dp[i] > 1) {
                    break;
                }
                next--;
            }
        }

        return dp[n];
    }
    public int find(int n){
        int l = 0,r = container.size()-1,c,res = 0;

        while (l < r) {
            c = l+((r-l)>>1);
            if (container.get(c) > n) {
                r = c-1;
            } else if (container.get(c) < n) {
                l = c+1;
                res = c;
            }else{
                res = c;
                break;
            }
        }

        if (l == r && container.get(l) <= n) {
            res = l;
        }
        //System.out.println(res);
        return res;
    }*/


    //没有思考到点上，仔细思考一下，这个题目是否就是零钱兑换？
    //使用零钱兑换解法，200多ms，效率很低，优化一下
    /*List<Integer> container;
    {
        container = new ArrayList<>();
        for (int i = 1; i * i <= 10000; i++) {
            container.add(i*i);
        }
    }
    public int numSquares(int n) {
        int next = find(n);
        if (container.get(next) == n) {
            return 1;
        }
        int[][] dp = new int[next+2][n+1];
        dp[0][0] = 0;
        for (int i = 1; i < next+2; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = 10001;
        }

        for (int i = 1; i < next + 2; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (container.get(i - 1) > j) {
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.min(dp[i][j-container.get(i-1)]+1,dp[i-1][j]);
                }
            }
        }

        return dp[next+1][n];
    }
    public int find(int n){
        int l = 0,r = container.size()-1,c,res = 0;

        while (l < r) {
            c = l+((r-l)>>1);
            if (container.get(c) > n) {
                r = c-1;
            } else if (container.get(c) < n) {
                l = c+1;
                res = c;
            }else{
                res = c;
                break;
            }
        }

        if (l == r && container.get(l) <= n) {
            res = l;
        }
        //System.out.println(res);
        return res;
    }*/
    /*static List<Integer> container;
    static {
        container = new ArrayList<>();
        for (int i = 1; i * i <= 10000; i++) {
            container.add(i*i);
        }
    }
    public int numSquares(int n) {
        int next = find(n);
        if (container.get(next) == n) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = 10001;
            for (int j = 1; j <= next; j++) {
                if (container.get(j) <= i && dp[i - container.get(j)] + 1 < dp[i]) {
                    dp[i] = dp[i-container.get(j)]+1;
                }
            }
        }

        return dp[n];
    }
    public int find(int n){
        int l = 0,r = container.size()-1,c,res = 0;

        while (l < r) {
            c = l+((r-l)>>1);
            if (container.get(c) > n) {
                r = c-1;
            } else if (container.get(c) < n) {
                l = c+1;
                res = c;
            }else{
                res = c;
                break;
            }
        }

        if (l == r && container.get(l) <= n) {
            res = l;
        }
        //System.out.println(res);
        return res;
    }*/


    //不单独使用数组记录完全平方
   /* public int numSquares(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            int temp = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                temp = Math.min(temp,dp[i-j*j]);
            }
            dp[i] = temp+1;
        }
        List<Integer> list = new ArrayList<>();
        list.get(0);
        return dp[n];
    }*/

    //******************************* 重新写一遍 *************************************

    //成功，不过效率不是很高，47ms
    /*public int numSquares(int n) {
        int[] dp = new int[n+1],map = new int[100];
        for(int i = 0;i < 100;i++){
            map[i] = (i+1) * (i+1);
        }
        for(int i = 1;i <= n;i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0;j < 100;j++){
                if(map[j] > i)
                    break;
                dp[i] = Math.min(dp[i],dp[i-map[j]]+1);
            }
        }
        return dp[n];
    }*/

    //24ms
    /*public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1;i <= n;i++){
            //注意细节，不用每次操作都去存到内存中数组的位置上，使用局部变量来存储，这是一个优化
            int temp = Integer.MAX_VALUE;
            for(int j = 1;j <= 100;j++){
                int temp2 = j * j;
                if(temp2 > i)
                    break;
                temp = Math.min(temp,dp[i-temp2]+1);
            }
            dp[i] = temp;
        }
        return dp[n];
    }*/

    //基本上是最优解，不用每次操作都做加法
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1;i < n+1;i++){
            int temp = Integer.MAX_VALUE;
            for(int j = 1;j*j < i+1;j++){
                temp = Math.min(temp,dp[i-j*j]);
            }
            dp[i] = temp+1;
        }
        return dp[n];
    }
}
