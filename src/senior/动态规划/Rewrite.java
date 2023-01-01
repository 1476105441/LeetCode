package senior.动态规划;

public class Rewrite {
    //解法一：动态规划，1ms
    /*public boolean wordBreak(String s, List<String> wordDict) {
        char[] c = s.toCharArray();
        char[][] t = new char[wordDict.size()][];
        int n = c.length,m = t.length;

        for (int i = 0; i < m; i++) {
            t[i] = wordDict.get(i).toCharArray();
        }

        boolean[] dp = new boolean[n];  //dp[i]表示以i结尾的子串能否被wordDict里的单词拼接上

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i+1 < t[j].length || (i+1 != t[j].length && !dp[i-t[j].length]))
                    continue;
                int l = i+1-t[j].length;
                boolean flag = true;
                for (int k = 0; k < t[j].length; k++) {
                    if (c[l] != t[j][k]) {
                        flag = false;
                        break;
                    }
                    l++;
                }
                if (flag) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n-1];
    }*/

    //解法二：DFS
    /*char[] c;
    List<char[]> list;
    boolean res;
    boolean[] set;
    public boolean wordBreak(String s, List<String> wordDict){
        c = s.toCharArray();
        set = new boolean[c.length];
        list = new ArrayList<>(wordDict.size());
        for (int i = 0; i < wordDict.size(); i++) {
            list.add(wordDict.get(i).toCharArray());
        }
        dfs(0);
        return res;
    }
    public void dfs(int loc){
        if (loc == c.length) {
            res = true;
            return;
        }
        if(set[loc])
            return;

        for (char[] t : list) {
            if(c.length - loc < t.length)
                continue;
            int i = loc,j = 0;
            boolean flag = true;
            while (j < t.length) {
                if (c[i] != t[j]) {
                    flag = false;
                    break;
                }
                i++;
                j++;
            }
            if(flag)
                dfs(loc+j);
            if(res)
                return;
        }
        set[loc] = true;
    }*/

    //解法三：BFS，5ms
    /*public boolean wordBreak(String s, List<String> wordDict) {
        char[] c = s.toCharArray();
        int n = c.length;
        List<char[]> t = new ArrayList<>(wordDict.size());
        for (String str : wordDict) {
            t.add(str.toCharArray());
        }
        boolean[] set = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            Integer loc = queue.poll();
            for (char[] chars : t) {
                if(loc+chars.length-1 >= n || set[loc])
                    continue;
                boolean flag = true;
                int j = loc;
                for (int i = 0; i < chars.length; i++) {
                    if (c[j++] != chars[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if(j == n)
                        return true;
                    queue.offer(j);
                }
            }
            set[loc] = true;
        }
        return false;
    }*/

    //BFS，2ms
    /*public boolean wordBreak(String s, List<String> wordDict) {
        char[] c = s.toCharArray();
        int n = c.length;
        List<char[]> t = new ArrayList<>(wordDict.size());
        for (String str : wordDict) {
            t.add(str.toCharArray());
        }
        boolean[] set = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            Integer loc = queue.poll();
            for (char[] chars : t) {
                if(loc+chars.length-1 >= n || set[loc+chars.length-1])
                    continue;
                boolean flag = true;
                int j = loc;
                for (int i = 0; i < chars.length; i++) {
                    if (c[j++] != chars[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if(j == n)
                        return true;
                    queue.offer(j);
                    set[loc+chars.length-1] = true;
                }
            }
        }
        return false;
    }*/

    //单词拆分II
    //再写一遍，梳理一下思路
    /*char[] c;
    int n;
    List<char[]> words;
    List<char[]>[] nodes;
    int[] set;
    List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict){
        c = s.toCharArray();
        n = c.length;
        words = new ArrayList<>(wordDict.size());
        nodes = new List[n];
        set = new int[n+1];
        res = new ArrayList<>();
        for (String t : wordDict) {
            words.add(t.toCharArray());
        }
        dfs(0);

        build(n-1,new ArrayList<>());

        return res;
    }
    public void build(int loc,List<char[]> list){
        if (loc < 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = list.size() - 1; i > -1; i--) {
                sb.append(list.get(i));
                if(i > 0)
                    sb.append(" ");
            }
            res.add(sb.toString());
            return;
        }
        if(nodes[loc] == null)
            return;
        for (char[] chars : nodes[loc]) {
            list.add(chars);
            build(loc-chars.length,list);
            list.remove(list.size()-1);
        }
    }
    public boolean dfs(int loc){
        if(loc == n)
            return true;
        boolean stamp = false;
        for (char[] word : words) {
            int nl = loc + word.length - 1;
            if(nl >= n || set[nl+1] == -1)
                continue;
            if (match(loc, word)) {
                if (nodes[nl] == null) {
                    nodes[nl] = new ArrayList<>();
                }
                nodes[nl].add(word);
                if (set[nl + 1] == 0) {
                    if (dfs(nl + 1)) {
                        stamp = true;
                        set[nl + 1] = 1;
                    } else {
                        set[nl + 1] = -1;
                    }
                }else
                    stamp = true;
            }
        }
        return stamp;
    }
    public boolean match(int loc,char[] temp){
        for (int i = 0; i < temp.length; i++) {
            if(c[loc] != temp[i])
                return false;
            loc++;
        }
        return true;
    }*/


    //想到一个贪心策略，失败，想的太简单了
    /*public int maxCoins(int[] nums) {
        int n = nums.length,l = 0,r = n-1,left = 1,right = 1,res = 0;
        while (l < r) {
            if (nums[l] > left && nums[r] > right) {
                if (right > left) {
                    res += nums[l] * left * right;
                    left = nums[l];
                    l++;
                } else if(right < left) {
                    res += nums[r] * left * right;
                    right = nums[r];
                    r--;
                } else {
                    if (nums[l] > nums[r]) {
                        res += nums[l] * left * right;
                        left = nums[l];
                        l++;
                    } else {
                        res += nums[r] * left * right;
                        right = nums[r];
                        r--;
                    }
                }
            } else if (nums[l] > left) {
                res += nums[l] * left * right;
                left = nums[l];
                l++;
            } else if (nums[r] > right) {
                res += nums[r] * left * right;
                right = nums[r];
                r--;
            } else {
                if (nums[l] > nums[r]) {
                    res += nums[l] * left * right;
                    left = nums[l];
                    l++;
                } else {
                    res += nums[r] * left * right;
                    right = nums[r];
                    r--;
                }
            }
        }
        res += left * nums[l] * right;
        return res;
    }*/


    //解法一：记忆化搜索
    /*int[][] temp;
    int[] nums1;
    public int maxCoins(int[] nums){
        int n = nums.length;
        temp = new int[n+2][n+2];
        nums1 = new int[n+2];
        nums1[0] = 1;
        nums1[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            nums1[i] = nums[i-1];
        }

        return dfs(0,n+1);
    }

    public int dfs(int i, int j) {
        if(i >= j-1)
            return 0;
        if(temp[i][j] != 0)
            return temp[i][j];
        int max = 0,t = nums1[i]*nums1[j];
        for (int k = i+1; k < j; k++) {
            max = Math.max(max,t * nums1[k] + dfs(i,k) + dfs(k,j));
        }
        temp[i][j] = max;
        return max;
    }*/


    /*public int maxCoins(int[] nums){
        int n = nums.length;
        int[] temp = new int[n+2];
        int[][] dp = new int[n+2][n+2];
        temp[0] = temp[n+1] = 1;
        for (int i = 1; i < n + 1; i++) {
            temp[i] = nums[i-1];
        }
        for (int l = 2; l < n + 1; l++) {
            for (int i = 0; i + l < n + 2; i++) {
                int j = i + l,t = temp[i] * temp[j];
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],t*temp[k]+dp[i][k]+dp[k][j]);
                }
            }
        }

        return dp[0][n+1];
    }*/


    //深搜，不加记忆化会超时
    /*int[] temp;
    int[][] vals;
    int n;
    public int maxCoins(int[] nums){
        n = nums.length;
        temp = new int[n+2];
        vals = new int[n+2][n+2];

        temp[0] = temp[n+1] =  1;
        for (int i = 0; i < n; i++) {
            temp[i+1] = nums[i];
        }

        return dfs(0,n+1);
    }
    public int dfs(int l,int r){
        if(l >= r-1)
            return 0;

        if(vals[l][r] != 0)
            return vals[l][r];

        int res = 0;

        int val = temp[l] * temp[r];
        for (int i = l+1; i < r; i++) {
            res = Math.max(res,val * temp[i] + dfs(l,i) + dfs(i,r));
        }
        vals[l][r] = res;

        return res;
    }*/


    //动态规划
    /*public int maxCoins(int[] nums){
        int n = nums.length;
        int[] temp = new int[n+2];
        int[][] dp = new int[n+2][n+2];

        for (int i = 0; i < n - 1; i++) {
            dp[i][i+1] = 1;
            temp[i+1] = nums[i];
        }
        temp[n] = temp[n-1];
        temp[0] = temp[n+1] = 1;
        for (int l = 2; l < n + 2; l++) {
            for (int i = 0; i < n + 2 - l; i++) {
                int j = i + l,val = temp[i]*temp[j];
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],val*temp[k] + dp[i][k] + dp[k][j]);
                }
            }
        }

        return dp[0][n+1];
    }*/

    public int maxCoins(int[] nums){
        int n = nums.length;
        int[] temp = new int[n+2];
        int[][] dp = new int[n+2][n+2];

        for (int i = 0; i < n; i++) {
            temp[i+1] = nums[i];
        }
        temp[0] = temp[n+1] = 1;
        for (int l = 2; l < n + 2; l++) {
            for (int i = 0; i < n + 2 - l; i++) {
                int j = i + l,val = temp[i]*temp[j];
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],val*temp[k] + dp[i][k] + dp[k][j]);
                }
            }
        }

        return dp[0][n+1];
    }
}
