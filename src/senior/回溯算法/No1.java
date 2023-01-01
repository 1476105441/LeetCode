package senior.回溯算法;

public class No1 {
    /**
     *          分割回文串
     * 给你一个字符串s，请你将s分割成一些子串，使每个子串都是回文串。
     * 返回s所有可能的分割方案。
     * 回文串是正着读和反着读都一样的字符串。
     *
     * 提示：
     *     1 <= s.length <= 16
     *     s 仅由小写英文字母组成
     */

    /**
     * 思路：
     * 回溯时先直接进入下一轮，也就是每个字符串
     * 都是单独存在的，然后回来后以当前节点为起
     * 始，寻找后面有没有回文串，有的话就进入循
     * 环，没有就返回
     */
    //成功，29ms，很慢
    /*List<List<String>> res;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        recursion(s.toCharArray(),0,new StringBuilder());
        return res;
    }

    public void recursion(char[] chars,int loc,StringBuilder sb){
        if (loc == chars.length) {
            String[] strings = sb.toString().split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                list.add(strings[i]);
            }
            res.add(list);
            return;
        }

        sb.append(chars[loc]).append(',');
        recursion(chars,loc+1,sb);
        int len = sb.length(),end = chars.length-1;
        sb.delete(len-2,len);
        while (end > loc) {
            if (chars[loc] == chars[end]) {
                if (isTrue(chars, loc, end)) {
                    for (int i = loc; i <= end; i++) {
                        sb.append(chars[i]);
                    }
                    sb.append(',');
                    recursion(chars,end+1,sb);
                    len = sb.length();
                    sb.delete(len-(end-loc+2),len);
                }
            }
            end--;
        }

    }

    public boolean isTrue(char[] chars,int start,int end){
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }*/

    //使用动态规划判断是不是回文串，36ms，更慢了
    /*List<List<String>> res;
    boolean[][] flags;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        flags = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            flags[i][i] = true;
            if (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                flags[i][i + 1] = true;
            }
        }
        for (int len = 2; len < chars.length; len++) {
            for (int i = 0; i < chars.length - len; i++) {
                if (chars[i] == chars[i + len] && flags[i + 1][i + len - 1]) {
                    flags[i][i + len] = true;
                }
            }
        }
        recursion(chars, 0, new StringBuilder());
        return res;
    }

    public void recursion(char[] chars, int loc, StringBuilder sb) {
        if (loc == chars.length) {
            String[] strings = sb.toString().split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                list.add(strings[i]);
            }
            res.add(list);
            return;
        }

        sb.append(chars[loc]).append(',');
        recursion(chars, loc + 1, sb);
        int len = sb.length(), end = chars.length - 1;
        sb.delete(len - 2, len);
        while (end > loc) {
            if (flags[loc][end]) {
                for (int i = loc; i <= end; i++) {
                    sb.append(chars[i]);
                }
                sb.append(',');
                recursion(chars, end + 1, sb);
                len = sb.length();
                sb.delete(len - (end - loc + 2), len);
            }
            end--;
        }
    }*/

    //根据官方题解进行了优化：使用字符串截取比使用stringBuilder构建要快
    //动态规划也做了优化，选择从后往前填表，回溯也进行了优化
    /*List<List<String>> res;
    boolean[][] flags;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        flags = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            //全部填成真是因为在判断长度为2的回文串时需要用到，而其他情况不会影响正常结果
            Arrays.fill(flags[i],true);
        }

        //从后往前来填写动态规划表可以直接把两个的情况归为一般情况因为
        //当你时两个相邻元素时j=i+1，则flags[i+1][j-1]就是flags[j][i]
        //而从后往前填表，flags[j][i]一定先于flags[i][j]
        for (int i = chars.length-1; i >= 0; i--) {
            for (int j = i+1; j < chars.length; j++) {
                flags[i][j] = chars[i] == chars[j] && flags[i+1][j-1];
            }
        }
        recursion(chars, 0, new ArrayList<>(),s);
        return res;
    }
    public void recursion(char[] chars, int loc, List<String> ans,String s) {
        if (loc == chars.length) {
            List<String> list = new ArrayList<>(ans);
            res.add(list);
            return;
        }
        for (int i = loc; i < chars.length; i++) {
            if (flags[loc][i]) {
                ans.add(s.substring(loc,i+1));
                recursion(chars, i + 1, ans,s);
                int len = ans.size();
                ans.remove(len-1);
            }
        }
    }*/

    /**
     * 解法二：回溯+记忆化搜索
     * 记忆化搜索：搜索时进行标记，如果是回文串标记为1，
     * 不是回文串标记为-1，没有搜索过标记为0
     */
    /*List<List<String>> res;
    int[][] flags;
    public List<List<String>> partition(String s){
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        flags = new int[chars.length][chars.length];

        dfs(chars, 0, new ArrayList<>(),s);
        return res;
    }
    public void dfs(char[] chars,int loc,List<String> list,String s){
        if (loc == chars.length) {
            List<String> ans = new ArrayList<>(list);
            res.add(ans);
            return;
        }
        for (int i = loc; i < chars.length; i++) {
            if (search(loc,i,chars) == 1) {
                list.add(s.substring(loc,i+1));
                dfs(chars,i+1,list,s);
                list.remove(list.size()-1);
            }
        }
    }
    public int search(int i,int j,char[] chars){
        if (flags[i][j] != 0) {
            return flags[i][j];
        }
        if (i >= j) {
            flags[i][j] = 1;
        }else if(chars[i] == chars[j]){
            flags[i][j] = search(i+1,j-1,chars);
        }else{
            flags[i][j] = -1;
        }
        return flags[i][j];
    }*/


    //重写，回溯解法，12ms
    /*List<List<String>> res;
    //回溯
    public List<List<String>> partition(String s) {
        char[] cs = s.toCharArray();
        res = new ArrayList<>();
        recursion(cs,0,new ArrayList<>());
        return res;
    }
    public void recursion(char[] c,int loc,List<String> list){
        //到了结束地点，在进入函数中做约束，必要满足条件才能进入
        if(loc == c.length){
            List<String> temp = new ArrayList<>();
            for(String s : list){
                temp.add(s);
            }
            res.add(temp);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c[loc]);
        list.add(sb.toString());
        recursion(c,loc+1,list);
        list.remove(list.size()-1);

        for(int i = loc+1;i < c.length;i++){
            sb.append(c[i]);
            if(isPartition(sb)){
                list.add(sb.toString());
                recursion(c,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
    public boolean isPartition(StringBuilder sb){
        char[] c = sb.toString().toCharArray();
        int l = 0,r = c.length-1;
        while(l < r){
            if(c[l] != c[r])
                return false;
            l++;
            r--;
        }
        return true;
    }*/

    //将是否是回文串的判断存储下来，加了一个记忆化操作，避免了重复的判断，6ms
    /*List<List<String>> res;
    int[][] flag;
    char[] c;
    public List<List<String>> partition(String s) {
        c = s.toCharArray();
        int n = c.length;
        flag = new int[n][n];
        res = new ArrayList<>();
        find(0,new ArrayList<>());
        return res;
    }
    public void find(int loc,List<String> list){
        if(loc == c.length){
            List<String> temp = new ArrayList<>(list);
            res.add(temp);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c[loc]);
        list.add(sb.toString());
        find(loc+1,list);
        list.remove(list.size()-1);

        for(int i=loc+1;i < c.length;i++){
            sb.append(c[i]);
            if(isPartition(loc,i) == 1){
                list.add(sb.toString());
                find(i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
    public int isPartition(int i,int j){
        if(flag[i][j] != 0)
            return flag[i][j];
        if(i == j){
            flag[i][j] = 1;
            return flag[i][j];
        }
        if(i > j){
            return 1;
        }
        if(c[i] != c[j]){
            flag[i][j] = -1;
            return -1;
        }else{
            flag[i][j] = isPartition(i+1,j-1);
            return flag[i][j];
        }
    }*/
}
